FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y nginx


COPY entrypoint.sh /entrypoint.sh
# Copy a custom NGINX configuration
COPY nginx.conf /etc/nginx/nginx.conf
COPY nginx.high.conf /etc/nginx/nginx.high.conf

COPY website /usr/share/nginx/html

COPY dynamic-throttling-web-api/target/demo-0.0.1-SNAPSHOT.jar /demo.jar
EXPOSE 80
EXPOSE 8080

# Start NGINX and keep the container running
CMD ["bash", "/entrypoint.sh"]

