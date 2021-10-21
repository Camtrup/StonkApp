## SUBMISSION 1

## FUNCTIONALITY:
We have got the functionality to search for a share from marketwatch.com and get the price, name and ticker for now, in Stonk.java.
The plan is to later be able to extract even more information for each share.
This was done with market scraping using Jsoup which is more about in the ReadME file.
A lot of time has been spent logging in and registering user pages to work.
We have also had problems transferring information from the various javafxml files to a controller.
Therefore, we found that it was better to create a controller class for each fxml file.

## FILE STORAGE:
We use the databas.json file to save the account to users. Here we only need to store the shares a person has bought and wants to buy.


## JAVA FXML:
So far we have 3 fairly finished javafxml pages. In total there will be 5 pages.
The last 2 we have not yet made.
finished pages:
- Login.fxml is for login page.
- mainPage.fxml is for the main page you enter
- newUser.fxml is for registering new users.

unfinished pages:
- stockPage.fxml: the page that appears for a stock when you try to buy it.
- myProfile.fxml: not sure if we need this, so we'll take it later

