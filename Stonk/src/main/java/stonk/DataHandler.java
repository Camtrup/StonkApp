package stonk;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataHandler {
    
    //Creates new user in the database
    public void newUser(String username, String password, String firstname, String lastname, int age, int cash, JSONArray portfolio) {
        JSONObject jsonObject = new JSONObject();
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
        jsonObject.put("users", userArray);
       
        try (FileWriter file = new FileWriter("Stonk/src/main/resources/app/database.json", false)){
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    //Array of all users
    public JSONArray getAllUsers(){
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject)new JSONParser().parse(new FileReader("Stonk/src/main/resources/app/database.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray userArray = (JSONArray) jsonObject.get("users");
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

    public JSONArray getPortfolio(int userIndex){
        JSONObject user = (JSONObject) getAllUsers().get(userIndex);
        JSONArray portfolio = (JSONArray) user.get("portfolio");
        return portfolio;
    }

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

    public void addToPortfoilio(int userIndex, String ticker, float price, int count){
        JSONArray userArray = getAllUsers();
        System.out.println(userArray.get(userIndex).toString());
        JSONArray portfolio = getPortfolio(userIndex);
        JSONObject stock = new JSONObject();
        stock.put("ticker", ticker);
        stock.put("price", price);
        stock.put("count", count);
        portfolio.add(stock);

        for(int i = 0; i < getUserCount(); i ++){
            JSONObject x = (JSONObject) userArray.get(i);
            if(x.equals(getUser(userIndex))){
                System.out.println("yeet");
                x.put("portfolio", portfolio);
            }
        }
        System.out.println(userArray.get(userIndex).toString());
    }

    public void removeFromPortfolio(int userIndex, String ticker){

    }

    public static void main(String[] args){
        DataHandler d = new DataHandler();
        d.addToPortfoilio(0, "gme", 202, 3);
        System.out.println(d.findUser("Mattis"));
    }
}
