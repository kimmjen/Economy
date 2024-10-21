# 베이스 이미지로 Node.js 사용
FROM node:18-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# package.json과 package-lock.json 복사
COPY package*.json ./

# 종속성 설치
RUN npm install

# 소스 코드 복사
COPY . .

# 프론트엔드 애플리케이션 빌드
RUN npm run build

# Nginx를 사용하여 정적 파일 서빙
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]