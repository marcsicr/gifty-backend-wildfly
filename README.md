# Gifty REST Backend
Backend implementation for Gifty using JavaEE (Wildfly) + PostgreSQL.

## About the project

This is a backend service for Gifty browser app that allows users to register, login and save their favorite gifs. The service has been implemented as a REST API.

## Tools and libraries used
* JavaEE + JAX-RS on Wildfly
* PostgreSQL + JPA
* JSON Web Tokens - JWT (Auth0)
* Maven
## Prerequisites 
This project uses a running instance of **Wildfly** and a **PostgreSQL** server. So before you begin, you should install/configure them first. 

## Installation 
1. Clone this repository
    ```sh
    git clone https://github.com/marcsicr/gifty-backend-wildfly.git
    ```

2. Modify **ear-module/pom.xml** properties to match your **Wildfly** and **PostgreSQL** working environment.
    ```xml
    ...
    <properties>
	
    <!--Wildfly Setup> -->
		<wildfly-user>USER</wildfly-user>
		<wildfly-password>1234</wildfly-password>
		<wildfly-host>localhost</wildfly-host>
        <wildfly-admin-port>9990</wildfly-admin-port>
		
	 <!-- PostgreSQL Setup -->
		 <postgresql.driver.version>42.2.19</postgresql.driver.version>
		 
		 <pg-host>192.168.1.2</pg-host>
		 <pg-port>5432</pg-port>
		 
		 <pg-user>postgres</pg-user>
		 <pg-password>1234</pg-password>
		 <gifty-schema-name>gifty</gifty-schema-name>
		 
	</properties>
    ...
    ``` 
3. Compile and deploy the application on your running Wildfly instance.
    ````sh
    mvn install
    ````
    If deployment was successfull the service should be up and running on http://localhost:8080/gifty  (using Wildfly default host configuration)

 ## Uninstall
To undeploy the service, make sure your Wildfly instance is up and running and then type
```
mvn clean
```
    
