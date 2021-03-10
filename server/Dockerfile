#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml -B dependency:resolve dependency:resolve-plugins
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package
CMD mvn -f /home/app/pom.xml spring-boot:run
