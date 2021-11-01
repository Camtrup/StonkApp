package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DataHandler {

    //The filepath now dynamiclly finds the working directory of the user, and the adds the path to the database
    private String file = System.getProperty("user.dir") + "/Stonk/data/src/main/resources/database.json";
    //When running with "javafx:run", the working directory will be "ui".
    //This method removes the path into "ui", so the path finds the file in "data"
    private void adaptFilePath(){
        String s = file.replace("/Stonk/ui", "");
        file = s;
    }
    
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
        adaptFilePath();
        JSONParser parser = new JSONParser();
        JSONArray userArray = new JSONArray();
        try(FileReader reader = new FileReader(file, StandardCharsets.UTF_8)){
            JSONObject obj = (JSONObject) parser.parse(reader);
            userArray = (JSONArray) obj.get("users");
        }
        
        catch(IOException | ParseException e){
            System.out.println(e);
        }
        return userArray;
    }

    //Returns a given user in the database by checking for the unique username
    //If the username is not found, then return null
    public JSONObject findUser(String username) {
        JSONArray userArray = getAllUsers();
        for(Object i : userArray){
            JSONObject user = (JSONObject) i;
            if(username.equals(user.get("username").toString())){
                return user;
            }
        }
        return null;
    }

    //Returs a user portfolio
    public JSONArray getPortfolio(JSONObject user){
        JSONArray portfolio = (JSONArray) user.get("portfolio");
        return portfolio;
    }


    //Checks if portfolio containsStock(user has bought this stock earlier)
    //If it has, the new price is the average, with a count += newCount
    //If not, it adds the new stock in the portfolio
    public void addToPortfoilio(String username, String ticker, float price, int count){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userArray.indexOf(findUser(username)));
        JSONArray portfolio = getPortfolio(user);
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

        float currentCash = Float.parseFloat(user.get("cash").toString());

        user.put("cash", currentCash - price * count);
        user.put("portfolio", portfolio);
        writeToFile(userArray);
    }
    public void addOrRemoveCash(String username, float money){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userArray.indexOf(findUser(username)));
        float currentCash = Float.parseFloat(user.get("cash").toString());
        user.put("cash", currentCash + money);
        writeToFile(userArray);
    }


    //Checks if the amount of stocks is valid, and then subtracts from the user
    //If one sells the full amount of stocks, the stock is removed from the portfolio
    //Adds the cash back in the account
    public void removeFromPortfolio(String username, String ticker,float price, int count){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userArray.indexOf(findUser(username)));
        JSONArray portfolio = (JSONArray) user.get("portfolio");
        JSONObject stock = new JSONObject();

        boolean containsStock = false;

        for(Object i : portfolio){
            JSONObject x = (JSONObject) i;
            if(x.get("ticker").equals(ticker)){
                containsStock = true;
                stock = x;
            }
        }
        if(containsStock){
            int newCount = Integer.parseInt(stock.get("count").toString()) - count;
            if(newCount >= 0){
                if(newCount == 0){
                    portfolio.remove(stock);
                }
                else {
                    stock.put("count", newCount);
                }
            }
            else {
                throw new IllegalArgumentException("Not enough stocks to sell");
            }
            float currentCash = Float.parseFloat(user.get("cash").toString());
            user.put("cash", currentCash + price * count);
            user.put("portfolio", portfolio);
            writeToFile(userArray);
        }
        else {
            throw new IllegalArgumentException("Stock not in portfolio");
        }
    }

    //Returns the users cash
    public float getCash(int userIndex){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(userIndex);
        return Float.parseFloat(user.get("cash").toString());
    }

/*     public void addCash(User user, float cash){
        JSONObject user = (JSONObject) userArray.get(userIndex);
        Float a = (cash + user.getCash());
    } */


    //Throws Excpetion if username doesnt exists
    //Returns null if password is incorrect
    //Returns a new instance of a user if the login is valid
    public JSONObject isLoginValid(String username, String password){
        JSONObject user = findUser(username);
        if(user != null){
            if(user.get("password").toString().equals(password)){
                return user;
            }
            else {
                throw new IllegalArgumentException("Password is incorrect");
            }
        }
        else {
            throw new IllegalArgumentException("Username not in databse");
        }
    }

    public void deleteUser(String username){
        JSONArray arr = getAllUsers();
        JSONObject user = findUser(username);
        arr.remove(user);
        writeToFile(arr);
    }

    //Writes the array to the file
    public void writeToFile(JSONArray arr){
        adaptFilePath();
        JSONObject obj = new JSONObject();
        obj.put("users", arr);
        try(FileOutputStream fileStream = new FileOutputStream(file, false)){
            Writer writer = new OutputStreamWriter(fileStream, StandardCharsets.UTF_8);
            writer.write(obj.toJSONString()); 
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
