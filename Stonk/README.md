# Stonk code Strucutre!

In this section we will write about how the code is built, everything from which libraries we are using to illustrations and the functioning of the different files.

## JSoup
**Stonk** is built with the library [JSoup](https://jsoup.org). Jsoup allows us to scrape and parse data from a website using DOM traversal and CSS selectors. 
Using this we are able to get information about every given stock in real-time.

## Maven build
Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information. The Maven-build for our app needs at least Java version 14, and JavaFX version 11.




## SpotBugs, CheckStyle and JaCoCo
- Spotbugs has helped with finding unused codes and errors or bugs we were not able to find ourselves. 

- Checkstyle made the code quality much better by helping us keep up with coding standards.

- We use jacoco whis is a Code Coverage Library, to check how much coerage our tests have. Our goal is to have at least 70% test coverage.
## How we have Coded
### Meetings
The miniumum requirments we set for ourselves was to meet once a week, but we found it better to meet around 3 times a week to more easily pair code(parr-programmering) and communicate better. We also focused on following the coding commit rules given from [conventionalCommits](https://www.conventionalcommits.org/en/v1.0.0/)

### Pair coding
Pair programming has helped us to higher the quality of the code by prrogramming out loud with a driver and navigator working together. We started meeting more often to pair program as much as possible.

### SCRUM
SCRUM has been central for our coding. we have participated and aware of all sorts of agile ceremonies (user story grooming, sprint planning, sprint retrospective). This helped to impact the end product, by having fixed deliveries in short iterations with a fixed length in close, ongoing collaboration between customer and supplier. Apart from the Sprints, the QA (who does it, when it gets done) helped alot. Being able to asign and set a deadline og gitlabs really helped having controll. 

SCRUM was a powerful tool that empowered us to fix our mistakes quickly and made the whole team feel acountabel for the delivery


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


## User Story
<sub>*Connected to issue [#9](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/issues/9).*</sub>

"As a stock broker I want to have a list over the stocks I own virtually, so that I can easily compare my virtual stocks to my actual stock holdings."

**Acceptance criteria**:

"If I open the homepage I expect to see which stocks I own, and also that it updates when I buy a new stock, or sell one that I already own. I also expect to be able to update the stock prices to see how all of them have performed after I bought them."


## Illustrations
***The illustrations are due to change as we will keep making changes to the UI going forward.***

<img src="https://i.ibb.co/hDPYfz3/Skjermbilde-2021-09-22-kl-14-08-24.png"  height="400">

*Homepage showing balance and recent transactions.*


<img src="https://i.ibb.co/PzvRwBW/Skjermbilde-2021-09-22-kl-14-08-40.png"  height="400">

*After you have completed a purchase there will be a confirmation screen that shows what you bought, total price and how much each stock cost.*

These are just some of the many thoughts and ideas we have, and we will keep updating these as we see fit as the project proceeds.
