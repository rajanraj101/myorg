# myorg
springboot2 rest services 

Make sure jdk 1.8 and maven are  already installed 

Change the log file location in ~\src\main\resources\log4j2.xml

## Packaging
```
mvn clean package
```
## Execution
```
java -jar target/myorg-0.0.1.jar
``` 


## Rest Service
go to browser 
```
http://localhost:8080/myorg/employees/all
```

## Docker commands

Make sure jar file and Dockerfile are in same directory

## To create docker image 
```
$docker build -f Dockerfile -t docker-myorg .
```

## To check docker images 
```
$docker images
```

## To run docker image in container
```
$docker run -p 8080:8080 docker-myorg
``` 

## To view all containers
```
$container ls -a 
``` 

## Deployed in HeroKu
'''
url: https://myorg101.herokuapp.com/myorg/employees/all
'''
