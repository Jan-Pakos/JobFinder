FROM eclipse-temurin:21
COPY target/jobfinder.jar /jobfinder.jar
ENTRYPOINT ["java","-jar","/jobfinder.jar"]