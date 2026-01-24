FROM eclipse-temurin:21
COPY target/JobFinder-0.0.1-SNAPSHOT.jar /JobFinder.jar
ENTRYPOINT ["java","-jar","/JobFinder.jar"]