import threading
from tqdm import tqdm
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

ticker_functions = {
    '^GSPC': eco.insert_sp500,
    '^DJI': eco.insert_dow,
    '^NDX': eco.insert_nasdaq100,
    '^RUT': eco.insert_russell2000,
    '^IRX': eco.insert_treasury_2yr,
    '^TNX': eco.insert_treasury_10yr,
    'DX-Y.NYB': eco.insert_dollar_index,
    'GC=F': eco.insert_gold,
    'CL=F': eco.insert_wti_oil,
    'NG=F': eco.insert_natural_gas
}


def print_data(ticker, row, date, change_percentage):
    print(ticker)
    print(
        f"date: {date}\n"
        f"open_price: {row['Open']}\n"
        f"close_price: {row['Close']}\n"
        f"high_price: {row['High']}\n"
        f"low_price: {row['Low']}\n"
        f"volume: {row['Volume']}\n"
        f"change_percentage: {change_percentage}%\n"
    )


def save_summary_to_db(ticker, row, date, change_percentage):
    insert_function = ticker_functions.get(ticker)
    if insert_function:
        insert_function(row, date, change_percentage)
    else:
        print(f"No insert function found for ticker: {ticker}")


def process_ticker(ticker, progress_bar):
    try:
        # yfinance를 사용하여 데이터 다운로드
        stock_data = yf.Ticker(ticker)
        history = stock_data.history(period="5d")

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

            # 데이터베이스에 저장
            save_summary_to_db(ticker, row, date.date(), change_percentage)

            # 전일 종가 업데이트
            previous_closes[ticker] = row['Close']

        # 진행 상황 업데이트
        progress_bar.update(1)

    except Exception as e:
        print(f"Failed to fetch data for {ticker}: {e}")
        progress_bar.update(1)


# tqdm을 사용하여 진행 상태 표시
def main():
    threads = []
    with tqdm(total=len(tickers)) as progress_bar:
        for index_name, ticker in tickers.items():
            thread = threading.Thread(target=process_ticker, args=(ticker, progress_bar))
            threads.append(thread)
            thread.start()

        # 모든 스레드가 완료될 때까지 대기
        for thread in threads:
            thread.join()

    print("All tickers have been processed.")


if __name__ == "__main__":
    main()
