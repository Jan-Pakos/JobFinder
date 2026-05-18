# --- Build stage: compile the JAR inside the image ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw -B -q dependency:go-offline
COPY src/ src/
RUN ./mvnw -B -q clean package -DskipTests

# --- Runtime stage: minimal JRE with just the built JAR ---
FROM eclipse-temurin:21-jre
COPY --from=build /workspace/target/jobfinder.jar /jobfinder.jar
ENTRYPOINT ["java","-jar","/jobfinder.jar"]
