import json

import psycopg2.extras
import psycopg2 as pg2

from util.utils import ExtendedEnum
from aenum import auto


class FETCH(ExtendedEnum):
    FETCH_ONE = auto()
    FETCH_ALL = auto()

DEBUG = False
class DBConn:
    _instance = None

    def __init__(self, host, port, dbname, user, password):
        if host:
            if not DBConn._instance:
                self.host = "localhost"
                self.port = 5432
                self.dbname = "economy"
                self.user = "postgres"
                self.password = "12341234"

    def connected(self):
        if hasattr(self, "conn"):
            return self.conn.closed == 0
        return False

    def debug(self, func_name, params):
        if DEBUG:
            params_str = ''
            if None not in params:
                params_str = ''.join(["'", "','".join(params), "'"])

            print(f'begin; select {func_name} ({params_str});FETCH ALL IN "rcursor";END;')

    def connect(self):
        return pg2.connect(
            f"host = {self.host}  dbname={self.dbname} user={self.user} password={self.password} port={self.port}")

    def close(self):
        if self.connected():
            try:
                self.conn.close()
            except Exception as e:
                print(e)
        self.conn = None

    @classmethod
    def get_instance(cls):
        if not cls._instance:
            cls._instance = cls(
                "localhost",
                5432,
                "lawhub_test",
                "postgres",
                "12341234"
            )
        return cls._instance  # 인스턴스를 반환하도록 수정

    def _call(self, func_name, params, fetch=FETCH.FETCH_ONE, cursor_name=None):
        res = []
        conn = None
        try:
            # 데이터베이스 연결 및 커서 생성
            conn = self.connect()
            cur = conn.cursor(cursor_factory=psycopg2.extras.RealDictCursor)

            # 파라미터 설정
            # _params = ','.join([f"%s" for _ in params])
            _params = ','.join(
                [f"NULL" if i is None else f"'{str(i)}'" if isinstance(i, (int, str)) else f"'{json.dumps(i)}'" for i in
                 params])

            # 1. 함수 실행해서 커서를 반환받음
            cur.execute(f'SELECT {func_name}({_params});FETCH ALL {cursor_name};')

            # 2. 반환된 결과가 REF CURSOR일 경우 FETCH ALL 실행

            if fetch == FETCH.FETCH_ONE:
                return cur.fetchone()  # 한 개의 행만 가져오기
            else:
                res = cur.fetchall()  # 모든 데이터를 가져옴

            # 3. 커서 닫기

        except Exception as e:
            if conn:
                conn.rollback()  # 예외 발생 시 롤백
            print(f"Error occurred: {e}")

        finally:
            if conn:
                conn.commit()
                conn.close()

        return res

    def _execute(self, func_name, params):
        self.debug(func_name, params)
        conn = None
        try:
            with self.connect() as conn:
                cur = conn.cursor()
                cur.callproc(func_name, params)
                return cur.fetchone()[0]

        finally:
            if conn is not None:
                conn.commit()
                conn.close()
        return 0