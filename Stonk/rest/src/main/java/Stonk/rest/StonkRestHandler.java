package Stonk.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import data.DataHandler;

public class StonkRestHandler {
    DataHandler handler = new DataHandler();
    
    public JSONArray getUserArray(){
        return handler.getAllUsers();
    }

    public void writeToFile(JSONArray userArray){
        handler.writeToFile(userArray);
    }

    public void save(JSONObject user){
        JSONArray userArray = getUserArray();
        if(user.containsKey(user)){
            
        }
        else{
            userArray.add(user);
            writeToFile(userArray);
        }

    }

    public JSONObject findUser(String username, JSONArray users){
        for(Object i : users){
            JSONObject temp = (JSONObject) i;
            if (temp.get("username").toString().equals(username)){
                return temp;
            }
        }
        return null;
    }

    public JSONObject isLoginValid(String username, String password, JSONArray users){
        JSONObject user = findUser(username, users);
        if (!user.equals(null)){
            if(user.get("password").equals(password)){
                return user;
            }
            else{
                throw new IllegalArgumentException("Password is incorrect");
            }
        }
        else {
            throw new IllegalArgumentException("Username not found");
        }
    }
}
