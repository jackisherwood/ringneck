FROM openjdk:8u181-alpine3.8

WORKDIR /

COPY target/ringneck-1.0.0-standalone.jar ringneck.jar

CMD java -jar ringneck.jar
