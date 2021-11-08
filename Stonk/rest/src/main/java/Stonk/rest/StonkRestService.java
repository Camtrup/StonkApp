package Stonk.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import core.Stonk;
import core.User;
import data.DataHandler;

@Service
public class StonkRestService {

    DataHandler handler = new DataHandler();

    private ArrayList<User> users = JSONArrayToArrayList();

    private ArrayList<User> JSONArrayToArrayList(){
        ArrayList<User> temp = new ArrayList<User>();
        for(Object i : handler.getAllUsers()){
            JSONObject user = (JSONObject) i;
            temp.add(JSONtoUser(user));
        }
        return temp;
    }

    public ArrayList<Stonk> JSONtoPortfolio(JSONArray portfolio){
        ArrayList<Stonk> temp = new ArrayList<Stonk>();
        for(Object i : portfolio){
            JSONObject stonk = (JSONObject) i;
            temp.add(new Stonk(stonk.get("ticker").toString(),
                                Float.parseFloat(stonk.get("price").toString()),
                                Integer.parseInt(stonk.get("count").toString()),
                                stonk.get("name").toString(),
                                stonk.get("priceChange").toString()));
        }
        return temp;
    }

    public User JSONtoUser(JSONObject user){
        return new User(user.get("firstName").toString(), 
                        user.get("lastName").toString(), 
                        user.get("username").toString(), 
                        user.get("password").toString(), 
                        Float.parseFloat(user.get("cash").toString()), 
                        Integer.parseInt(user.get("age").toString()), 
                        JSONtoPortfolio((JSONArray) user.get("portfolio")), 
                        false);
    }

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public User isLoginValid(String username, String password){
        for (User i : users){
            if (i.getUserName().equals(username)){
                if(i.getPassword().equals(password)){
                    return i;
                }
                throw new IllegalArgumentException("Password is incorrect");
            }
        }
        throw new IllegalArgumentException("Username not found");
    }

    private int getUserIndex(String username){
        int index = 0;
        for(User i : users){
            if(i.getUserName().equals(username)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String newUser(String firstname, String lastname, String username, String password, Float cash, int age){
        User temp = null;
        try {
            temp = new User(firstname, lastname, username, password, cash, age, new ArrayList<Stonk>(), true);
            for (User i : users){
                if (i.getUserName().equals(temp.getUserName())){
                    throw new IllegalArgumentException("Username already registered.");
                }
            }
            users.add(temp);
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
            return "400: " + e.getMessage();
        }
        return "200";
    }

    public String buyStonks(String username, String password, String ticker, int count) {
        try{
            User temp = isLoginValid(username, password);
            int index = getUserIndex(username);
            temp.addToPortfoilio(ticker, count);
            users.set(index, temp);
        }
        catch(IllegalArgumentException e){
            return "400: " + e.getMessage();
        }
        return "200";
    }
    
    public String sellStonks(String username, String password, String ticker, int count) {
        try{
            User temp = isLoginValid(username, password);
            int index = getUserIndex(username);
            temp.removeFromPortfolio(ticker, count);
            users.set(index, temp);
        }
        catch(IllegalArgumentException e){
            return "400: " + e.getMessage();
        }
        return "200";
    }



    public String saveJSON(){
        Gson gson =  new GsonBuilder().create();
        JsonArray arr = gson.toJsonTree(users).getAsJsonArray();
        System.out.println(arr.toString());
        try {
            handler.writeToFile(arr.toString());
        }
        catch(FileNotFoundException e){
            return "400" + e.getMessage();
        } catch (IOException e) {
            return "400" + e.getMessage();
        }
        return "200";
    }
}

