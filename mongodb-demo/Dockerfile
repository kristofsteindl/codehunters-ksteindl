FROM --platform=linux/amd64 openjdk:17-alpine
COPY ./build/libs/mongodb-demo*.jar mongodb-demo.jar
EXPOSE 8080
ENTRYPOINT java -jar mongodb-demo.jar