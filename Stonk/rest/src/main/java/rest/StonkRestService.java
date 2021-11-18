package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import core.Stonk;
import core.User;
import data.DataHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Class RestService.
 */
@Service
public class StonkRestService {

  DataHandler handler = new DataHandler();

  private ArrayList<User> users = jsonArrayToArrayList();

  /**
   * User to jsonArrayList.
   *
   * @return the arrayList of user.
   */
  private ArrayList<User> jsonArrayToArrayList() {
    ArrayList<User> temp = new ArrayList<User>();
    for (Object i : handler.getAllUsers()) {
      JSONObject user = (JSONObject) i;
      temp.add(jsonToUser(user));
    }
    return temp;
  }

  /**
   * portfolio.
   *
   * @param portfolio given.
   * @return as arraylist with Stonk.
   */
  public ArrayList<Stonk> jsonToPortfolio(JSONArray portfolio) {
    ArrayList<Stonk> temp = new ArrayList<Stonk>();
    for (Object i : portfolio) {
      JSONObject stonk = (JSONObject) i;
      temp.add(new Stonk(stonk.get("ticker").toString(),
          Float.parseFloat(stonk.get("price").toString()),
          Integer.parseInt(stonk.get("count").toString()), stonk.get("name").toString(),
          stonk.get("priceChange").toString()));
    }
    return temp;
  }

  /**
   * From JSONObject to user.
   *
   * @param user for finding.
   * @return the gives user.
   */
  public User jsonToUser(JSONObject user) {
    return new User(user.get("firstName").toString(), user.get("lastName").toString(),
        user.get("username").toString(),
        user.get("password").toString(), Float.parseFloat(user.get("cash").toString()),
        Integer.parseInt(user.get("age").toString()),
        jsonToPortfolio((JSONArray) user.get("portfolio")),
        jsonToPortfolio((JSONArray) user.get("watchList")), false);
  }

  protected ArrayList<User> getAllUsers() {
    return users;
  }

  /**
   * Gets a user.
   *
   * @param username a string.
   * @param password a string.
   * @return the user or null.
   */
  public User getUser(String username, String password) {
    if (isLoginValid(username, password).contains("200")) {
      return users.get(getUserIndex(username));
    }
    return null;
  }

  /**
   * Check if login is valid.
   *
   * @param username a string.
   * @param password a string.
   * @return a response.
   */
  public String isLoginValid(String username, String password) {
    for (User i : users) {
      if (i.getUsername().equals(username)) {
        if (i.getPassword().equals(password)) {
          return "200";
        }
        return "400: Password is incorrect";
      }
    }
    return "400: Username not found";
  }

  /**
   * Gets the current users index.
   *
   * @param username for finding user.
   * @return the index or -1
   */
  private int getUserIndex(String username) {
    int index = 0;
    for (User i : users) {
      if (i.getUsername().equals(username)) {
        return index;
      }
      index++;
    }
    return -1;
  }

  /**
   * New user.
   *
   * @param firstname a string.
   * @param lastname a string.
   * @param username a string.
   * @param password a string.
   * @param cash float for how much cash.
   * @param age int.
   * @return a response.
   */
  public String newUser(String firstname, String lastname,
      String username, String password, Float cash, int age) {
    User temp = null;
    try {
      temp = new User(firstname, lastname, username, password, cash, age, new ArrayList<Stonk>(),
          new ArrayList<Stonk>(), true);
      for (User i : users) {
        if (i.getUsername().equals(temp.getUsername())) {
          throw new IllegalArgumentException("Username already registered.");
        }
      }
      users.add(temp);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return "401: " + e.getMessage();
    }
    return "200";
  }

  /**
   * Buy a Stock.
   *
   * @param username a string.
   * @param password a string.
   * @param ticker a string for finding stock.
   * @param count how many.
   * @return a response.
   */
  public String buyStonks(String username, String password, String ticker, int count) {
    if (isLoginValid(username, password).contains("400")) {
      return "400: User not found";
    }
    try {
      int index = getUserIndex(username);
      User temp = users.get(index);
      temp.addToPortfoilio(ticker, count);
      users.set(index, temp);
      if (count < 0){
        throw new IllegalArgumentException("Enter a positive number");
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return "406: " + e.getMessage();
    }
    return "200";
  }

  /**
   * Add more cash to user.
   *
   * @param username a string.
   * @param password a string.
   * @param cash a float.
   * @return a response.
   */
  public String addMoreCash(String username, String password, Float cash) {
    if (isLoginValid(username, password).contains("400")) {
      return "400: User not found";
    }
    int index = getUserIndex(username);
    User temp = users.get(index);
    temp.addCash(cash);
    users.set(index, temp);
    return "200";
  }

  /**
   * Sell a Stock.
   *
   * @param username a string.
   * @param password a string.
   * @param ticker a string.
   * @param count int for how many.
   * @return a response.
   */
  public String sellStonks(String username, String password, String ticker, int count) {
    if (isLoginValid(username, password).contains("400")) {
      return "400: User not found";
    }
    try {
      int index = getUserIndex(username);
      User temp = users.get(index);
      temp.removeFromPortfolio(ticker, count);
      users.set(index, temp);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return "405: " + e.getMessage();
    }
    return "200";
  }

  /**
   * Save to JSON file.
   *
   * @return a response.
   */
  public String saveJson() {
    Gson gson = new GsonBuilder().create();
    JsonArray arr = gson.toJsonTree(users).getAsJsonArray();
    try {
      handler.writeToFile(arr.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return "408" + e.getMessage();
    } catch (IOException e) {
      e.printStackTrace();
      return "409" + e.getMessage();
    }
    return "200";
  }

  /** Add a Stock to WatchList.
   *
   * @param username String.
   * @param password String.
   * @param ticker String.
   * @return a response.
   */
  public String addStonksToWatchList(String username, String password, String ticker) {
    if (isLoginValid(username, password).contains("400")) {
      return "400: User not found";
    }
    try {
      int index = getUserIndex(username);
      User temp = users.get(index);
      temp.addToWatchList(ticker, 1);
      users.set(index, temp);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return "404: " + e.getMessage();
    }
    return "200";
  }

  /**
   * Remove a Stock from StockList.
   *
   * @param username String.
   * @param password String.
   * @param ticker String.
   * @return a response.
   */
  public String removeStonksFromWatchList(String username, String password, String ticker) {
    if (isLoginValid(username, password).contains("400")) {
      return "400: User not found";
    }
    try {
      int index = getUserIndex(username);
      User temp = users.get(index);
      temp.removeFromWatchList(ticker, 1);
      users.set(index, temp);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return "403: " + e.getMessage();
    }
    return "200";
  }

  /**
   * Removes the user.
   *
   * @param username String.
   * @param password String.
   * @return A response.
   */
  public String removeUser(String username, String password) {
    if (isLoginValid(username, password).contains("400")) {
      return "400: User not found";
    }
    int index = getUserIndex(username);
    users.remove(index);
    return "200";
  }
}
