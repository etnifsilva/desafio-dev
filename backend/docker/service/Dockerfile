FROM amazoncorretto:11-alpine3.15 as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM amazoncorretto:11-alpine3.15
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target
RUN mkdir -p app
WORKDIR /app
COPY --from=build ${DEPENDENCY}/*.jar app.jar
RUN ls
ENTRYPOINT ["java","-jar", "app.jar"]