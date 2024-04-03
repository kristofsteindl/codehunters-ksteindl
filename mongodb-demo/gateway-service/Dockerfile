FROM openjdk:17-alpine
COPY ./build/libs/gateway-service*SNAPSHOT.jar gateway-service.jar
EXPOSE 8080
ENTRYPOINT java -jar gateway-service.jar