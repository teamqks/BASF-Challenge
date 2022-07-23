# BASF-Challenge

## Install the app

1. Clonar el repositorio.
git clone 
2. Compilar el proyecto con Maven y el siguiente comando: 
mvn clean package -DskipTests
3. Acceder a la ruta base del proyecto, donde se encuentra el fichero docker-compose.yml y ejecutar los comandos:
docker-compose build
docker-compose up
4.La aplicación estará corriendo (Microservicio + MongoDB), para conocer la especificación del API se puede acceder a las URL:
http://localhost:8082/v2/api-docs
http://localhost:8082/swagger-ui/
5. En la carpeta /src/test/postman hay una batería de pruebas que se puede realizar con Postman.
6. En la carpeta /src/main/resources/files/ hay unos ficheros XML de ejemplo.

## Run the app

- En la aplicación se ha establecido un método sencillo de seguridad, por ello en las peticiones que se realicen al API se debe incluir la cabecera: BASF-auth : secret (excepto en las llamadas al swagger y el método HTTP OPTIONS)
- En primer lugar, se debería empezar por la llamada POST a la URL localhost:8082/api/v1/extractions/execute introduciendo los ficheros XML de los cuales se quiera extraer información.
- A continuación, se puede recuperar toda la info sobre las patentes con la URL localhost:8082/api/v1/extractions/ o de una patente concreta si se sabe el id con la URL localhost:8082/api/v1/extractions/{id} (Método GET)
- Por último, se puede eliminar una patente concreta con el método DELETE en la URL localhost:8082/api/v1/extractions/{id} o limpiar la base de datos al completo en la URL localhost:8082/api/v1/extractions/
