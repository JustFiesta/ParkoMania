# ParkoMania

Parking spaces booking app made as a JavaFX project for studies.

It is my first big GUI project.

<hr>

## Functionality
Users can:
* register/log in/log out into app
* add/remove plate number for their car
* place two types of reservations on vehicle
* view current and past reservations

<hr>

## What have I learned?
* using hibernate to fetch, commit, and update objects in database 
* OOP and functional programming in practise 
* how to develop GUI project 
* how to split tasks to atomic level - like in Lean Development meaning. This way is better for single person to not loose focus and motivation. Also speeds up development process. 
* how to explain code to colleagues - writing classes, methods and variables in way that anyone can easly get up to speed on what is going on 
* debugging code
* building jar 
* working with documentation - well oracles' is a mess, hibernates' too. But source code form classes make it bearable to understand 
* asking for help if needed - if stuck don't worry, anyone can have better and worse days. Sometimes brain is not working as it should 

<hr>

## What I need to do, to make my programs better?
* make more classes for boiler code - ex. I didn't create a class for db connection - it would have shortened developing time much more, and cut need for pasting same code. 
* make functions oriented on one tast only - it would make code more readable and maintainable. 
* split classes to modules - maybe not in this case but i see how it can improve maintanability in bigger projects. 
* commit after finishing writing one functionality - not few - I often wrote too fast and used commits not to often. But I tried to make them as much readable as i could. The goal is to commit after one functionality and merge/rebase them into one push. 

<hr>

## Dependencies
* Hibernate ORM
* Jakarta
* JavaFX controls - not mandatory. Berly used
* MySQL JDBC driver

<hr>

## Catalog structure
-> documentation - documentation folder, containing design assumptions.  
-> src - source folder for this project.  
    -> java - contains module info and all clasess used in this program.  
    -> resources - hibernate config, fxml views (made using scene builder), images used in project.  


<hr>

## Documentation
Please check the documentation folder

<hr>

## Info and help
* hibernate.org/orm/releases
* mvnrepository.com

* always check requires in module info, and always add dependencies there
* paths in jar file get messed up, there is no simple way to fix it. Many People get similar problem, yet it always differ in some way.

<hr>

## Special Thanks
To my girlfriend for making this simple, yet charming UI design. Many thanks to You dear.


