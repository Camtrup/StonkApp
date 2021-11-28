# UI 

## what is UI:
UI is the package for where the user interface is made. Here the two main components are the fxml and Controller classes. The FXML files are where the actual user interface is made with scene buidler. THe controller classes it where the connection between the frontend and backend is. Here for instance the buttons from the fxml get their functions that tell them what to do.
- You can find the fxml files here:
[UI FXML](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/ui/src/main/resources/ui/fxml)
- You can find the Controller classes here:
[UI Controller](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/ui/src/main/java/ui)


## UI classes:
- LoginController.java: Controller for login page
- MainController.java: Controller for main page where you se your cash balance and stocks
- ProfileController.java: Controller for profile page where you can edit your account
- RegisterController.java: Controller for register page to make a new account
- StockPageController.java: Controller for Stock page
- StonkApp.java: Connects the fxml files to the project.
- HTTPHandler.java: Sends requests up to our server. 
- SuperController.java: a Controller sites that loads all the fxml files.

## Class Diagram for UI
<img src="out/docs/diagramsUML/classUi/UIClass.png">
