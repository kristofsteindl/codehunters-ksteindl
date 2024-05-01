FROM --platform=linux/amd64 openjdk:17-alpine
COPY ./build/libs/service-discovery*SNAPSHOT.jar service-discovery.jar
EXPOSE 8080
ENTRYPOINT java -jar service-discovery.jar