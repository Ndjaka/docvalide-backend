FROM maven:3.8.3-jdk-11-slim AS build

RUN mkdir /project

COPY . /project

WORKDIR /project

RUN mvn clean package

FROM  adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9

RUN mkdir /app

RUN addgroup -g 1001 -S ghosttechgroup

RUN adduser -S ghosttech -u 1001

COPY --from=build /project/target/docvalide-backend-0.0.1-SNAPSHOT.jar /app/docvalide-api.jar

WORKDIR /app

RUN chown -R ghosttech:ghosttechgroup /app

CMD java $JAVA_OPTS -jar bmi.jar