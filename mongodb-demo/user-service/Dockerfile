FROM --platform=linux/amd64 openjdk:17-alpine
COPY ./build/libs/user-service*SNAPSHOT.jar user-service.jar
EXPOSE 8080
ENTRYPOINT sleep 60 && java -jar user-service.jar