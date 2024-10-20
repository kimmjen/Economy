# from pydantic import model_validator
# from pydantic.v1 import BaseSettings
# from pydantic_settings import SettingsConfigDict

from pydantic import PostgresDsn, model_validator
from pydantic_settings import BaseSettings, SettingsConfigDict

class Settings(BaseSettings):
    HOST: str = '0.0.0.0'
    PORT: int = 8080

    @model_validator(mode="after")
    def validator(cls, values: "Settings") -> "Settings":
        values.SQLALCHEMY_DATABASE_URI = (
            f"postgresql://{values.emulators_user}:{values.emulators_password}@"
            f"{values.emulators_host}:{values.emulators_port}/{values.emulators_dbname}"
        )
        return values

    model_config = SettingsConfigDict(env_file=".env")