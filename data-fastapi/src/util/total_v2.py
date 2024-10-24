from tqdm import tqdm
from datetime import timezone
import yfinance as yf
from dao.economy_dao import Economy

# Economy 인스턴스 가져오기
eco = Economy.get_instance()

# 티커 설정
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

# 티커에 따른 데이터베이스 삽입 함수 매핑
ticker_functions = {
    '^GSPC': lambda row, date, change_percentage: eco.insert_data('sp500', row, date, change_percentage),
    '^DJI': lambda row, date, change_percentage: eco.insert_data('dow', row, date, change_percentage),
    '^NDX': lambda row, date, change_percentage: eco.insert_data('nasdaq100', row, date, change_percentage),
    '^RUT': lambda row, date, change_percentage: eco.insert_data('russell2000', row, date, change_percentage),
    '^IRX': lambda row, date, change_percentage: eco.insert_data('treasury_2yr', row, date, change_percentage),
    '^TNX': lambda row, date, change_percentage: eco.insert_data('treasury_10yr', row, date, change_percentage),
    'DX-Y.NYB': lambda row, date, change_percentage: eco.insert_data('dollar', row, date, change_percentage),
    'GC=F': lambda row, date, change_percentage: eco.insert_data('gold', row, date, change_percentage),
    'CL=F': lambda row, date, change_percentage: eco.insert_data('wti_oil', row, date, change_percentage),
    'NG=F': lambda row, date, change_percentage: eco.insert_data('natural_gas', row, date, change_percentage)
}

def save_summary_to_db(ticker, row, date, change_percentage):
    retries = 3  # 재시도 횟수
    while retries > 0:
        try:
            insert_function = ticker_functions.get(ticker)
            if insert_function:
                insert_function(row, date.isoformat(), change_percentage)  # date를 isoformat()으로 문자열로 변환
            else:
                print(f"No insert function found for ticker: {ticker}")
            break
        except Exception as e:
            retries -= 1
            print(f"Failed to insert data for {ticker}, retries left: {retries}, error: {e}")
            if retries == 0:
                # 재시도 실패 시 로컬 파일에 저장
                with open('failed_inserts.txt', 'a') as f:
                    f.write(f"{ticker}, {date}, {change_percentage}, {e}\n")

def process_ticker(ticker):
    global previous_closes
    try:
        # yfinance를 사용하여 데이터 다운로드
        stock_data = yf.Ticker(ticker)
        history = stock_data.history(period="max")  # 최근 1년 데이터만 가져오기

        # 데이터를 순회하면서 저장
        for date, row in history.iterrows():
            previous_close = previous_closes[ticker]

            # 변동률 계산
            if previous_close:
                change_percentage = ((row['Close'] - previous_close) / previous_close) * 100
            else:
                change_percentage = 0.0

            previous_closes[ticker] = row['Close']  # 전일 종가 업데이트

            # 시간대가 포함된 날짜 처리
            date = date.replace(tzinfo=timezone.utc)

            # 데이터베이스에 저장
            save_summary_to_db(ticker, row, date.date(), change_percentage)

    except Exception as e:
        print(f"Failed to fetch data for {ticker}: {e}")

# 메인 함수
def main():
    with tqdm(total=len(tickers)) as progress_bar:
        for index_name, ticker in tickers.items():
            process_ticker(ticker)
            progress_bar.update(1)

    print("All tickers have been processed.")

if __name__ == "__main__":
    main()
