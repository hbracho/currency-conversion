FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN mkdir -m 777 logs
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#COPY dd-java-agent.jar dd-java-agent.jar
COPY opentelemetry-javaagent-all.jar opentelemetry-javaagent-all.jar
#ENTRYPOINT ["java","-javaagent:/dd-java-agent.jar","-jar","/app.jar"] "-Dotel.javaagent.debug=true",
ENTRYPOINT ["java","-javaagent:/opentelemetry-javaagent-all.jar","-Dotel.resource.attributes=service.name=currency-conversion-service","-jar","/app.jar"]

