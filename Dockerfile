FROM eclipse-termurin:21-jre-alpine
COPY target/jobfinder.jar /jobfinder.jar
ENTRYPOINT ["java","-jar","/jobfinder.jar"]