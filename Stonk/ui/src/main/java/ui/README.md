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
