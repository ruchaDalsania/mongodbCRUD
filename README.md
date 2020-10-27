# mongodb project

This is a Spring boot application. 

- Main Class	: com.sample.mongo.MongoDBProjectApplication
- Language	: Java
- Build		: Gradle
- Logging		: Log4j2

This will connect to a cloud MongoDB Database via url specified in application.properties

- Class level Request mapping URL : /api/v2
- GET mapping					 : /cloud/movies
- Cross Origin Front End			 : http://localhost:8081


### Gradle Build
```
gradle build
```
### Docker
- docker ps
- docker images

### Run mongodb
unable to find mongodb:4.0.4, so it will pull a new image of same name
> docker run -d -p 27017-27019:27017-27019 --name mongodb mongo

you will be able to see the new one
> docker ps

connect to bash
> docker exec -it mongodb bash

connect to cloud db
> mongo "mongodb+srv://m001-student:m001-mongodb-basics@cluster0-jxeqq.mongodb.net/test?authSource=admin&replicaSet=Cluster0-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true" --username m001-student -password m001-mongodb-basics

connect to local db
connect to local database(mongodb://127.0.0.1:27017), you can add this address in compass to connect to GUI
> mongo

list admin, config and local
> show dbs

switch to a new one, if exist then connect to existing one
> use mymongodb

adds a new record
> db.people.save({name:"rucha"})
> db.food.save({category:"sweet", name:"Jalebi", picture:null})

returns a row
> db.people.find({name:"rucha"})

to disconnect from current db but still in bash inside container
> quit()

### Heroku deployment
```
Heroku create
git push heroku master
herkou open
```
Creates an app with the specified name, without setting up a git remote
```
heroku create <APP-NAME> --no-remote
heroku open --app <APP-NAME>
heroku logs --app <APP-NAME>
```

```sh
https://safe-meadow-94931.herokuapp.com/api/v2/cloud/movies
```


