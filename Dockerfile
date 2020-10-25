FROM openjdk:11
WORKDIR /home
COPY /target/csv_test-0.0.1-SNAPSHOT.jar spring-DE-falabella.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-DE-falabella.jar"]