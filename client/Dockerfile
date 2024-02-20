# Use an official nginx image as a base
FROM nginx:alpine

# Copy custom nginx.conf
COPY nginx.conf ./nginx.conf

# Copy static resource to nginx server
COPY ./index.html /usr/share/nginx/html/index.html
COPY ./css /usr/share/nginx/html/css
COPY ./js /usr/share/nginx/html/js

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]

