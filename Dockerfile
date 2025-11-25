


FROM eclipse-temurin:21-jdk AS build
WORKDIR /app


COPY pom.xml .
COPY src ./src


RUN apt-get update && apt-get install -y maven


RUN mvn clean package -DskipTests




FROM tomcat:11.0.14-jdk21


RUN rm -rf /usr/local/tomcat/webapps/*


COPY --from=build /app/target/teleconsulta.war /usr/local/tomcat/webapps/ROOT.war


ENV DB_HOST=database \
    DB_PORT=5432 \
    DB_NAME=teleconsulta \
    DB_USER=postgres \
    DB_PASS=postgres


ENV CATALINA_OPTS="$CATALINA_OPTS \
    -DDB_HOST=${DB_HOST} \
    -DDB_PORT=${DB_PORT} \
    -DDB_NAME=${DB_NAME} \
    -DDB_USER=${DB_USER} \
    -DDB_PASS=${DB_PASS}"

EXPOSE 8080

CMD ["catalina.sh", "run"]
