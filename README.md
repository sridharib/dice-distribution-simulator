# dice-distribution-simulator
## Avaloq dice distribution simulatior

This is a sample project to demonstrate dice rolling simulator. The rest end point accepts three parameters & returns the result as a JSON structure.
```
numberOfDice: Integer
numberOfRolls: Integer
sidesOfDice: Integer
```

###### Commands to run this project
```
mvn clean install
cd dice-distribution-simulator
mvn spring-boot:run 
```

Once the server is up & running then go to

> http://localhost:8080/swagger-ui/
