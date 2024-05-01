FROM --platform=linux/amd64 openjdk:17-alpine
COPY ./build/libs/gateway-service*SNAPSHOT.jar gateway-service.jar
EXPOSE 8080
ENTRYPOINT sleep 50 && java -jar gateway-service.jar