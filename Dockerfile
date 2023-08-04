FROM eclipse-temurin:20.0.2_9-jdk-alpine as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:20.0.2_9-jre-alpine
RUN addgroup spring-api-group; adduser --ingroup spring-api-group --disabled-password spring-api-user
USER spring-api-user
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java","-jar","/opt/app/*.jar"]