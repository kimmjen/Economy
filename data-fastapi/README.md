#### 2. data-fastapi
```markdown
# Data API (FastAPI)

## 설명
이 디렉터리는 Python 3.11과 FastAPI를 사용한 금융 데이터 제공 API입니다. `yfinance` 라이브러리를 통해 주식 및 금융 데이터를 하루 전 데이터를 기준으로 수집합니다.

## 주요 기술 스택
- Python 3.11
- FastAPI
- yfinance
- PostgreSQL

## 설정 및 실행 방법
1. 가상 환경 설정 및 종속성 설치:

```bash
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

## 패키지 목록 추출

```bash
pip list --format=freeze > requirements.txt
```

## 경로 문제시
```bash
export PYTHONPATH="절대경로/Economy/data-fastapi/src"
```
