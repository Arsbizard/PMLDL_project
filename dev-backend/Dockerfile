FROM gradle:8.5-jdk17 as builder
USER root
WORKDIR /builder
COPY . /builder
RUN gradle build --stacktrace -x test --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8000
COPY --from=builder /builder/build/libs/SafeShelf-0.0.1-SNAPSHOT.jar .
COPY --from=builder /builder/src/main/resources /app/resources
USER nobody
CMD ["java", "-jar", "SafeShelf-0.0.1-SNAPSHOT.jar"]
