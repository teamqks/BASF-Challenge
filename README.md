# BASF-Challenge

## Install the app

1. Clone the repository.
git clone
2. Compile the project with Maven and the following command:
mvn clean package -DskipTests
3. Access the base path of the project, where the docker-compose.yml file is located and execute the commands:
docker-compose build
docker-compose up
4.The application will be running (Microservice + MongoDB), to know the API specification you can access the URLs:
http://localhost:8082/v2/api-docs
http://localhost:8082/swagger-ui/
5. In the /src/test/postman folder there is a battery of tests that can be performed with Postman.
6. In the /src/main/resources/files/ folder there are some sample XML files.

## Run the app

- A simple security method has been established in the application, therefore the requests made to the API must include the header: BASF-auth : secret (except in calls to the swagger and the HTTP OPTIONS method)
- First of all, you should start with the POST call to the URL localhost:8082/api/v1/extractions/execute, introducing the XML files from which you want to extract information.
- Next, you can retrieve all the information about the patents with the URL localhost:8082/api/v1/extractions/ or of a specific patent if you know the id with the URL localhost:8082/api/v1/extractions/{ id} (GET Method)
- Finally, you can delete a specific patent with the DELETE method in the URL localhost:8082/api/v1/extractions/{id} or clean the entire database in the URL localhost:8082/api/v1/extractions /
