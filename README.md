# Coding Test for Employee API
### I have moved original README.md to TASK.md which has original instructions for test. Please refer TASK.md for original instructions.

##  Employee API description and steps to run it

- This service was built using Java 8, SpringBoot, Maven, h2 in memory db.
- I created API's(CreateEmployee, GetEmployee, GetAllEmployees, DeleteEmployee) in EmployeeController.java.
- I made use of Spring Boot validation library for validating user input for CreateEmployee API
- Added Swagger library for documenting API's, Access URL "http://localhost:9084/swagger-ui/index.html" 
  after app started.
- H2 DB can be accessed at http://localhost:9084/h2-console. Login details are mentioned in application.properties.  
  DB is emploeedb  

- Steps to Execute. 

   - Java 8, Maven(> 3.5.X) needs to be installed. Mine was 3.6.3.
   - clone the project java-banking-api-awnmft.
   - open terminal, hit mvn clean install.
   - hit mvn spring-boot:run.
   - OR  run using docker. Fro this docker needs to be installed.
   - RUN Below commands to run the application using docker.
   - docker build -t employeeapp .
   - docker run -d -p 9084:9084 -t employeeapp
   - open other terminal, type curl commands or use postman.
       - http://localhost:9084//api/employees/ [POST]
       - http://localhost:9084//api/employees/1 [GET]
       - http://localhost:9084//api/employees [GET]
       - http://localhost:9084//api/employees/3 [DELETE]