# Return-Management
this is sample program for simulate return management

<br>
<br>

## Requirements
This program requires *Java JDK 8+* to build and running. at this case i use *GraalVM CE 21.3.0*

<br>
<br>


## Build and Running
In this repository also included maven wrapper for compile and build purpose.

- run following command at first time build to fetch all required libary

    `./mvnw clean install`

- run following command to perform unit test
  
    `./mvnw test`

- run following command to run program at development mode
  
    `./mvnw spring-boot:run`

- run following command to build program as a jar package.
  
    `./mvnw clean package`
    
- then following command used run from a jar package.
  
    `java -jar target/return-management-0.0.1-SNAPSHOT.jar`

<br>
<br>

## Endpoint test
for endpoint test, please find file *ReturnKey.postman_collection.json* for Postman Collection.

*note*: this postman collection exported using version 2.1
