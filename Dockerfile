# Use the official NGINX base image
FROM nginx

# Copy custom configuration file to NGINX configuration directory
COPY nginx.conf /etc/nginx/nginx.conf

# Copy static website files to NGINX web root directory
COPY website /usr/share/nginx/html

# Expose the default NGINX port
EXPOSE 80

# Start NGINX server in the foreground
CMD ["nginx", "-g", "daemon off;"]

