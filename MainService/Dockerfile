FROM openjdk:11-jre-slim
WORKDIR /usr/app/bin
COPY ./target/mainservice-1.0.0.jar /usr/app/bin
ENTRYPOINT ["java","-jar","mainservice-1.0.0.jar"]
