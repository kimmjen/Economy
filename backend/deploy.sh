#!/bin/bash

# 스크립트 중 에러 발생 시 중단
set -e

# Frontend 빌드
echo "Starting frontend build..."
cd ../front

# npm install 및 build
npm install
npm run build

echo "Frontend build complete."

# Backend 빌드 준비
echo "Starting backend build..."
cd ../backend

# 기존 build 디렉토리 삭제
echo "Cleaning previous build..."
rm -rf build

# Gradle 빌드
./gradlew build

echo "Backend build complete."

# Docker-compose를 사용하여 서비스 실행
echo "Starting Docker containers..."
#docker compose down   # 기존 컨테이너 종료
#docker compose up -d   # 새로운 컨테이너 시작

docker buildx build --no-cache --platform linux/amd64 -t wpals814/economy:v1.0.0 .

docker push wpals814/economy:v1.0.0

echo "Deployment complete."