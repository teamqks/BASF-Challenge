FROM openjdk:11

WORKDIR /app

COPY ./target/basf-coding-challenge-1.0-SNAPSHOT.jar .

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "basf-coding-challenge-1.0-SNAPSHOT.jar"]