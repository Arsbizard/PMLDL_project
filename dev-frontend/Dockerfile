# Use the official Node.js image as the base image
FROM node:18-alpine as build

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install dependencies
RUN npm install --legacy-peer-deps

# Copy the rest of the application code into the container
COPY . .

# Build the React app for production
RUN npm run build

# Use a lightweight web server to serve the static files
FROM nginx:1.25-alpine

# Copy the built React files to the nginx html directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
