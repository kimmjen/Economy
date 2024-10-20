from pprint import pprint

from dao.db_conn import DBConn, FETCH


class Economy(DBConn):

    def insert_data(self, table_name, row, date, change_percentage):
        return self._execute(f'{table_name}$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def retrieve_data_by_date_range(self, table_name, start_date, end_date, cursor_name):
        return self._call(f'{table_name}$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name=cursor_name)

    def insert_dow(self, row, date, change_percentage):
        return self._call('dow$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_sp500(self, row, date, change_percentage):
        return self._call('sp500$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_nasdaq100(self, row, date, change_percentage):
        return self._call('nasdaq100$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_russell2000(self, row, date, change_percentage):
        return self._call('russell2000$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_treasury_2yr(self, row, date, change_percentage):
        return self._call('treasury_2yr$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_treasury_10yr(self, row, date, change_percentage):
        return self._call('treasury_10yr$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_dollar_index(self, row, date, change_percentage):
        return self._call('dollar$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_gold(self, row, date, change_percentage):
        return self._call('gold$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_wti_oil(self, row, date, change_percentage):
        return self._call('wti_oil$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def insert_natural_gas(self, row, date, change_percentage):
        return self._call('natural_gas$insert',
                          [date, float(row['Open']), float(row['Close']), float(row['High']), float(row['Low']),
                           int(row['Volume']), float(change_percentage)])

    def retrieve_dow_date_range(self, start_date, end_date):
        return self._call('dow$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="dow_cursor")

    def retrieve_sp500_date_range(self, start_date, end_date):
        return self._call('sp500$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="sp500_cursor")

    def retrieve_nasdaq100_date_range(self, start_date, end_date):
        return self._call('nasdaq100$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="nasdaq100_cursor")

    def retrieve_russell2000_date_range(self, start_date, end_date):
        return self._call('russell2000$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="russell2000_cursor")

    def retrieve_treasury_2yr_date_range(self, start_date, end_date):
        return self._call('treasury_2yr$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="treasury_2yr_cursor")

    def retrieve_treasury_10yr_date_range(self, start_date, end_date):
        return self._call('treasury_10yr$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="treasury_10yr_cursor")

    def retrieve_dollar_index_date_range(self, start_date, end_date):
        return self._call('dollar_index$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="dollar_index_cursor")

    def retrieve_gold_date_range(self, start_date, end_date):
        return self._call('gold$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="gold_cursor")

    def retrieve_wti_oil_date_range(self, start_date, end_date):
        return self._call('wti_oil$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="wti_oil_cursor")

    def retrieve_natural_gas_date_range(self, start_date, end_date):
        return self._call('natural_gas$list_by_date_range', [start_date, end_date], fetch=FETCH.FETCH_ALL,
                          cursor_name="natural_gas_cursor")


if __name__ == '__main__':
    import pandas as pd
    def format(data):
        df = pd.DataFrame(data)
        pprint(df)
    eco_dao = Economy.get_instance()
    start_date = '2024-10-01'
    end_date = '2024-10-10'
    dow = eco_dao.retrieve_dow_date_range(start_date, end_date)
    format(dow)
    sp500 = eco_dao.retrieve_sp500_date_range(start_date, end_date)
    format(sp500)
    nasdaq100 = eco_dao.retrieve_nasdaq100_date_range(start_date, end_date)
    format(nasdaq100)
    russell2000 = eco_dao.retrieve_russell2000_date_range(start_date, end_date)
    format(russell2000)
    yr2 = eco_dao.retrieve_treasury_2yr_date_range(start_date, end_date)
    format(yr2)
    yr10 = eco_dao.retrieve_treasury_10yr_date_range(start_date, end_date)
    format(yr10)
    dollar = eco_dao.retrieve_dollar_index_date_range(start_date, end_date)
    format(dollar)
    gold = eco_dao.retrieve_gold_date_range(start_date, end_date)
    format(gold)
    oil = eco_dao.retrieve_wti_oil_date_range(start_date, end_date)
    format(oil)
    gas = eco_dao.retrieve_natural_gas_date_range(start_date, end_date)
    format(gas)
    # pandas DataFrame으로 변환

    # DataFrame 출력

