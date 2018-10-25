# Office Cleaner
### Description
Office cleaner is a service that simulates a robot moving in an office space
and cleaning the places the robot visits. The path of the robot movement is
described by start coordinates and move commands. After the cleaning has been
done the robot reports the number of unique places cleaned, and the service
will store the result into the database. 

### Technology
Office cleaner is RESTful web application written in Java on the [Spring Boot](https://spring.io/projects/spring-boot) framework. 
The service listens to HTTP protocol on port 5000. See [API](#api) section below for details on how to use the service.
Cleaning results are stored in a Postgres database. The defualt data database name is ```postgres```, 
but is configurable through Environment variables

## How to get started
### Preperations
Make sure you have Java sdk, Maven and Postgres installed.
For Mac OSX developers these tools can easily be installed using [Homebrew](https://brew.sh/).
```
#> brew install java, maven, postgres
```
### Build and run the application
The executable jar file is built using Maven. The executable contains the application and a 
Tomcat server where the application will be deplyed when run.
```
#> mvn package.
```
This will build the application, run all tests and package it into a runnable jar file. 
The application is run by issuing following command
```
#> java -jar target/office-cleaner-1.0-SNAPSHOT.jar
```

## Environment variables
When starting the application, it will look for a file called application.properties. 
If it exists it will read the environment variables from there.
It is also possible to set enviroment variables through the command line.
If custom environment variables are not specified, it will use default variables specified below.

Default Postgres setup harcoded in the application, but this can be overridden through the Environment.
It is also possible to change the port that the web application listenes to.

Default variables:
```
server.port=5000
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=cleaners
spring.datasource.password=1234
```

## <a name="api"></a>API
Here goes the API


