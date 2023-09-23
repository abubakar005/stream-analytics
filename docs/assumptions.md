# Assumptions
During the development of the application, several assumptions were made to ensure the smooth implementation and functionality of the system. These assumptions are listed below:

1. **Technology Stack:** The solution has been implemented using Spring Boot, Java 17, Maven, WebFlux, and WebClient as per the provided preferences. The chosen stack aligns with the purpose of the application, ensuring efficient real-time data processing.

2. **Architecture:** The application follows a microservices architecture, with a focus on asynchronous and parallel processing using WebFlux. This architecture choice allows for high scalability and responsiveness.

3. **Streaming Endpoints:** The application assumes that the provided streaming endpoints are accessible and functional. It will actively consume data from these endpoints. Faulty/buggy data is disregarded if it occurs.

4. **Data Processing:** The application processes real-time streaming data from the endpoints, focusing on the specific requirements mentioned. Data processing includes filtering, aggregation, and transformation as needed.

5. **Concurrency:** Given the parallel nature of streaming data processing, the application efficiently utilizes multi-threading and parallel processing, enhancing performance.

6. **Testing:** The solution includes comprehensive unit tests using JUnit 5 to ensure the correctness of functionality. The primary aim is to achieve high test coverage, validating each component's behavior.

7. **Data Storage:** The solution does not require permanent data storage, as it focuses on real-time data processing. Therefore, it does not interact with databases or external data storage mechanisms.

8. **Swagger Documentation:** The application provides API documentation using Swagger (OpenAPI) to facilitate understanding and testing of endpoints.


These assumptions were made based on the available information and project requirements to ensure the successful development and deployment of the application. It is important to consider these assumptions while testing, maintaining, and further enhancing the system.