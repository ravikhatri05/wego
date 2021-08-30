# Parking

Parking is a spring project with H2 in Mysql mode for the car parking assignment of wego.

## Installation

Use the cmds mentioned below in sequence

```bash
1. mvnw clean install
2. docker-compose build
3. docker-compose run
```

Alternate

```bash
1. mvnw clean install
2. cd target
3. java -jar parking-0.0.1-SNAPSHOT.jar
```

## How to use

``` java
1.once you build and ran the application than verify the instance using below url
    'http://localhost:8080/'
    
2. validate the DB schema and table structure using below end point
    'http://localhost:8080/h2-console/login.jsp?jsessionid=5f84d5ea63206ad12719e00325e7baff'
    fill the connection details from application.properties file

3. GET 'http://localhost:8080/carparks/config/upload'
   call above api to init the car parking data from csv

4. GET 'http://localhost:8080/carparks/config/sync'
   call the above api to sync the latest parking availability status

5. GET 'http://localhost:8080/carparks/nearest?latitude=1.37326&longitude=103.897&page=1&par_page=5'
   call above api to get the nearest parking

```

## TODO

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
