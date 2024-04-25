
:blue_car: Car service backend :red_car:
 ==========

The idea came from home because we have cars and I want to organise that what we or the car 
mechanic(s) do with the cars. So I decided to make a project for develop my skills.

I used the following:
-------------------
- Spring Boot
- Docker
- Gradle
- JDK Correctto-20.0.2
- Swagger

## How to run this project

1) Firstly you need to run the **docker-compose.yml** and the **Dockerfile** file. This will create a docker container
with the database and the backend. Use the following command in the terminal:
    > `docker-compose up`
2) If you want to stop just use Ctrl+C
3) Use the following command if you want to delete the container:
    > `docker-compose down`

***Please note that the database is empty, so you need to put some data in it! Especially add repair types!***

You can easily add data to the database with ***Swagger***.

Use this for access the endpoints: http://localhost:8080/swagger-ui/index.html#/

## Frontend

I made a frontend for this project

You can download the frontend for this project here: https://github.com/Konn25/carservice_frontend
