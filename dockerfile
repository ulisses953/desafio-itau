# Usar uma imagem base do OpenJDK
FROM openjdk:21-jdk-slim

# Configurar diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado pela aplicação para o contêiner
COPY target/itau-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta que a aplicação utiliza
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
