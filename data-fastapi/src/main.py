import logging
from fastapi import FastAPI
from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.triggers.interval import IntervalTrigger
from contextlib import asynccontextmanager
from util.ticker_processor import main as ticker_processing_main  # ticker_processor에서 main 함수 가져오기

# 로깅 설정
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

# APScheduler 스케줄러 인스턴스 생성
scheduler = BackgroundScheduler()


# 주기적으로 실행할 작업 정의 (ticker_processor의 main() 함수 실행)
def job():
    logger.info("Scheduled job is running...")
    ticker_processing_main()


# FastAPI 애플리케이션 시작 시 스케줄러 시작
def startup_event():
    # 10초마다 실행되는 작업 추가
    scheduler.add_job(job, trigger=IntervalTrigger(seconds=10))
    scheduler.start()


# FastAPI 애플리케이션 종료 시 스케줄러 종료
def shutdown_event():
    scheduler.shutdown()


def start():
    print("service is started.")


def shutdown():
    print("service is stopped.")

@asynccontextmanager
async def lifespan(app: FastAPI):
    # When service starts.
    start()

    yield

    # When service is stopped.
    shutdown()
    # 애플리케이션이 시작될 때 실행할 작업
    logger.info("Scheduler started")

    startup_event()

    yield  # 이 지점에서 FastAPI 서버가 작동합니다.

    # 애플리케이션이 종료될 때 실행할 작업
    shutdown_event()
    logger.info("Scheduler stopped")


app = FastAPI(lifespan=lifespan)


# FastAPI 경로 설정
@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}
