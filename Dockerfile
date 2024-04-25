FROM gradle:8.2.1-jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM amazoncorretto:20.0.2

COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar /app/carservicebook.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/carservicebook.jar"]