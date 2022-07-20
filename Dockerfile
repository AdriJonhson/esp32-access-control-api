from openjdk:11 -alpine

COPY ./target/acesscontrol-0.0.1-SNAPSHOT.jar /app/acesscontrol-0.0.1-SNAPSHOT.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "acesscontrol-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080