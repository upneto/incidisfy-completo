# Maven
FROM maven:3-jdk-11
WORKDIR /incidisfy-backend-reclamacao
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run