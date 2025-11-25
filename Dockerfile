# ============================================================
# ETAPA 1 — Build do WAR
# ============================================================
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copia o projeto Maven
COPY pom.xml .
COPY src ./src

# Instala Maven na imagem
RUN apt-get update && apt-get install -y maven

# Compila e empacota o WAR
RUN mvn clean package -DskipTests

# ============================================================
# ETAPA 2 — Tomcat com WAR e variáveis configuradas
# ============================================================
FROM tomcat:11.0.14-jdk21

# Remove apps padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR compilado para o Tomcat
COPY --from=build /app/target/teleconsulta.war /usr/local/tomcat/webapps/ROOT.war

# Variáveis de ambiente que serão passadas ao Tomcat via -D
ENV DB_HOST=database \
    DB_PORT=5432 \
    DB_NAME=teleconsulta \
    DB_USER=postgres \
    DB_PASS=postgres

# Passa variáveis para o Tomcat como System Properties
ENV CATALINA_OPTS="$CATALINA_OPTS \
    -DDB_HOST=${DB_HOST} \
    -DDB_PORT=${DB_PORT} \
    -DDB_NAME=${DB_NAME} \
    -DDB_USER=${DB_USER} \
    -DDB_PASS=${DB_PASS}"

EXPOSE 8080

CMD ["catalina.sh", "run"]
