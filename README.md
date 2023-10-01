# Tripster_user_ms
Microservicio de usuarios 

# Microservicio Gestión de usuarios
Este microservicio ofrece las funcionalidades para la creación, edición, visualización y eliminación de usuarios.

# Requisitos previos
Es necesario tener instalado Docker

# Pasos para ejecutar el programa

1. Descargar este repositorio (.zip)
   
2. Extraer el .zip en la carpeta en la que desee alojar el proyecto 

3. Montar la imagen de docker del microservicio, para esto abra una terminal en el directorio donde se encuentre el Dockerfile del proyecto y ejecute el siguiente comando:
```cmd
docker build -t tripster_user .
```
4. Desplegar el contenedor de la imagen ejecutando el siguiente comando en una terminal:
```cmd
docker run -p 8080:8080 --name tripster_user_ms -d tripster_user
```
5. Verifique el despliegue y realize pruebas en Postman.
