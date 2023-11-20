# 기본 이미지로 Java를 사용합니다.
FROM openjdk:8-jdk-alpine

# 애플리케이션 파일을 컨테이너 내부로 복사합니다.
COPY build/libs/app.jar app.jar

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java","-jar","/app.jar"]
