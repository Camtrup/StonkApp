package stonk;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataHandler {
    
    public void newUser(String username, String password, String firstname, String lastname) throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        Object fileReaderObj = parser.parse(new FileReader("Stonk/src/main/resources/app/database.json"));
        JSONObject jsonObject = (JSONObject) fileReaderObj;
        JSONArray userArray = getAllUsers();
        
        JSONObject user = new JSONObject();

        user.put("username",username);
        user.put("password",password);
        user.put("firstname",firstname);
        user.put("lastname",lastname);
        user.put("cash","");
        user.put("lists","");

        JSONObject userSuper = new JSONObject();
        String userCount = "user" + (getUserCount()+1);

        userSuper.put(userCount, user);

        userArray.add(userSuper);
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

    public JSONArray getAllUsers() throws FileNotFoundException, IOException, ParseException{
        JSONObject jsonObject = (JSONObject)new JSONParser().parse(new FileReader("Stonk/src/main/resources/app/database.json"));
        JSONArray userArray = (JSONArray) jsonObject.get("users");
        return userArray;
    }

    public int getUserCount() throws FileNotFoundException, IOException, ParseException{
        JSONObject jsonObject = (JSONObject)new JSONParser().parse(new FileReader("Stonk/src/main/resources/app/database.json"));
        JSONArray userArray = (JSONArray) jsonObject.get("users");
        return userArray.size();
    }

    public int findUser(String username) throws FileNotFoundException, IOException, ParseException{
        JSONArray userArray = getAllUsers();
        for(int i = 0; i < getUserCount(); i++){
            JSONObject x = (JSONObject) userArray.get(i).get("")
        }


        return (Integer) null;
    }

    public User getUser(int index){
        return new User();
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataHandler d = new DataHandler();
        d.newUser("casper", "12345", "Casper", "SASAS");
    }
}
