package core;

import data.DataHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//encryption imports
import java.security.NoSuchAlgorithmException;
import java.nio.charset.Charset;
import java.security.MessageDigest;  

/**
 * Class user.
 */
public class User {

  public float cash;
  private int age;
  private String firstName;
  private String lastName;
  private String password;
  private String username;
  // private JSONArray portfolio; Unused

  public DataHandler handler = new DataHandler();

  /**
   * Constructor.
   *
   * @param firstName string
   * @param lastName string
   * @param username string
   * @param password string
   * @param cash float
   * @param age int
   * @param portfolio JSONArray
   * @param isNewUser boolean
   */
  public User(String firstName, String lastName, String username, String password,
      float cash, int age, JSONArray portfolio, boolean isNewUser) {
    if (isNewUser) {
      setFirstName(firstName);
      setLastName(lastName);
      setUserName(username);
      setPassword(encryptPassword(password));
      setCash(cash);
      setAge(age);
      // this.portfolio = getPortfolio(); bruker vi den?
      handler.newUser(username, encryptPassword(password), firstName, lastName, age, cash, new JSONArray());
    } else {
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.password = password;
      this.cash = cash;
      this.age = age;
    }
  }

  public User() {
    firstName = "This is a dummy, used as temp";
  }

  /**
   * Adds stock to portfolio.
   *
   * @param ticker for finding stock.
   * @param count how many.
   */
  public void addToPortfoilio(String ticker, int count) {
    if (count <= 0) {
      throw new IllegalArgumentException("Amount of stocks cant be negative or 0");
    }
    Stonk stock = new Stonk();
    stock.getStockInfo(ticker);
    setCash(cash - (stock.getPrice() * count));
    handler.addToPortfoilio(username, ticker, stock.getPrice(), count);
  }

  /**
   * Removes from portfolio.
   *
   * @param ticker for finding stock.
   * @param count how many.
   */
  public void removeFromPortfolio(String ticker, int count) {
    if (count <= 0) {
      throw new IllegalArgumentException("Amount of stocks cant be negative or 0");
    }
    Stonk stock = new Stonk();
    stock.getStockInfo(ticker);
    handler.removeFromPortfolio(username, ticker, stock.getPrice(), count);
    setCash(cash + (stock.getPrice() * count));
  }

  public JSONArray getPortfolio() {
    return handler.getPortfolio(handler.findUser(username));
  }

  /**
   * Sets first name.
   *
   * @param firstName new firstName.
   */
  public void setFirstName(String firstName) {
    if (firstName.isBlank()) {
      throw new IllegalArgumentException("First name cannot be blank");
    }
    this.firstName = firstName;
  }

  /**
   * Sets last name.
   *
   * @param lastName new lastName.
   */
  public void setLastName(String lastName) {
    if (lastName.isBlank()) {
      throw new IllegalArgumentException("Last name cannot be blank");
    }
    this.lastName = lastName;
  }

  /**
   * Sets username.
   *
   * @param name new username.
   */
  public void setUserName(String name) {
    if (name.isBlank()) {
      throw new IllegalArgumentException("Username cannot be blank");
    }
    if (handler.findUser(name) != null) {
      throw new IllegalArgumentException("Username is already registered");
    }
    this.username = name;
  }

  /**
   * Sets password.
   *
   * @param password new password.
   */
  private void setPassword(String password) {
    if (password.isBlank()) {
      throw new IllegalArgumentException("Password cannot be blank");
    }
    this.password = encryptPassword(password);
    System.out.println("encrypted password:" + this.password);
  }

  /**
   * Sets the cash.
   *
   * @param cash how much.
   */
  private void setCash(float cash) {
    if (cash < 0) {
      throw new IllegalArgumentException("Cant set a negative balance");
    }
    this.cash = cash;
  }

  /**
   * Adds money.
   *
   * @param cash how much.
   */
  public void addMoney(float cash) {
    if (cash < 0) {
      throw new IllegalArgumentException("Cant set a negative balance");
    }
    this.cash += cash;
    handler.addOrRemoveCash(getUserName(), cash);
  }

  /**
   * Removes money.
   *
   * @param cash how much.
   */
  public void removeMoney(float cash) {
    if (cash < 0) {
      throw new IllegalArgumentException("Cant set a positive number");
    }
    this.cash -= cash;
    handler.addOrRemoveCash(getUserName(), cash);
  }

  /**
   * Sets age. 
   *
   * @param age int.
   */
  public void setAge(int age) {
    if (age < 18) {
      throw new IllegalArgumentException("You need to be over 18 years");
    }
    if (String.valueOf(age).isEmpty()) {
      throw new IllegalArgumentException("Age cannot be empty");
    }
    this.age = age;
  }

  /**
   * Checks if login is valid.
   *
   * @param username string.
   * @param password string.
   * @return the gives user if valid.
   */
  public User isLoginValid(String username, String password) {
    String hashedPassword = encryptPassword(password);
    JSONObject temp = handler.isLoginValid(username, hashedPassword);
    System.out.println("This is the password: " + hashedPassword);
    return new User(temp.get("firstname").toString(),
        temp.get("lastname").toString(), temp.get("username").toString(),
        temp.get("password").toString(), Float.parseFloat(temp.get("cash").toString()),
        Integer.parseInt(temp.get("age").toString()), (JSONArray) temp.get("portfolio"), false);
  }

  public String encryptPassword(String password) {
    String tempPassword = password;
    String encryptedPassword = null;
    try{
      MessageDigest m = MessageDigest.getInstance("MD5");
      m.update(tempPassword.getBytes(Charset.forName("UTF-8")));
      byte[] bytes = m.digest(); 
      StringBuilder s = new StringBuilder();  
      for(int i=0; i< bytes.length ;i++)  {  
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
        }
      encryptedPassword = s.toString(); 
    }
    catch (NoSuchAlgorithmException e){
      e.printStackTrace();
    }
    System.out.println("Plain-text password: " + tempPassword);  
    System.out.println("Encrypted password using MD5: " + encryptedPassword);  
    return encryptedPassword;
  }

  public String getUserName() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public float getCash() {
    return cash;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Hello " + username + " you have " + cash + " dollars in your account. Happy trading!!";
  }

  /**
   * Main.
   *
   * @param args .
   */
  public static void main(String[] args) {
    User u = new User();
    u.setCash(2000);
    // u.handler.addCash(u, 500);
    System.out.println(u.getCash());
    u.encryptPassword("12345");
  }

}
