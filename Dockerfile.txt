# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Install necessary dependencies for JavaFX
RUN apk add --no-cache \
    fontconfig \
    ttf-dejavu \
    openjfx

# Copy the current directory contents into the container at /app
COPY . /app

# Expose the port that your server will run on
EXPOSE 18866

# Command to run the application
CMD ["java", "-cp", "src", "ServerSide.ChatServer"]
