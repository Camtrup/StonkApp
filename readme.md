[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135)

![](https://i.ibb.co/qxM02Nc/fb2bfc2bf0ed4c569cdf5b8168878d1b.png)


# STONK APP

**Stonk** is a stock ["Paper Trading"](https://www.investopedia.com/terms/p/papertrade.asp) application, where the stock prices get updated in real time. Trading with virtual money is a great way for beginners to learn how to trade in the financial markets, but also to experienced traders that want to test their trading strategies without any risk.

Our initial thought was to pull the stock prices using an API, but we decided against it as we needed a service that was free-of-charge. Therefore we get the stocks current price by web-scraping from marketwatch.com. We use .json files for saving information such as username, password and your balance. The reasoning behind this is that more advanced systems is not needed for a game like Stonk.

## How to use Stonk:
You can choose between registering a new account or use one of ours.
If you want to see how an account with bought stocks looks like. You can log in with:
- Username: casper
- Password: 12345

### Run Stonk locally:
```
1. Clone (https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master)
2. Open the folder in a IDE as a Maven project.
3. Run (mvn install) inside the project folder.
4. To start the app get inside the ui package (Stonk/ui) and run: `mvn javafx:run`.
5. If you want to run the tests you can run `mvn verify` in (Stonk/).
```

### Run Stonk in Gitpod:
```
The repository also includes Gitpod configuration files. Follow theese steps to run the app.

1. Either click on the run gitpod button in gitlab or click on the gitpod button in this README file.
2. When the build of the app is done, write in (Stonk/ui): `mvn javafx:run`
3. If you want to run the tests you can run mvn verify in (Stonk/)
```
## Functionalities: 
- Log in
- Register
- Search up stocks
- Buy stocks
- Sell stocks
- Add stocks to wishlist
- Add more money
- Log out
- Delete user


## Project file
You can find more information about the code in the project file. It is divided into four parts. Core, data, UI and rest.

### CORE:
Core is where we have our two main java classes Stonk.java and User.java. 
- User.java is where the user credentials are made.
- Stonk.java is the class that gets the information about the stock you want to look for by web scraping the web page matketwatch.
- This is where you can find theese files.
[Core](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/src/main/java/stonk)

## Data
Data is the package for storing data and handling it. It gets handled with the datahandler and stored in Database.json
- You can find it here:
[Data](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/data/src/main/java/data)

### UI
UI is the package for where the user interface is made. Here the two main components are the fxml and Controller classes. The FXML files are where the actual user interface is made with scene buidler. THe controller classes it where the connection between the frontend and backend is. Here for instance the buttons from the fxml get their functions that tell them what to do.
- You can find the fxml files here:
[UI FXML](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/ui/src/main/resources/ui/fxml)
- You can find the Controller classes here:
[UI Controller](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/ui/src/main/java/ui)

### REST
INSERT TEXT HERE


## Project architecture
The structure in UI was too complex and large to put in with the rest of the structure of the app in the image. 

### Structure of the app:
```
 .
└── gr2135/
    ├── .idea/
    │   ├── gr2135.iml
    │   ├── misc.xml
    │   ├── modules.xml
    │   ├── runConfigurations.xml
    │   └── vcs.xml
    ├── .vscode/
    │   └── settings.json
    └── Stonk/
        ├── core/
        │   ├── src/
        │   │   ├── main/java/core/
        │   │   │   ├── Stonk.java
        │   │   │   └── User.java
        │   │   └── test/java/core/
        │   │       ├── StonkTest.java
        │   │       └── UserTest.java
        │   └── pom.xml
        ├── data/
        │   ├── src/
        │   │   ├── main/java/data/
        │   │   │   └── DataHandler.java
        │   │   └── test/java/data/
        │   │       └── DataHandlerTest.java
        │   └── pom.xml
        ├── rest/
        │   ├── .mvn/wrapper/
        │   │   ├── MavenWrapperDownloader.java
        │   │   ├── maven-wrapper.jar
        │   │   └── maven-wrapper.properties
        │   ├── src/
        │   │   ├── main/
        │   │   │   ├── java/rest/
        │   │   │   │   ├── StonkRestApplication.java
        │   │   │   │   ├── StonkRestcontroller.java
        │   │   │   │   └── StonkRestService.java
        │   │   │   └── resources/
        │   │   │       └── application.properties
        │   │   └── test/java/Stonk/rest/
        │   │       └── ApplicationTest.java
        │   ├── mvnw
        │   ├── mvnw.cmd
        │   └── pom.xml
        ├── ui/
        │   ├── src/
        │   │   ├── main/
        │   │   │   ├── java/ui/
        │   │   │   │   ├── HttpHandler.java
        │   │   │   │   ├── LoginController.java
        │   │   │   │   ├── MainController.java
        │   │   │   │   ├── ProfileController.java
        │   │   │   │   ├── RegisterController.java
        │   │   │   │   ├── StartApp.java
        │   │   │   │   ├── StockPageController.java
        │   │   │   │   └── StonkApp.java
        │   │   │   └── resources/ui/
        │   │   │       ├── fxml/
        │   │   │       │   ├── login.fxml
        │   │   │       │   ├── mainPage.fxml
        │   │   │       │   ├── newUser.fxml
        │   │   │       │   ├── profile.fxml
        │   │   │       │   └── stockPage.fxml
        │   │   │       └── images/
        │   │   │           ├── main.png
        │   │   │           ├── registerPage.png
        │   │   │           ├── signup.png
        │   │   │           ├── star-icon.png
        │   │   │           ├── stockPage.png
        │   │   │           └── stonkpicture.png
        │   │   └── test/java/ui/
        │   │       ├── README.md
        │   │       ├── StonkAppTest.java
        │   │       ├── StonkBuyTest.java
        │   │       ├── StonkLoginTest.java
        │   │       ├── StonkRegisterTest.java
        │   │       └── StonkUserTest.java
        │   └── pom.xml
        ├── README.md
        └── pom.xml
```

### Tree Structure visualized:
<a href="https://ibb.co/ChM8gLZ"><img src="https://i.ibb.co/gzP9NYp/tree-arc-all-IT-project.png" alt="tree-arc-all-IT-project" border="0"></a>

(click on the image for better quality)

### Tree Structure of UI:
<a href="https://ibb.co/fxy20kd"><img src="https://i.ibb.co/qrcd1NY/tre-arkitektur.png" alt="tre-arkitektur" height="150px" width="100%" border="0"></a>

(click on the image for better quality)