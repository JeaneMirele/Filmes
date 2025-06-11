FROM ubuntu:latest
LABEL authors="Jeane"
# Etapa de build: usa o OpenJDK 24 para compilar o projeto com Maven Wrapper
FROM openjdk:21-jdk AS build
# Define o diretório de trabalho dentro do contêiner
WORKDIR /app
# Copia o arquivo de configuração do Maven (pom.xml) para o diretório de trabalho
COPY pom.xml .
# Copia o diretório com o código-fonte da aplicação
COPY src src
COPY mvnw .
COPY .mvn .mvn
# Dá permissão de execução ao script mvnw
RUN chmod +x ./mvnw
# Executa o build da aplicação, pulando os testes
RUN ./mvnw clean package -DskipTests
# Etapa final: cria a imagem definitiva com a aplicação empacotada com OpenJDK 24 Slim
FROM openjdk:21-jdk-slim
# Cria um volume temporário para arquivos que possam ser escritos pela aplicação
VOLUME /tmp
# Copia o arquivo .jar gerado na etapa de build para o contêiner final
COPY --from=build /app/target/*.jar app.jar
# Define o ponto de entrada para executar a aplicação Java
ENTRYPOINT ["java", "-jar", "/app.jar"]
# Expõe a porta 8080 para acesso externo à aplicação
EXPOSE 8080