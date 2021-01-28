# eshop
This is a sample web application which represents a kind of online shop functionality, a simple admin web application and a simple database.

The server side is made with Java, Spring, Spring Boot, Spring Cloud, Thymeleaf, PostgreSQL. The client UI is based on a ready to use html web template. The admin UI is created with help of the BootAdmin framework.

## Modules
### eshop-admin
An admin web application. The UI is built on the BootAdmin web framework. The server side is a Spring MVC application with the Thymeleaf template engine. It requires `eshop-cloud-config` application to read the config parameters.

The admin application enables managing users as well as brands, categories and products. Product images are stored in a local folder in the file system. The directory path is set in the confing properties. Images are loaded from a cloud service `eshop-cloud-image` via the gateway `eshop-cloud-gateway`.

By default it is configured on port 8090.

`http://localhost:8090/eshop/admin`


### eshop-admin-ui-test
A cucumber test application which performs a couple of UI tests scenarios of the `eshop-admin` application. The tests are made with Selenium.

### eshop-client
A web shop application. The UI is a free ecommerce template modified by Thymeleaf template elements. The server side is made with help of Spring MVC, Spring Boot. The shopping cart is managed by the Spring session and stored in a local in-memory Hazelcast storage. Registering clients undergoes form validation (Java Bean Validation) with a couple of custom annotations. 

It requires `eshop-cloud-config` application to read the config parameters. Product images are loaded from a cloud service `eshop-cloud-image` via the gateway `eshop-cloud-gateway`.

By default it is configured on port 8089.

`http://localhost:8089/eshop`

### eshop-cloud-config
Manages configuration storage for the rest of modules using Spring Cloud Config. The `application.properties` file contains the common config parameters.

By default it is configured on port 8081.

### eshop-cloud-discovery
A spring cloud discovery server. Theauthentication is secured by basic authentication (login = `discover`, pass = `discover`).

It requires `eshop-cloud-config` application to read the config parameters. By default it is configured on port 8082. 

`http://discover:discover@localhost:8082`

### eshop-cloud-gateway
A gateway which routes requests to `eshop-cloud-image`.

By default it is configured on port 8888.

### eshop-cloud-image
Hosts a cloud service that provides images by requests. All its logic is loceted in `eshop-image-download-service`.

It requires `eshop-cloud-config` application to read the config parameters. By default it is configured on port 8084.

In case you want to run several service instances you need to run them on various ports, i.e.:

`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085`

### eshop-image-download-service
Provides logic for `eshop-cloud-image`. 

### eshop-common
Contains the code shared by other modules.

### eshop-dao
Contains shared entities, DTOs, entity-dto mappers, repositories.

### eshop-db
Responsible for data migration with `liquibase`.

### eshop-products-loader
A Spring Integration sample application which loads data to the Products table from a CSV file (loads all but images). A sample CSV file Products.csv is provided. The module uses the `opencsv` library as well.

It requires `eshop-cloud-config` application to read the config parameters. By default it is configured on port 10050.


## Set-up and Run Guide
#### 1. Set up DB
Be sure you have PostgreSQL RDBMS installed. 
- Create a database named `eshop`
- Check and set up if needed the DB settings in the `application.properties` config file located in `eshop-db/src/main/resources`
- Check the liquibase settings in the `liquibase-maven-plugin.properties` config file located in `eshop-db/src/main/resources`
- Execute the liquibase DB migration scripts (located in `eshop-db/src/main/resources/db.changelog`) by running the command
> `mvn clean install liquibase:update`

In case you need to rollback the DB to some point you can do it rolling back by tag, i.e.:
> `mvn liquibase:rollback -Dliquibase.rollbackTag=1.0`
> 

#### 2. Set up config property files in eshop-cloud-config
- Move the content of the `"/move files to config-repository"` directory to `/eshop-cloud-config/config-repository`. Initialize a git repository in it (`git init`).
- Review and correct if needed the config files, which are:
    - `application.properties` - common config parameters shared by other applications
    - `admin.properties`
    - `client.properties`
    - `discovery.properties`
    - `gateway.properties`
    - `image-service.properties`
    - `loader.properties`

#### 3. Run applications
1. Run the config application in `/eshop-cloud-config` 
   > `mvn clean install`
   > 
   > `mvn spring-boot:run`
   
2. Run `eshop-cloud-discovery`
3. Run `eshop-cloud-image`
4. Run `eshop-cloud-gateway`
5. Run `eshop-cloud-gateway`
6. Run `admin`, `client` or `loader`