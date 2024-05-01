FROM --platform=linux/amd64 openjdk:17-alpine
RUN cd /root && mkdir .ssh
COPY ./build/libs/*config*.jar configserver.jar
RUN apk update; apk --no-cache add curl; apk add openssh; apk add git
EXPOSE 8080
ENTRYPOINT java -jar configserver.jar