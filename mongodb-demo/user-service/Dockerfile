FROM openjdk:17-alpine
COPY ./build/libs/user-service*SNAPSHOT.jar user-service.jar
EXPOSE 8080
ENTRYPOINT java -jar user-service.jar