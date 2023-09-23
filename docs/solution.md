# **Streaming Analytics API Implementation Details**
This documentation provides an in-depth understanding of the Spring Boot project's implementation details for the Streaming Analytics API. The API is designed to collect and process streaming data from three different platforms, presenting the results collectively based on specified requirements.

- The Streaming Analytics API efficiently gathers data from three distinct streaming platforms, utilizing a parallelized approach through Webflux, allowing asynchronous data retrieval. 
- The API processes data either within a 20-second time frame or until the user's first name **'Sytec'** appears third time, whichever criterion is met first. 
- The collected data is organized by user, encompassing both individual and overall statistics.

## **API Endpoint(s)**
The following API endpoint is available for getting streams details:

1. <font size="4">User's Streaming Details:</font> **'GET /api/v1/stream/details'**

**Note:**
- The API endpoint is accessible on **'localhost:8082'** followed by the respective URL paths mentioned above.
- Additional endpoints are available via the Actuator library for accessing statistics and API health metrics.

## **Data Processing Workflow**
- **Data Collection:** The API simultaneously collects streaming data from all three platforms, maintaining a parallelized asynchronous approach.
- **Condition Termination:** Data collection halts either after 20 seconds or when a user with the first name 'Sytec' is encountered third time.
- **Data Processing:** Upon satisfying the termination condition, the collected data is processed on a per-user basis to generate individual and overall statistics.
- **Response Formation:** The API constructs a JSON response containing the processed data.

## **Returned Data Structure**
The API's response comprises the **UsersStreamDetail** object, encompassing the following elements:

- **totalShowsReleasedAfter2020:** Total count of shows released in or after the year 2020.
- **users:** A list of **UserDetail** objects, each containing:
  - User ID
  - First name
  - Last name
  - User's age
  - Total count of successful streaming events
  - **EventDetails** list, containing details of all executed events:
    - Event name
    - Show ID
    - Show title
    - Platform
    - First cast member (if present)
    - Event time in CET timezone (dd-MM-yyyy HH:mm:ss.SSS format)
      

  Additionally, the response includes the following:

- **streamConsumptionDurationMillis:** Time taken for streaming APIs consumption.
- **startedStreamEventPercentage:** Percentage of started events in relation to total events received on the **'Sytflix'** platform.

## **Additional Implementations**
- **Open API Documentation (Swagger):** Integrated for comprehensive API documentation.
- **Actuator Endpoints:** Exposes statistical endpoints, facilitating integration with **Prometheus**.
- **Grafana Integration:** Utilizes data from **Prometheus** to generate visual graphs in **Grafana**, enhancing data visualization.

## **Technology Stack**
The application is built using the following technologies:

- Spring Boot
- Lombok
- Java 17
- Maven
- Unit Testing
- Actuator
- Open API Documentation (Swagger)
- Docker
- Prometheus
- Grafana

I hope this documentation provides clarity on the inner workings of the Streaming Analytics API. For further details, refer to the project's source code and the integrated Swagger documentation.