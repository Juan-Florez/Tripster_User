# Utiliza una imagen base de OpenJDK 20.0.2 (ajusta la etiqueta según tu versión)
FROM openjdk:22-jdk

# Directorio de trabajo en el contenedor
WORKDIR /opt

# Copia tu archivo JAR al contenedor (ajusta la ubicación si es necesario)
ADD target/tripster_user*.jar /opt/tripster_user_ms.jar

# Debugging commands:
RUN ls /opt/ # Verify that the file is in the target directory
RUN echo "Hello, World!" > /opt/test.txt # Write a test file to the target directory to check for permission issues
RUN cat /opt/test.txt # Verify that the test file was written successfully


# Expone el puerto en el que se ejecuta la aplicación (ajusta según tu configuración)
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "/opt/tripster_user_ms.jar"]