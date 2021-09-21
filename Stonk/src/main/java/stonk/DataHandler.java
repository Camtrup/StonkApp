package stonk;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataHandler {
    
    //Creates new user in the database
    public void newUser(String username, String password, String firstname, String lastname, int age, float cash, JSONArray portfolio) {
        JSONArray userArray = getAllUsers();
        JSONObject user = new JSONObject();

        user.put("username",username);
        user.put("password",password);
        user.put("firstname",firstname);
        user.put("lastname",lastname);
        user.put("age", age);
        user.put("cash", cash);
        user.put("portfolio",portfolio);

        userArray.add(user);
        writeToFile(userArray);
    }
    //Array of all users
    public JSONArray getAllUsers(){
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject)new JSONParser().parse(new FileReader("Stonk/src/main/resources/stonk/database.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray userArray = (JSONArray) jsonObject.get("users");
        System.out.println("getAllUsers --- " + userArray.toJSONString());
        return userArray;
    }

    public int getUserCount() {
        return getAllUsers().size();
    }

    //Returns the index of a given username in the database
    //If the username is not found, then return -1
    public int findUser(String username) {
        JSONArray userArray = getAllUsers();
        int count = 0;
        for(Object i : userArray){
            JSONObject user = (JSONObject) i;
            if(user.get("username").equals(username)){
                return count;
            }
            count++;
        }
        return -1;
    }

    //Returns a given user by index in the database
    public JSONObject getUser(int index){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(index);
        return user;
    }

    //Returs a user portfolio
    public JSONArray getPortfolio(int userIndex){
        JSONObject user = (JSONObject) getAllUsers().get(userIndex);
        JSONArray portfolio = (JSONArray) user.get("portfolio");
        return portfolio;
    }

    //Returns the index of a stock in a users portfolio
    public int getStockInPortfolio(String ticker, int userIndex){
        JSONArray portfolio = getPortfolio(userIndex);
        int count = 0;
        for (Object i : portfolio){
            JSONObject stock = (JSONObject) i;
            if(stock.get("ticker").equals(ticker)){
                return count;
            }
            count++;
        }
        return -1;
    }

    //Checks if portfolio containsStock(user has bought this stock earlier)
    //If it has, the new price is the average, with a count += newCount
    //If not, it adds the new stock in the portfolio
    public void addToPortfoilio(int userIndex, String ticker, float price, int count){
        JSONArray userArray = getAllUsers();
        JSONArray portfolio = getPortfolio(userIndex);
        JSONObject stock = new JSONObject();
        
        boolean containsStock = false;
        
        for(Object i : portfolio){
            JSONObject checkStock = (JSONObject) i;
            if(checkStock.get("ticker").equals(ticker)){
                stock = checkStock;
                containsStock = true;
                break;
            }
        }
        
        if(containsStock){
            float oldPrice = (float) Double.parseDouble(stock.get("price").toString());
            int oldCount = Integer.parseInt(stock.get("count").toString());
            
            int newCount = oldCount + count;
            float newPrice = (oldPrice * oldCount + price * count)/newCount;

            stock.put("price", newPrice);
            stock.put("count", newCount);
        }
        else {
            stock.put("ticker", ticker);
            stock.put("price", price);
            stock.put("count", count);
            portfolio.add(stock);
        }
        for(int i = 0; i < getUserCount(); i ++){
            JSONObject x = (JSONObject) userArray.get(i);
            if(x.equals(getUser(userIndex))){
                x.put("portfolio", portfolio);
            }
        }
        removeCash(price * count, userIndex);
        writeToFile(userArray);
    }

    //Checks if the amount of stocks is valid, and then subtracts from the user
    //If one sells the full amount of stocks, the stock is removed from the portfolio
    //Adds the cash back in the account
    public void removeFromPortfolio(int userIndex, String ticker, int count){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userIndex);
        JSONArray portfolio = (JSONArray) user.get("portfolio");
        JSONObject stock = new JSONObject();

        boolean containsStock = false;

        float plusCash = 0;

        for(Object i : portfolio){
            JSONObject x = (JSONObject) i;
            if(x.get("ticker").equals(ticker)){
                containsStock = true;
                stock = x;
            }
        }
        if(containsStock){
        
            if(Integer.parseInt(stock.get("count").toString()) >= count){
                int newCount = Integer.parseInt(stock.get("count").toString()) - count;
                plusCash = Float.parseFloat(stock.get("price").toString()) + Float.parseFloat(stock.get("price").toString()) * count;

                if(newCount == 0){
                    portfolio.remove(stock);
                }
                else {
                    stock.put("count", newCount);
                }
            }
            user.put("cash",plusCash);
            user.put("portfolio", portfolio);
            writeToFile(userArray);
        }
        else {
            throw new IllegalArgumentException("Stock not in portfolio");
        }

    }

    //Adds cash to the users account
    public void addCash(float num, int userIndex){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userIndex);
        user.put("cash", getCash(userIndex) + num);
        writeToFile(userArray);
    }


    //Checks if withdrawing the amount is valid
    public void removeCash(float num, int userIndex){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userIndex);
        Float cash = Float.parseFloat(user.get("cash").toString());
        if(cash > num){
            cash -= num;
            user.put("cash", cash);
            writeToFile(userArray);
        }
        else{
            throw new IllegalArgumentException("Not enough cash");
        }

    }

    //Returns the users cash
    public float getCash(int userIndex){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userIndex);
        return Float.parseFloat(user.get("cash").toString());
    }

    //Throws Excpetion if username doesnt exists
    //Returns null if password is incorrect
    //Returns a new instance of a user if the login is valid
    public User isLoginValid(String username, String password){
        int index = findUser(username);
        if(index >= 0){
            JSONObject user = getUser(index);
            if(user.get("password").toString().equals(password)){
                JSONArray portfolio = (JSONArray) user.get("portfolio");
                return new User(username, 
                            password,
                            user.get("firstname").toString(), 
                            user.get("lastname").toString(),
                            Float.parseFloat(user.get("cash").toString()),
                            Integer.parseInt(user.get("age").toString()), 
                            portfolio);
            }
        }
        else {
            throw new IllegalArgumentException("Username not in databse");
        }
        return null;
    }

    //Writes the array to the file
    public void writeToFile(JSONArray arr){
        JSONObject obj = new JSONObject();
        obj.put("users", arr);
        try(FileWriter file = new FileWriter("Stonk/src/main/resources/stonk/database.json", false)) {
            file.write(obj.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DataHandler d = new DataHandler();
        d.isLoginValid("username", "password");
        System.out.println(d.isLoginValid("username", "password"));
    }
}
