FROM amazoncorretto:17

WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/customer-service-0.0.1-SNAPSHOT.jar /app/

# Specify the command to run on container start
CMD ["java", "-jar", "customer-service-0.0.1-SNAPSHOT.jar"]