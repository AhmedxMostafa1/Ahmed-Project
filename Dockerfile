FROM ubuntu:22.04

# Install OpenJDK, dependencies, and Xvfb
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    unzip \
    fonts-dejavu \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

# Download and install JavaFX SDK
WORKDIR /tmp
RUN wget https://download2.gluonhq.com/openjfx/20.0.2/openjfx-20.0.2_linux-x64_bin-sdk.zip \
    && unzip openjfx-20.0.2_linux-x64_bin-sdk.zip \
    && mv javafx-sdk-20.0.2 /opt/javafx \
    && rm openjfx-20.0.2_linux-x64_bin-sdk.zip

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Compile the Java code with JavaFX module path
RUN javac --module-path /opt/javafx/lib --add-modules javafx.controls,javafx.fxml -d out *.java

# Expose the port that your server will run on
EXPOSE 18866

# Set up Xvfb and run your JavaFX application
CMD ["sh", "-c", "Xvfb :99 -screen 0 1024x768x24 & export DISPLAY=:99 && java --module-path /opt/javafx/lib --add-modules javafx.controls,javafx.fxml -cp out ChatServer.java"]
