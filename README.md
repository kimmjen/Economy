## 프로젝트 기획 배경

이 프로젝트는 유튜버 **오선**님의 **미국증시라이브** 방송에서 영감을 받아 기획되었습니다. 미국 주식 시장과 관련된 실시간 데이터를 제공하여 사용자들이 더 나은 투자 결정을 내릴 수 있도록 돕는 것을 목표로 하고 있습니다.

# Economy Dashboard



https://github.com/user-attachments/assets/d59151c0-4242-4a38-805a-db92a378ce59



미국 주식 지수, 채권, 달러 지수, 금, 에너지 가격에 대한 최신 정보를 제공하는 금융 데이터 대시보드입니다.


## 목차

- [Economy Dashboard](#economy-dashboard)
  - [목차](#목차)
  - [구성](#구성)
  - [기술 스택](#기술-스택)
  - [기능](#기능)



## 구성

```
Economy/
│
├── backend/                         # 백엔드 Spring Boot 애플리케이션
│   ├── .gradle/                     # Gradle 관련 설정 파일
│   ├── build/                       # 빌드 아티팩트 저장 디렉토리
│   ├── gradle/                      # Gradle 설정 파일
│   ├── src/
│   │   └── main/
│   │       └── java/hello/backend/  # Java 소스 디렉토리
│   │           ├── config/          # 설정 관련 클래스
│   │           ├── controller/      # API 컨트롤러 클래스
│   │           ├── dto/             # Data Transfer Object 클래스
│   │           ├── entity/          # 엔티티 클래스
│   │           ├── exception/       # 예외 처리 클래스
│   │           ├── repository/      # 데이터베이스 관련 클래스 (JPA Repository)
│   │           ├── services/        # 비즈니스 로직 서비스 클래스
│   │           └── BackendApplication.java # 애플리케이션 진입점
│   ├── resources/                   # 리소스 파일 (설정 파일, 템플릿 등)
│   ├── test/                        # 테스트 관련 코드
│   ├── build.gradle                 # Gradle 설정 파일
│   ├── docker-compose.yml           # Docker Compose 설정 파일
│   ├── Dockerfile                   # Docker 빌드 파일
│   ├── gradlew                      # Gradle 래퍼 스크립트
│   ├── gradlew.bat                  # Windows용 Gradle 래퍼 스크립트
│   ├── settings.gradle              # 프로젝트의 Gradle 설정 파일
│   └── .gitignore                   # Git에서 추적하지 않을 파일 목록
├── data-fastapi/                     # FastAPI 애플리케이션
│   ├── .idea/                        # IDE 설정 파일
│   ├── .venv/                        # 가상 환경 디렉터리
│   ├── src/                          # 소스 코드 디렉터리
│   │   ├── api/                      # API 라우트 핸들러
│   │   ├── core/                     # 핵심 로직 및 설정
│   │   ├── dao/                      # 데이터 접근 계층 (Data Access Object)
│   │   ├── db/                       # 데이터베이스 연결 및 설정
│   │   ├── service/                  # 서비스 로직
│   │   ├── util/                     # 유틸리티 함수 및 도구
│   │   └── __init__.py               # 패키지 초기화 파일
│   ├── main.py                       # FastAPI 애플리케이션 진입점
│   ├── .gitignore                    # Git에서 추적하지 않을 파일 목록
│   ├── requirements.txt              # 종속성 목록
│   └── test_main.http                # HTTP 테스트 파일
├── front/                            # 프론트엔드 React 애플리케이션
│   ├── .idea/                        # IDE 설정 파일
│   ├── node_modules/                 # npm 패키지 저장 디렉토리
│   ├── public/                       # 정적 파일 디렉터리
│   ├── src/                          # 소스 코드 디렉터리
│   │   ├── api/                      # API 요청 관련 로직
│   │   ├── assets/                   # 이미지 및 기타 정적 자산
│   │   ├── components/               # React 컴포넌트
│   │   ├── context/                  # 전역 상태 관리 (Context API)
│   │   ├── data/                     # 데이터 관련 로직
│   │   ├── i18n/                     # 다국어 지원 (Internationalization)
│   │   ├── layouts/                  # 레이아웃 관련 컴포넌트
│   │   ├── pages/                    # 각 페이지별 컴포넌트
│   │   ├── routes/                   # 라우트 정의
│   │   ├── widgets/                  # 재사용 가능한 위젯 컴포넌트
│   │   ├── App.css                   # 글로벌 스타일시트
│   │   ├── App.jsx                   # 메인 애플리케이션 컴포넌트
│   │   ├── index.css                 # 인덱스 스타일시트
│   │   └── main.jsx                  # 애플리케이션 진입점
│   ├── .gitignore                    # Git에서 추적하지 않을 파일 목록
│   ├── eslint.config.js              # ESLint 설정 파일
│   ├── index.html                    # HTML 템플릿 파일
│   ├── package-lock.json             # 종속성 잠금 파일
│   ├── package.json                  # 프로젝트 종속성 관리 파일
│   ├── postcss.config.cjs            # PostCSS 설정 파일
│   ├── prettier.config.cjs           # Prettier 코드 스타일 설정 파일
│   ├── README.md                     # 프로젝트 설명 파일
│   ├── tailwind.config.cjs           # Tailwind CSS 설정 파일
│   └── vite.config.js                # Vite 번들러 설정 파일
└── SQL/                             # 데이터베이스 관련 SQL 스크립트 디렉터리
    └── doc/                         # SQL 문서 및 스크립트 파일
        ├── content.sql              # 콘텐츠 관련 SQL 스크립트
        ├── insert_function.sql      # 데이터 삽입 기능 관련 SQL 스크립트
        ├── list_by_date_range.sql   # 날짜 범위에 따른 데이터 조회 SQL 스크립트
        └── table.sql                # 테이블 생성 SQL 스크립트
```

- **Backend**: 
  - **Java 17**: 프로젝트의 기본 언어 버전으로 사용
  - **Spring Boot 3.3.4**: 최신 Spring Boot 버전 사용
  - **Lombok**: 편리한 코드 작성 지원
  - **PostgreSQL**: 데이터베이스로 사용
  - **JJWT**: JWT(JSON Web Token) 인증을 위한 라이브러리
  - **SpringDoc OpenAPI**: API 문서 자동 생성
- **Data API**: 
  - Python 3.11, FastAPI 사용
  - `yfinance` 라이브러리를 이용하여 금융 데이터를 수집 및 가공
- **Frontend**: 
  - React.js 기반
  - [Material Tailwind Dashboard React](https://github.com/creativetimofficial/material-tailwind-dashboard-react) 프로젝트를 따라 개발

## 기술 스택

- **Backend**: Java 17, Spring Boot 3.*, Lombok, PostgreSQL
- **Data API**: Python 3.11, FastAPI, yfinance
- **Frontend**: React.js, Material Tailwind

## 기능

- 다음과 같은 하루 전 금융 데이터를 제공합니다:
  - 미국 주요 주식 지수:
    - Nasdaq 100
    - S&P 500
    - Dow Jones Industrial Average
    - Russell 2000
  - 미국 채권: 2년물 국채 수익률, 10년물 국채 수익률
  - 달러 지수
  - 금 가격
  - 에너지 가격
- 오선님의 라이브 때, 기사 요약문(추가 예정)
- 매 시간마다 뉴스기사 크롤링(추후 예정)
- 직관적이고 간단한 UI로 데이터 접근이 용이함
- 확장성과 모듈화를 고려한 백엔드 API 구조
