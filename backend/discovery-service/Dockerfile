FROM openjdk:17-jdk-slim
EXPOSE 8761
ADD target/discovery-service.jar discovery-service.jar
ENTRYPOINT ["java","-jar","/discovery-service.jar"]