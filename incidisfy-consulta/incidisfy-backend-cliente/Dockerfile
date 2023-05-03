# Maven
FROM maven:3-jdk-11
WORKDIR /incidisfy-backend-cliente
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run