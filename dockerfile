FROM maven:3.6.3-openjdk-17
ARG JAR_FILE=target/loadtestservice-1.0-SNAPSHOT.jar
WORKDIR test
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]