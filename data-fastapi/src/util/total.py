from datetime import timezone

import yfinance as yf

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

def print_summary_to_db(ticker, row, date, change_percentage):
    if ticker == '^GSPC':
        print_data(row, date, change_percentage)
    elif ticker == '^DJI':
        print_data(row, date, change_percentage)
    elif ticker == '^NDX':
        print_data(row, date, change_percentage)
    elif ticker == '^RUT':
        print_data(row, date, change_percentage)
    elif ticker == '^IRX':
        print_data(row, date, change_percentage)
    elif ticker == '^TNX':
        print_data(row, date, change_percentage)
    elif ticker == 'DX-Y.NYB':
        print_data(row, date, change_percentage)
    elif ticker == 'GC=F':
        print_data(row, date, change_percentage)
    elif ticker == 'CL=F':
        print_data(row, date, change_percentage)
    elif ticker == 'NG=F':
        print_data(row, date, change_percentage)

def refactoring_to_db(ticker, row, date, change_percentage):
    eco.insert_data(ticker, row, date, change_percentage)

def save_summary_to_db(ticker, row, date, change_percentage):
    if ticker == '^GSPC':
        eco.insert_sp500(row, date, change_percentage)
    elif ticker == '^DJI':
        eco.insert_dow(row, date, change_percentage)
    elif ticker == '^NDX':
        eco.insert_nasdaq100(row, date, change_percentage)
    elif ticker == '^RUT':
        eco.insert_russell2000(row, date, change_percentage)
    elif ticker == '^IRX':
        eco.insert_treasury_2yr(row, date, change_percentage)
    elif ticker == '^TNX':
        eco.insert_treasury_10yr(row, date, change_percentage)
    elif ticker == 'DX-Y.NYB':
        eco.insert_dollar_index(row, date, change_percentage)
    elif ticker == 'GC=F':
        eco.insert_gold(row, date, change_percentage)
    elif ticker == 'CL=F':
        eco.insert_wti_oil(row, date, change_percentage)
    elif ticker == 'NG=F':
        eco.insert_natural_gas(row, date, change_percentage)


for index_name, ticker in tickers.items():
    try:
        # yfinance를 사용하여 데이터 다운로드
        stock_data = yf.Ticker(ticker)
        history = stock_data.history(period="1d")
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
            date = date.replace(tzinfo=timezone.utc)

            # 데이터베이스에 저장 (트랜잭션 처리)
            # with transaction.atomic():
            # print_summary_to_db(ticker, row, date.date(), change_percentage)
            refactoring_to_db(index_name, row, date.date(), change_percentage)

            # 전일 종가 업데이트
            previous_closes[ticker] = row['Close']

    except Exception as e:
        print(f"Failed to fetch data for {index_name}: {e}")
