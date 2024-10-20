import sqlalchemy
from sqlalchemy.ext.asyncio import AsyncEngine, create_async_engine
from contextlib import asynccontextmanager
from threading import Lock


class SingletonMeta(type):
    _instances = {}
    _lock = Lock()  # To prevent race conditions in multi-threaded environments

    def __call__(cls, *args, **kwargs):
        with cls._lock:
            if cls not in cls._instances:
                instance = super().__call__(*args, **kwargs)
                cls._instances[cls] = instance
        return cls._instances[cls]


from sqlalchemy import text

class CONN(metaclass=SingletonMeta):
    def __init__(self):
        user = "postgres"
        password = "12341234"
        host = "localhost"
        port = "5432"
        dbname = "lawhub_test"
        self.url = f'postgresql+asyncpg://{user}:{password}@{host}:{port}/{dbname}'
        self._engine = create_async_engine(self.url, echo=True)
        self._metadata = sqlalchemy.MetaData()

    @asynccontextmanager
    async def get_connection(self):
        async with self._engine.connect() as conn:
            yield conn

    async def _call(self, func_name, params):
        query = text(f"SELECT * FROM {func_name}(:param1, :param2)")
        async with self.get_connection() as conn:
            result = await conn.execute(query, {"param1": params[0], "param2": params[1]})
            return result.fetchall()

    async def _execute(self, func_name, params):
        query = text(f"CALL {func_name}(:param1, :param2)")
        async with self.get_connection() as conn:
            await conn.execute(query, {"param1": params[0], "param2": params[1]})
            await conn.commit()

    async def disconnect(self):
        if self._engine:
            await self._engine.dispose()

    def get_metadata(self):
        return self._metadata

    def get_engine(self):
        return self._engine

    @classmethod
    async def close(cls):
        if cls._engine:
            await cls._engine.dispose()

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        pass
