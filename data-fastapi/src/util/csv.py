import threading
from datetime import timezone
import yfinance as yf
import pandas as pd
from dao.economy_dao import Economy

eco = Economy.get_instance()

tickers = {
    'dow': '^DJI',
    'sp500': '^GSPC',
    'nasdaq100': '^NDX',
    'russell2000': '^RUT',
    '2yr_treasury': '^IRX',
    '10yr_treasury': '^TNX',
    'dollar_index': 'DX-Y.NYB',
    'gold': 'GC=F',
    'wti_oil': 'CL=F',
    'natural_gas': 'NG=F'
}

# 각 자산별로 전일 종가를 저장할 변수 초기화
previous_closes = {ticker: None for ticker in tickers.values()}


def print_data(row, date, change_percentage):
    print(
        f"date: {date}\n"
        f"open_price: {row['Open']}\n"
        f"close_price: {row['Close']}\n"
        f"high_price: {row['High']}\n"
        f"low_price: {row['Low']}\n"
        f"volume: {row['Volume']}\n"
        f"change_percentage: {change_percentage}%\n"
    )

def refactoring_to_db(ticker, row, date, change_percentage):
    eco.insert_data(ticker, row, date, change_percentage)

def save_to_csv(index_name, data):
    # 데이터프레임을 생성하고 csv 파일로 저장
    df = pd.DataFrame(data)
    df.to_csv(f'{index_name}.csv', index=False)
    print(f'{index_name}_summary.csv 파일이 생성되었습니다.')

# 데이터를 저장할 리스트 초기화
data_dict = {index_name: [] for index_name in tickers.keys()}

# 데이터를 CSV로 저장하는 처리 과정
for index_name, ticker in tickers.items():
    try:
        # yfinance를 사용하여 데이터 다운로드
        stock_data = yf.Ticker(ticker)
        history = stock_data.history(period="5d")
        # history = stock_data.history(period="1mo")
        print(index_name)
        # 데이터를 순회하면서 저장
        for date, row in history.iterrows():
            previous_close = previous_closes[ticker]

            # 변동률 계산
            if previous_close:
                change_percentage = ((row['Close'] - previous_close) / previous_close) * 100
            else:
                change_percentage = 0.0

            # 시간대가 포함된 날짜 처리
            # date = date.replace(tzinfo=timezone.utc)

            # 데이터를 리스트에 저장
            data_dict[index_name].append({
                'datetime': date,
                'date': date.date(),
                'open_price': row['Open'],
                'close_price': row['Close'],
                'high_price': row['High'],
                'low_price': row['Low'],
                'volume': row['Volume'],
                'change_percentage': change_percentage
            })

            # 데이터베이스에 저장 (트랜잭션 처리)
            # refactoring_to_db(index_name, row, date.date(), change_percentage)

            # 전일 종가 업데이트
            previous_closes[ticker] = row['Close']

        # 각 index_name 별로 데이터를 CSV 파일로 저장
        save_to_csv(index_name, data_dict[index_name])

    except Exception as e:
        print(f"Failed to fetch data for {index_name}: {e}")