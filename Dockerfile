FROM amazoncorretto:17-alpine-jdk

VOLUME /tmp

ARG JAR_FILE=target/docvalide-backend.jar

COPY ${JAR_FILE} docvalide-backend.jar

COPY entrypoint.sh entrypoint.sh

RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]