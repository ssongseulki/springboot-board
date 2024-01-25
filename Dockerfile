#자바 버전
FROM openjdk:11-jdk
#JAR_FILE이라는 변수를 정의해서 .jar 위치 저장
ARG JAR_FILE=build/libs/board-spring-0.0.1-SNAPSHOT.jar
#board-spring-0.0.1-SNAPSHOT.jar 이름을 도커에서는 docker-springboot 로 출력되도록 변경
ADD ${JAR_FILE} docker-springboot.jar
#컨테이너 생성 시 환경변수에 대한 인수 전달
ENV ACCESS_KEY=${ACCESS_KEY}
ENV SECRET_KEY=${SECRET_KEY}
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENTRYPOINT ["java","-jar","/docker-springboot.jar"]
