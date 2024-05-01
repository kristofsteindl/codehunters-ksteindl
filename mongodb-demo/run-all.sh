docker rmi mongodb-demo-user-service --force
docker rmi mongodb-demo-service-discovery --force
docker rmi mongodb-demo-gateway-service --force
docker rmi mongodb-demo-external-configuration-service --force

./gradlew clean assemble

docker compose up

