# Maven
FROM maven:3-jdk-11
WORKDIR /incidisfy-backend-categoria
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run