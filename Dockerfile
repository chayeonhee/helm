# 1. 경량화된 JDK 환경을 사용
FROM eclipse-temurin:21

RUN  mkdir -p /app
# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. JAR 파일을 컨테이너로 복사
COPY target/*.jar app.jar

# 외부노출 포트설정
# deploy, service의 targetport
# # EXPOSE 8182
# 4. 실행 명령
CMD ["java", "-jar", "app.jar"]