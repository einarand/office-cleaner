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
Cleaning results are stored in a Postgres database. The default data database name is ```postgres```, 
but is configurable through [Environment](#environment) variables

## How to get started
### Preperations
Make sure you have Java sdk, Maven and Postgres installed.
For Mac OSX developers these tools can easily be installed using [Homebrew](https://brew.sh/).
```
#> brew install java maven postgres
```
Desclaimer: There may be other packages needed as well that I can't think of right now.

### Build and run the application
The executable jar file is built using Maven. The executable contains the application and a 
Tomcat server where the application will be deplyed when run.
```
#> mvn package
```
This will build the application, run all tests and package it into a executable jar file. 
The application is run by issuing following command.
```
#> java -jar target/office-cleaner-1.0-SNAPSHOT.jar
```
Note: To package the application into a war file instead of a jar file, please change the ```<packaging>``` element in the ```pom.xml``` file from ```jar``` to ```war```.

## <a name="environment"></a>Environment variables
When starting the application, it will look for a file called ```application.properties```. 
If it exists it will read the environment variables from there.
It is also possible to set enviroment variables through the command line.
If custom environment variables are not specified, it will use default variables specified below.

Default Postgres setup harcoded in the application, but this can be overridden through environment varibles (```spring.datasource``` name space).
It is also possible to change the port that the web application listenes to through the variable ```server.port```.

Default variables:
```
server.port=5000
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=cleaners
spring.datasource.password=1234
```

## <a name="api"></a>API
The API is accessable on http, port 5000 through two REST endpoints. One REST endpoint for executing the robot cleaning simulation and creating a report and one REST endpoint for getting the cleaning reports later. The url for the cleaning report will be found in the ```location``` header after executing the cleaning.

### Doing cleaning and creating a report
#### Request example
***POST*** ```http://localhost:5000/tibber-developer-test/enter-path```

required headers:
```
Content-Type: application/json
```

request body example:
```
{
    "start":{
         "x":5,
         "y":2
    },
    "commands":[
        {"direction":"north","steps":2},
        {"direction":"east","steps":3}, 
        {"direction":"south","steps":1}, 
        {"direction":"west","steps":5},
   ]
}
```

#### Response example
response code:
```
201 CREATED
```

headers:
```
Location: http://localhost:5000/tibber-developer-test/enter-path/08520023-e28f-4964-84cd-af25ddc5cc3d
```

body:
```
{
    "timestamp": "2018-10-25T08:16:39.727Z",
    "commandsIssued": 4,
    "placesCleaned": 11,
    "steps": 11,
    "durationInSeconds": 0.000058323
}
```

### Getting reports from previous cleanings
#### Request example
***GET*** ```http://localhost:5000/tibber-developer-test/enter-path/08520023-e28f-4964-84cd-af25ddc5cc3d```
#### Response example
response code:
```
200 OK
```

body:
```
{
    "timestamp": "2018-10-25T08:16:39.727Z",
    "commandsIssued": 4,
    "placesCleaned": 11,
    "steps": 11,
    "durationInSeconds": 0.000058323
}
```
