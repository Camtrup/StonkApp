# Rest

## Rest services

All files will have the standard mapping "http://localhost:8080/ + the name of our requestmappings. 
Here is a full list over all the mappings: 

- test: 

- user: Will return an ArrayList<User> of our users. 

- isLoginValid/{username}/{password}: username and password will be based on input values, and the function checks that the values follows our specific criterias for logging in.

- user/{username}/{password}: Username and password are from the current user. This method is for getting a specific users data, eg the corresponding stocks.

- buy/{username}/{password}/{ticker}/{count}: username and password comes from the current user. Ticker is for searching up the stock and count says how many the user wants to buy. This adds the stock to your stocks and updates balance.

- sell/{username}/{password}/{ticker}/{count}: username and password comes from the current user. Ticker is for searching up the stock and count says how many the user wants to sell. This removes the stock from your stocks and updates balance.

- add/{username}/{password}/{ticker}: Username and password from the current user. Ticker for finding the stock. This will add the specific stock to the users watchlist.

- remove/{username}/{password}/{ticker}: Username and password from the current user. Ticker for finding the stock. This will remove the specific stock from the users watchlist.

- new/{firstname}/{lastname}/{username}/{password}/{cash}/{age}: This will create a new user with these values, and updates in database.

- delete/{username}/{password}: This will delete the current user.

- value/{username}/{password}/{cash}: This is used for adding more cash to your current balance.

- save: Saves the updated information to the JSON-file.

### Class Diagram for Rest
<img src="out/docs/diagramsUML/classRest/classRest.png">
