package rest;

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
                        JSONtoPortfolio((JSONArray) user.get("watchList")),
                        false);
    }

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public User getUser(String username, String password){
        if (isLoginValid(username, password).contains("200")){
            return users.get(getUserIndex(username));
        }
        return null;
    }

    public String isLoginValid(String username, String password){
        for (User i : users){
            if (i.getUsername().equals(username)){
                if(i.getPassword().equals(password)){
                    return "200";
                }
                return "400: Password is incorrect";
            }
        }
        return "400: Username not found";
    }

    private int getUserIndex(String username){
        int index = 0;
        for(User i : users){
            if(i.getUsername().equals(username)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String newUser(String firstname, String lastname, String username, String password, Float cash, int age){
        User temp = null;
        try {
            temp = new User(firstname, lastname, username, password, cash, age, new ArrayList<Stonk>(), new ArrayList<Stonk>(),  true);
            for (User i : users){
                if (i.getUsername().equals(temp.getUsername())){
                    throw new IllegalArgumentException("Username already registered.");
                }
            }
            users.add(temp);
        }
        catch(IllegalArgumentException e){
            return "400: " + e.getMessage();
        }
        return "200";
    }

    public String buyStonks(String username, String password, String ticker, int count) {
        if (isLoginValid(username, password).contains("400")){
            return "400: User not found";
        }
        try{
            int index = getUserIndex(username);
            User temp = users.get(index);
            temp.addToPortfoilio(ticker, count);
            users.set(index, temp);
        }
        catch(IllegalArgumentException e){
            return "400: " + e.getMessage();
        }
        return "200";
    }
    
    public String sellStonks(String username, String password, String ticker, int count) {
        if (isLoginValid(username, password).contains("400")){
            return "400: User not found";
        }
        try{
            int index = getUserIndex(username);
            User temp = users.get(index);
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

    public String addStonksToWatchList(String username, String password, String ticker) {
        if (isLoginValid(username, password).contains("400")){
            return "400: User not found";
        }
        try {
            int index = getUserIndex(username);
            User temp = users.get(index);
            temp.addToWatchList(ticker, 1);
            users.set(index,temp);
        }
        catch(IllegalArgumentException e){
            return "400: " + e.getMessage();
        }
        return "200";
    }

    public String removeStonksFromWatchList(String username, String password, String ticker) {
        if (isLoginValid(username, password).contains("400")){
            return "400: User not found";
        }
        try {
            int index = getUserIndex(username);
            User temp = users.get(index);
            temp.removeFromWatchList(ticker, 1);
            users.set(index,temp);
        }
        catch(IllegalArgumentException e){
            return "400: " + e.getMessage();
        }
        return "200";
    }

    public String removeUser(String username, String password) {
        if (isLoginValid(username, password).contains("400")){
            return "400: User not found";
        }
        int index = getUserIndex(username);
        users.remove(index);
        return "200";
    }
}

