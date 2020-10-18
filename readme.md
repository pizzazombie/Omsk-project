# Omsk project
> Test task for ltcompany

![test task](task.pdf)

Implement a web application with GET requests.


## Installation

First clone project and go to project folder

```sh
git clone https://github.com/pizzazombie/Omsk-project.git; cd Omsk-project;
```
 
Configure database setting in `application.properties` file.
Then compile project by Maven:

```sh
mvn package
```

Execute jar file:

```sh
cd target;
java -jar omsk-0.0.1-SNAPSHOT.jar;
```

## Usage example

Application works on port 9000;

2 get requests released:
```sh
GET http://localhost:9000/getDevices?id={id}
```
{id} - device_id in database. 
```
GET http://localhost:9000/getProjects
```

