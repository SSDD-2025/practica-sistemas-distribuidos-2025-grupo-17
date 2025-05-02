#Base image
FROM maven:3.9.9-eclipse-temurin-21-jammy AS builder

#Directory
WORKDIR /project

#Copy dependencies
COPY pom.xml /project/

# Download dependencies
RUN mvn dependency:go-offline

# Copy project code
COPY /src /project/src

# Compile
RUN mvn clean package -DskipTests

#Imagen del contenedor
FROM eclipse-temurin:21-jre

# JAR directory
WORKDIR /usr/src/app/

# Copy JAR
COPY --from=builder /project/target/practica_grupo17-1.0.0.jar /usr/src/app/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]

EXPOSE 8443

CMD ["--server"]