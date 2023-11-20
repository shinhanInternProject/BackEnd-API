# Java 17을 사용하는 Alpine Linux 기반의 OpenJDK 이미지를 기본 이미지로 사용합니다.
FROM openjdk:17-alpine

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# 빌드 파일을 컨테이너에 복사합니다.
# Gradle 빌드 시 생성되는 JAR 파일의 경로를 확인하고, 해당 경로를 사용하세요.
COPY build/libs/*.jar app.jar

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java","-jar","app.jar"]
