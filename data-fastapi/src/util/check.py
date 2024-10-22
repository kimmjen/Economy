import threading
from tqdm import tqdm
from datetime import timezone
import yfinance as yf
from dao.economy_dao import Economy

# Economy 인스턴스 생성
eco = Economy.get_instance()

# 티커와 테이블 매칭
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

# 이전 종가 저장용
previous_closes = {ticker: None for ticker in tickers.values()}

# 티커별 데이터 삽입 함수 정의
ticker_functions = {
    '^GSPC': lambda row, date, change_percentage: eco.insert_data('sp500', row, date, change_percentage),
    '^DJI': lambda row, date, change_percentage: eco.insert_data('dow', row, date, change_percentage),
    '^NDX': lambda row, date, change_percentage: eco.insert_data('nasdaq100', row, date, change_percentage),
    '^RUT': lambda row, date, change_percentage: eco.insert_data('russell2000', row, date, change_percentage),
    '^IRX': lambda row, date, change_percentage: eco.insert_data('treasury_2yr', row, date, change_percentage),
    '^TNX': lambda row, date, change_percentage: eco.insert_data('treasury_10yr', row, date, change_percentage),
    'DX-Y.NYB': lambda row, date, change_percentage: eco.insert_data('dollar_index', row, date, change_percentage),
    'GC=F': lambda row, date, change_percentage: eco.insert_data('gold', row, date, change_percentage),
    'CL=F': lambda row, date, change_percentage: eco.insert_data('wti_oil', row, date, change_percentage),
    'NG=F': lambda row, date, change_percentage: eco.insert_data('natural_gas', row, date, change_percentage)
}

# 함수 정의
def print_data(index_name, ticker, row, date, change_percentage):
    print("\n")
    print(index_name)
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


def process_ticker(ticker, progress_bar, index_name):
    try:
        stock_data = yf.Ticker(ticker)
        history = stock_data.history(period="5d")

        for date, row in history.iterrows():
            previous_close = previous_closes[ticker]

            if previous_close:
                change_percentage = ((row['Close'] - previous_close) / previous_close) * 100
            else:
                change_percentage = 0.0

            date = date.replace(tzinfo=timezone.utc)

            # save_summary_to_db(ticker, row, date.date(), change_percentage)
            print_data(index_name,ticker, row, date.date(), change_percentage)

            previous_closes[ticker] = row['Close']

        progress_bar.update(1)

    except Exception as e:
        print(f"Failed to fetch data for {ticker}: {e}")
        progress_bar.update(1)


# 데이터 수집을 관리하는 메인 함수
def main():
    threads = []
    with tqdm(total=len(tickers)) as progress_bar:
        for index_name, ticker in tickers.items():
            thread = threading.Thread(target=process_ticker, args=(ticker, progress_bar,index_name))
            threads.append(thread)
            thread.start()

        for thread in threads:
            thread.join()

    print("All tickers have been processed.")

if __name__ == '__main__':
    main()