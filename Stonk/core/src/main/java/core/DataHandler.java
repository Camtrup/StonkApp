package core;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataHandler {

    //Since the app runs from the pom.xml in the module ui. It seems that this had to be the natural position to put the databse
    private String filePath = "src/main/resources/ui/database.json";

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

    public int getUserCount(){
        return getAllUsers().size();
    }

    //Array of all users
    public JSONArray getAllUsers(){
        JSONParser parser = new JSONParser();
        JSONArray userArray = new JSONArray();
        try(FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8)){
            JSONObject obj = (JSONObject) parser.parse(reader);
            userArray = (JSONArray) obj.get("users");
        }
        
        catch(IOException | ParseException e){
            System.out.println("det er min feil");
            System.out.println(e);
        }
        return userArray;
    }

    //Returns the index of a given username in the database
    //If the username is not found, then return -1
    public int findUser(String username) {
        JSONArray userArray = getAllUsers();
        int count = 0;
        for(Object i : userArray){
            JSONObject user = (JSONObject) i;
            if(username.equals(user.get("username").toString())){
                return count;
            }
            count++;
        }
        return -1;
    }

    public User generateUser(String username){
        JSONObject user = getUser(findUser(username));
        return new User(user.get("firstname").toString(),
                                user.get("lastname").toString(),
                                user.get("username").toString(),
                                user.get("password").toString(),
                                Float.parseFloat(user.get("cash").toString()),
                                Integer.parseInt(user.get("age").toString()),
                                (JSONArray) user.get("portfolio"),
                                false);

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

    public void setCash(int userIndex, float cash){
        JSONArray arr = getAllUsers();
        JSONObject user = (JSONObject) arr.get(0);
        user.put("cash", cash);
        writeToFile(arr);
    }

    //Throws Excpetion if username doesnt exists
    //Returns null if password is incorrect
    //Returns a new instance of a user if the login is valid
    public User isLoginValid(String username, String password){
        int index = findUser(username);
        if(index >= 0){
            JSONObject user = getUser(index);
            if(user.get("password").toString().equals(password)){
                return new User(user.get("firstname").toString(),
                                user.get("lastname").toString(),
                                user.get("username").toString(),
                                user.get("password").toString(),
                                Float.parseFloat(user.get("cash").toString()),
                                Integer.parseInt(user.get("age").toString()),
                                (JSONArray) user.get("portfolio"),
                                false);
            }
            else {
                throw new IllegalArgumentException("Password is incorrect");
            }
        }
        else {
            throw new IllegalArgumentException("Username not in databse");
        }
    }

    public void deleteUser(int userIndex){
        JSONArray arr = getAllUsers();
        arr.remove(userIndex);
        writeToFile(arr);
    }

    //Writes the array to the file
    public void writeToFile(JSONArray arr){
        JSONObject obj = new JSONObject();
        obj.put("users", arr);
        try(FileOutputStream fileStream = new FileOutputStream(filePath, false)){
            Writer writer = new OutputStreamWriter(fileStream, StandardCharsets.UTF_8);
            writer.write(obj.toJSONString()); 
            writer.close();
        }
        

       // try(FileWriter file = new FileWriter(filePath, false)) {
         //   file.write(obj.toJSONString());
           // file.close();
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DataHandler d = new DataHandler();
        d.setCash(0, 10);
        
        
    }
}
