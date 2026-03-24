# Estágio de Build
FROM openjdk:21-jdk AS build
WORKDIR /app

# CRUCIAL: Copiar os arquivos do Maven Wrapper e o pom.xml primeiro
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Garante permissão de execução
RUN chmod +x mvnw

# Copia o código fonte
COPY src src

# Executa o build (o wrapper vai baixar o Maven automaticamente se necessário)
RUN ./mvnw clean package -DskipTests

# Estágio Final (Execução)
FROM openjdk:21-jdk-slim
WORKDIR /app
VOLUME /tmp

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Configuração para o Render (Usa a porta que o Render fornecer ou 8080 como padrão)
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]