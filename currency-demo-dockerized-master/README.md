# currency-demo-dockerized
# Build the application


mvn clean package docker:build

# Running the Application 

docker-compose -f docker/common/docker-compose.yml up
