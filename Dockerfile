# Use an official Ubuntu image with OpenJDK 17
FROM ubuntu:22.04

# Install OpenJDK and JavaFX
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    openjfx \
    fonts-dejavu \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Compile the Java code
RUN javac -d out ChatServer.java Database/UserDatabase.java

# Expose the port that your server will run on
EXPOSE 18866

# Command to run the application
CMD ["java", "-cp", "out", "ServerSide.ChatServer"]
