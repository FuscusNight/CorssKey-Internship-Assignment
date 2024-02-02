
FROM openjdk:17


WORKDIR /app


COPY ./target/MortageCalculator-CrossKeyLucian-1.0-SNAPSHOT.jar /app/MortageCalculator.jar
COPY ./prospects.txt /app/prospects.txt

EXPOSE 80


CMD ["java", "-jar", "MortageCalculator.jar"]
