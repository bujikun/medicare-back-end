FROM --platform=linux/amd64 eclipse-temurin:20.0.2_9-jdk-jammy as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:20.0.2_9-jre-jammy
RUN apt-get update -y && apt-get upgrade -y
RUN addgroup spring-api-group; adduser --ingroup spring-api-group --disabled-password spring-api-user
USER spring-api-user
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java","-jar","/opt/app/*.jar"]