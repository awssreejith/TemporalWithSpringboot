FROM openjdk:11-jre-slim
WORKDIR /usr/app/bin
COPY ./target/providerservice-1.0.0.jar /usr/app/bin
ENTRYPOINT ["java","-jar","providerservice-1.0.0.jar"]
