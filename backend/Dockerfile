# 1. Base image: Java 17 지원하는 JDK 이미지 사용
FROM openjdk:17-jdk-slim

# 2. JAR 파일을 build 디렉토리에서 복사
ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 3. JAR 파일을 실행
ENTRYPOINT ["java","-jar","/app.jar"]

# 4. 기본적으로 8080 포트를 사용하므로 이를 외부에 노출
EXPOSE 8080
