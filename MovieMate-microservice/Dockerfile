## Use a base image with Java installed
#FROM adoptopenjdk:11-jdk-hotspot
#
## Set the working directory
#WORKDIR /app
#
## Copy the source code
#COPY . .
#
## Build the project with Gradle
#RUN ./gradlew build
#
#EXPOSE 8700
#
## Run the application
#CMD ["java", "-jar", "./build/libs/MovieMate-Application-0.0.1-SNAPSHOT.jar"]

FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE=build/libs/MovieMate-Application-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} moviemate-app.jar

EXPOSE 8700

ENTRYPOINT ["java","-jar","moviemate-app.jar"]


