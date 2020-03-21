# myorg
springboot2 rest services 

Make sure jdk 1.8 and maven are  already installed 
Change the log file location in ~\src\main\resources\log4j2.xml

#To build (create jar file)
mvn clean package
 
#To run app:
java -jar target/myorg-0.0.1.jar

#URL to test locally 
http://localhost:8080/myorg/employees/all

#Docker commands
#Make sure jar file and Dockerfile are in same directory
#Command to create docker image 
$docker build -f Dockerfile -t docker-myorg .

#Command to check docker images 
$docker images

#Command to run docker image in container
$docker run -p 8080:8080 docker-myorg
 