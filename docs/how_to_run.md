# **Running the Application**
To run the application, please follow the step-by-step instructions provided below. Ensure that you have a compatible Java version (Java 17) installed on your system.

## 1. Set Java Version
Set the Java version to 17 for this application. Make sure you have Java 17 installed and configured properly on your system.

## 2. Starting the Application
To start the application, you have two options:

### Option 1: Using an Integrated Development Environment (IDE)

1. Open your preferred integrated development environment (IDE) such as Eclipse or IntelliJ (recommended).
2. Import the project as a Maven project. The IDE will automatically download the required dependencies.
3. Locate the main application class **StreamAnalyticsApplication.java** within the project.
4. Run the main method of this class to start the application.

### Option 2: Using Docker (Optional)
If you prefer to use Docker for running the application, follow these steps:

1. Ensure that **Docker** is installed and running on your system.
2. Open a terminal or command prompt and navigate to the project's root directory where the **Dockerfile** is located.
3. Build a Docker image using the following command:
```
    docker build -t stream-analytics-application .
```
4. Run a Docker container using the created image with the following command:
```
    docker run -p 8082:8082 stream-analytics-application
```

Replace 8082 with your desired port number if needed.

## 3. Setting Up Prometheus and Grafana (Optional)
To view the application's statistics, Prometheus and Grafana need to be configured. A **docker-compose.yml** file is provided in the project's root directory to streamline the setup process.

Follow these steps to configure Prometheus and Grafana:

### 1. Prometheus Configuration:
- Open the **prometheus.yml** file in the root directory and configure it as needed.
- Update the targets with the IP address of the system where the application is running, like targets: ['192.168.0.106:8082'].

### 2. Grafana Configuration:
- Navigate to the Grafana folder in the root directory.
- Configure the **datasources.yml** file to specify data source details, including Prometheus.
- Adjust **admin** user and password in the **docker-compose.yml** file.

### 3. Starting Prometheus and Grafana:
- Open a terminal or command prompt and navigate to the project's root directory.
- Run the command **docker-compose up** to start both containers.
- Access **Prometheus** at http://localhost:9090/ and **Grafana** at http://localhost:3000/.

For Grafana:
- Log in using the provided credentials from the Docker Compose file.
- Import the dashboard template from the **Grafana_Dashboard.json** file.
- Set up the data source using the URL http://prometheus:9090.
- Analyze the data as needed.

**Useful URLs:**
- http://localhost:8082/api/v1/stream/details [API Endpoint]
- http://localhost:8082/swagger-ui/index.html [Swagger]
- http://localhost:8082/actuator/health [Health Check]

Congratulations! You have now successfully set up and launched the application. You can proceed to interact with the API and explore its features.