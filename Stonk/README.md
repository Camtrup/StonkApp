# Stonk code Strucutre!

In this section we will write about how the code is built, everything from which libraries we are using to illustrations and the functioning of the different files.

## Maven build
Maven is a software project management and building tool. Based on the concept of a project object model (POM). The Maven-build for our app needs at least Java version 14, and JavaFX version 11.

The reason we went with maven instead of gradle is because it uses an XML file for declaring the project, its dependencies, the build order, and its required plugin. This is something we find familiar and easy to understand from doing former projects.

### JSoup
**Stonk** is built with the library [JSoup](https://jsoup.org). Jsoup allows us to scrape and parse data from a website using DOM traversal and CSS selectors. 
Using this we are able to get information about every given stock in real-time.
### Gson 
Gson is a great Java library that primary use is to convert Java Objects into their JSON representation. It also gets used to convert a JSON string to an equivalent Java object. 
### JSON-simple
JSON-simple is s much simpler version of JSON. It uses a Java library library for JSON processing, read and write JSON data and full compliance with JSON specification.
### JAVAFX
JavaFX is a Java library that simplifies the development of Rich Internet Applications (RIA). Applications written using this library can be run across different platforms. The applications can also be run on various devices such as computers, mobile phones, TVs and tablets.
### Spring Boot
Spring Boot is an open source, micro-service-based Java network. The Spring Boot framework creates a completely production-ready environment that is fully configurable using the pre-built code in the code base. The Microservice architecture provides developers with a full-featured application, including embedded application servers.

### Jlink
Jlink is a tool that generates a custom Java runtime image that has only the platform modules that are required for an application.
### SpotBugs, CheckStyle and JaCoCo
- Spotbugs has helped with finding unused codes and errors or bugs we were not able to find ourselves. 

- Checkstyle made the code quality much better by helping standardizing our formatting to Google coding conventions

- We use jacoco which is a Code Coverage Library, to check how much coverage our tests have. Our goal is to have at least 80% test coverage at each of our modules.

## How we have Coded
### Meetings
The miniumum requirments we set for ourselves was to meet once a week, but we found it better to meet around 3 times a week to more easily pair code(parr-programmering) and communicate better. We also focused on following the coding commit rules given from [conventionalCommits](https://www.conventionalcommits.org/en/v1.0.0/) 

### Pair coding
Pair programming has helped us to higher the quality of the code by prrogramming out loud with a driver and navigator working together. We started meeting more often to pair program as much as possible.

### SCRUM
SCRUM has been central for our coding. We have participated and are aware of all sorts of agile ceremonies (user story grooming, sprint planning, sprint retrospective). This helped to impact the end product, by having fixed deliveries in short iterations with a fixed length in close, ongoing collaboration between customer and supplier. Apart from the Sprints, the QA (who does it, when it gets done) helped alot. Being able to asign and set a deadline on gitlabs really helped having control over our project. 

SCRUM was a powerful tool that empowered us to fix our mistakes quickly and made the whole team feel acountabel for the delivery, and in addition it is really easy not to get stuck on problems because if you do, you can simply ask the team in the next meeting.

#### How gitlab helped
Gitlab is great for enabling lean and agile project management especially for scrum projects. Here we were able to add issues, which had a deadline, labels and could be issued to grup members. THe milestone and board functionalities also helped us hold upå with deadlines.

## Files

CORE:
  - Stonk.java: Pulls information about the stock using JSoup.
  - User.java: Creation and editing of user information.
  

DATA:
   - database.json: Saves information like username, password and balance.
   - Datahandler.java: Saving data to the json file.

UI:
- LoginController.json: Controller for login page
- MainController.json: Controller for main page
- ProfileController.json: Controller for profile page
- RegisterController.json: Controller for register page
- StockPageController.json: Controller for Stock page
- StonkApp.java: Connects the fxml files to the project.
- HTTPHandler.java: Sends requests up to our server. 

REST:
- StonkRestApplication.java: Launches our rest-api.
- StonkRestController.java: The controller for our api with the different paths on the application.
- StonkRestService.java: The "backend" for our server which can execute most of the functionality in our app.

# User Story 1
<sub>*Connected to issue [#9](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/issues/9).*</sub>

"As a stock broker I want to have a list over the stocks I own virtually, so that I can easily compare my virtual stocks to my actual stock holdings."

# User Story 2
<sub>*Connected to issue [#24](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/issues/24).*
</sub> 

"As a user, if the app doesn't respond, I would like som feedback boxes so that it would be easier to understand why the app is not working at a given time.

# User Story 3
<sub>*Connected to issue [#40](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/issues/40).*
</sub> 

"As a user, privacy is important to me, so I would like some kind of mechanism to store our data more secure."

# User Story 4
<sub>*Connected to issue [#41](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/issues/41).*
</sub> 

"As a user who watches some stocks more closely than others, I would like a watchlist so that I could easily be updated on all of them."

## Illustrations

<a href="https://ibb.co/LCt19wK"><img src="https://i.ibb.co/RC0cQn8/Skjermbilde-2021-11-20-kl-17-27-51.png"  height="400"
 alt="Skjermbilde-2021-11-20-kl-17-27-51" border="0"></a>   Profile side where you can add more money to you account
 

<a href="https://ibb.co/sw5Ccv9"><img src="https://i.ibb.co/85zbGDg/Skjermbilde-2021-11-20-kl-17-28-27.png"  height="400" alt="Skjermbilde-2021-11-20-kl-17-28-27" border="0"></a>   Stockpage to buy, sell and add stocks to watch List

<a href="https://ibb.co/cTNk138"><img src="https://i.ibb.co/f0Cq2FN/Skjermbilde-2021-11-20-kl-17-28-55.png"  height="400" alt="Skjermbilde-2021-11-20-kl-17-28-55" border="0"></a>  Homepage showing balance and stocks you own


<a href="https://ibb.co/CbX8MGL"><img src="https://i.ibb.co/Pcbj9Hk/Skjermbilde-2021-11-20-kl-17-33-03.png" height="400" alt="Skjermbilde-2021-11-20-kl-17-33-03" border="0"></a>  Login site


<a href="https://ibb.co/fvwNJVg"><img src="https://i.ibb.co/16WXcYg/Skjermbilde-2021-11-20-kl-17-33-16.png" height="400" alt="Skjermbilde-2021-11-20-kl-17-33-16" border="0"></a>  Register a new user site


## Diagrams

#### sequence diagram:


#### class diagram:



