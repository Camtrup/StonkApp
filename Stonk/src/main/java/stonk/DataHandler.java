package stonk;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataHandler {
    
    public void newUser(String username, String password, String firstname, String lastname) throws ParseException {
        String jsonString = "{\"users\": []}";
        JSONObject user = new JSONObject();
        user.put("username",username);
        user.put("password",password);
        user.put("firstname",firstname);
        user.put("lastname",lastname);
        user.put("cash","");
        user.put("lists","");
        JSONObject userSuper = new JSONObject();
        String userCountName = "user" + (getUserCount()+1);
        userSuper.put(userCountName, user);
       
        try (FileWriter file = new FileWriter("Stonk/src/main/resources/app/database.json")){
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            JSONArray arr = (JSONArray) obj.get("users");
            arr.add(userSuper);
            file.write(arr.toJSONString());
            file.flush();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public JSONArray getAllUsers(){
        JSONArray arr = new JSONArray();
        return arr;
    }

    public int getUserCount(){
        return 0;
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataHandler d = new DataHandler();
        d.newUser("casper", "12345", "Casper", "SASAS");
    }
}
