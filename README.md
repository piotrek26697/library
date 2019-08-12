# Build
mvn clean package && docker build -t lbd/library .

# RUN

docker rm -f library || true && docker run -d -p 8080:8080 -p 4848:4848 --name library lbd/library 