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
        JSONParser parser = new JSONParser();
        Object fileReaderObj = null;
        try {
            fileReaderObj = parser.parse(new FileReader("Stonk/src/main/resources/app/database.json"));
        } catch (IOException | ParseException e1) {
            e1.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) fileReaderObj;
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
    public User getUser(int index){
        JSONArray userArray = getAllUsers();
        JSONObject user = (JSONObject) userArray.get(index);

        return new User(user.get("firstname").toString(), 
                        user.get("lastname").toString(), 
                        user.get("username").toString(), 
                        user.get("password").toString(), 
                        Integer.parseInt(user.get("cash").toString()), 
                        Integer.parseInt(user.get("age").toString()),
                        null);
    }

    public void addStock(String ticker, float price, int count){
        
    }

    public static void main(String[] args){
        DataHandler d = new DataHandler();
        System.out.println(d.findUser("Casper"));
    }
}
