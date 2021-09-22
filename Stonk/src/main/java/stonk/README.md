# Stonk code!

In this section we will write about how the code is built, everything from which libraries we are using to illustrations and the functioning of the different files.

## JSoup
**Stonk** is built with the library [JSoup](https://jsoup.org). Jsoup allows us to scrape and parse data from a website using DOM traversal and CSS selectors. 
Using this we are able to get information about every given stock in real-time.


## Files

 - Datahandler.java: Saving data to the json file.
   
  - Stonk.java: Pulls information about the stock using JSoup.
   
  - StonkApp.java: Connects the app and the FXML.
   
  - User.java: Creation and editing of user information.
   
   - database.json: Saves information like username, password and balance.

## Illustrations
***The illustrations are due to change as we will keep making changes to the UI going forward.***

![Homepage with recent transactions](https://i.ibb.co/hDPYfz3/Skjermbilde-2021-09-22-kl-14-08-24.png =250x)
*Homepage showing balance and recent transactions.*


![Showing the completion of a purchase](https://i.ibb.co/PzvRwBW/Skjermbilde-2021-09-22-kl-14-08-40.png =250x)
*After you have completed a purchase there will be a confirmation screen that shows what you bought, total price and how much each stock cost.*

These are just some of the many thoughts and ideas we have, and we will keep updating these as we see fit when the project proceeds.