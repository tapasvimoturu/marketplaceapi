    Technologies Used

   1.Spring Boot/Spring Data. 1.5.9 
   2.Mongo DB. v3.4.10
   3.Eclipse IDE   Version: Oxygen.2 Release (4.7.2)
   4.Spring boot embedded Tomcat Server
   5.Java 8
   6.Maven 
   6.PostMan for testing api
   
MarketPlace API has two models(entities)

     1)Project 

     2)Bid

     Project Model will hold List of Bid.Project has budget, name, description, requirements, last Bid date, least bid amount fields

     Project has two statuses OPEN and CLOSED

     Bid has amount, projectId and createdDate fields

     Bid always exist within  a Project, a composition association.


   Used Spring Boot to create initial Project and Spring Data/Mongo DB for transaction/persistence.Used Mongo DB as it don’t need any pre defined schema and we might have different kinds of Projects in Market Place with different fields.

In src/main/resources/application.properties we have to give configurations for mongo db like below 

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=marketplace

Database “marketplace” will be created in mongo db first time when we save Project/Bid.


For Launching the application just open the project in eclipse and run as spring boot application it will run in embedded tomcat.

Used Postman to test API

API for Projects

1)Get All projects

localhost:8080/marketplace/api/projects
    
    By default will give all OPEN Projects
localhost:8080/marketplace/api/projects?status=CLOSED
        Will return all closed Projects also
     

2)Get Project with ID

localhost:8080/marketplace/api/projects/{projectId}

NB:This returns the current least Bid Amount  field also we have in system

3)Create a Project POST method

localhost:8080/marketplace/api/projects

4)Update a Project PUT method

localhost:8080/marketplace/api/projects

Validations 

Name is mandatory for Project

API for Bid

1)Create a Bid for a Project -POST method

localhost:8080/marketplace/api/projects/{projectId}/bids

2)Get All Bids for a Project 

localhost:8080/marketplace/api/projects/{projectId}/bids

3)Delete a Bid DELETE method

localhost:8080/marketplace/api/projects/{projectId}/bids/{bidId}

4)Update a Bid PUT method

localhost:8080/marketplace/api/projects/{projectId}/bids

Validations

Bid Creation Date should be before Project Last Bidding Date

While Creating Bid Project is validated like only for a valid Project we can create a Bid

If Project status is CLOSED we can’t create a Bid


Additional Notes


1) For getting the winner of a Bid we can have a field wonBidId in Project and set it using  a cron(concurrent program) job which can close the Project and change the status to CLOSED on lastBidDay.

2)Could have added authentication and  authorization to API , but due to time constraint keeping it in notes

User Entity with user related attributes and roles.


Feedback on Exercise
   The time the exercise took (after dev environment is set up)
    I have spent nearly 5 hours on this exercise after setting up the environment.
    
    Exercise Difficulty: Easy, Moderate, Difficult, Very Difficult
       Easy
    
    How did you feel about the exercise itself? (1 lowest, 10 highest—awesome way to assess coding ability)
        Its a very nice way to assess not only coding ability but also design, thought process and problem solving ability of         a candidate--8
    How do you feel about coding an exercise as a step in the interview process? (1 lowest, 10 highest—awesome way to assess      coding ability)
        I felt it exciting as you are solving a real world problem than some basic coidng/algorithms-- 8
 What would you change in the exercise and/or process?
        Not much things.
