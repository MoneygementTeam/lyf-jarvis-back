FROM openjdk:17-jdk-slim

# 애플리케이션 JAR 파일 복사
COPY build/libs/lyf-jarvis-back-0.0.1.jar app.jar

# 환경 변수 설정
ENV JAVA_OPTS="-Dspring.profiles.active=prod"

# 애플리케이션 실행
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
