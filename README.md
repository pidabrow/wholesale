# Wholesale Application
This application is a REST API to support Wholesale management. It allows to create/update/delete Products along with create/update Availabilities.
For each Product there's also an option to display all Availabilities using proper API endpoint.

The entire API is documented using Swagger. In order to open the documentation download the app, run and open `http://localhost:8080/swagger-ui.html` for SwaggerUI.

# How to run?
1. Clone the application
2. Go to the project directory
3. Run `mvn clean install` and once sucessful, `mvn spring-boot:run`
4. Once both maven goals are completed, the application is ready to use.

# How to access the DB?
The application uses embedded H2 Database which is accesible under `http://localhost:8080/h2-console`, once the application is running
Credentials - url: `jdbc:h2:mem:testdb` user: `sa`, password blank.
