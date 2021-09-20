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
    
    public void newUser(String username, String password, String firstname, String lastname, int age, int cash) {
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
        user.put("lists","");

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

    public int findUser(String username) {
        JSONArray userArray = getAllUsers();
        int count = 0;
        for(Object i : userArray){
            JSONObject user = (JSONObject) i;
            if(user.get("username")==username){
                return count;
            }
            count++;
        }
        return getUserCount() + 1;
    }

    public User getUser(int index){
        return new User("","","","", index, index);
    }
    public static void main(String[] args){
        DataHandler d = new DataHandler();
        d.findUser("username");
    }
}
