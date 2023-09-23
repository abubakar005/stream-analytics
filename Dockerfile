FROM openjdk:17
MAINTAINER Abubakar

# Refer to Maven build
ARG JAR_FILE=target/stream-analytics-0.0.1-SNAPSHOT.jar

# set working directory
WORKDIR /opt/app

# copy jar file to the deirectory /opt/app/
COPY ${JAR_FILE} stream-analytics.jar

# Run project container
ENTRYPOINT ["java","-jar","stream-analytics.jar"]