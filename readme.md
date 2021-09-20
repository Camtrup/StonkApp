# STONK APP
the stonk, is an application that lets you trade stocks with fake money, where the stock prices get updated in real time. its a great way for learning to trade better, without dthe risk. The stock prices are gotten through web-scraping from marketwatch.com instead of using an API because they cost money. We use .json files for saving inforamtion such ass username, password and your wallet. This is because we don't see any reason for better safety than that for a game. 

## Project file:
Stonk>src>main>java>...
## fxml and json files can you find in:
Stonk>src>main>java>resources>app...

## files we have:
Datahandler.java: This file is how we save data to our json file.

Stonk.java: Gets us the stock we want and information about it by webscraping

StonkApp.java: connects the app to the fxml files to get an UI

User.java: code for making and changing a users information  

database.json: file for saving inforamtion such ass username, password and your wallet.

