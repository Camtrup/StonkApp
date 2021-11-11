package core;

import java.nio.charset.Charset;
import java.security.MessageDigest;
//encryption imports
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;  

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
  private ArrayList<Stonk> portfolio = new ArrayList<Stonk>();
  private ArrayList<Stonk> watchList = new ArrayList<Stonk>();

  /**
   * Constructor.
   *
   * @param firstName string
   * @param lastName string
   * @param username string
   * @param password string
   * @param cash float
   * @param age int
   * @param portfolio ArrayList of type Stonk
   * @param watchList Arraylist of type Stonk
   * @param isNewUser boolean
   */
  public User(String firstName, String lastName, String username, String password,
      float cash, int age, ArrayList<Stonk> portfolio, ArrayList<Stonk> watchList, boolean isNewUser) {
    if (isNewUser) {
      setFirstName(firstName);
      setLastName(lastName);
      setUserName(username);
      setPassword(password);
      addCash(cash);
      setAge(age);
      setPortfolio(portfolio);
      setWatchList(watchList);
    } else {
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.password = password;
      this.cash = cash;
      this.age = age;
      setPortfolio(portfolio);
      setWatchList(watchList);
    }
  }
  
  //Used for login
  public User(String username, String password){
      this.username = username;
      setPassword(password);
  }

  private void setWatchList(ArrayList<Stonk> watchList2) {
    this.watchList = new ArrayList<Stonk>(watchList2);
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
    Stonk stock = new Stonk(ticker, count);
    boolean isOwned = false;
    for(Stonk i : portfolio){
      if (i.getTicker().equals("ticker")){
        i.setNewAverage(stock);
        isOwned = true;
      }
    }
    if(!isOwned){
      portfolio.add(stock);
    }
    addCash(stock.getPrice()*stock.getCount());
  
  }
  
  public void addToWatchList(String ticker, int count) {
    Stonk stock = new Stonk(ticker, count);
    for (Stonk i : watchList){
      if (i.getTicker().equals(ticker)){
        throw new IllegalArgumentException("Stock is already in watchlist");
      }
    }
    watchList.add(stock);
  }
  
  public void removeFromWatchList(String ticker, int count){
    boolean isInList = false;
    if(watchList.size() == 0){
      throw new IllegalArgumentException("List is empty");
    }
    for (Stonk i : getWatchList()){
      if (i.getTicker().equals(ticker)){
        watchList.remove(i);
        isInList = true;
      }
    }
    if(!isInList){
      throw new IllegalArgumentException("Stock not in watchlist");
    }
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
    Stonk stock = new Stonk(ticker, count);
    boolean isOwned = false;
    for(Stonk i : getPortfolio()){
      if(i.getTicker().equals(ticker)){
        isOwned = true;
        if(count > i.getCount()){
          throw new IllegalArgumentException("Not enough stocks to sell");
        }
        else if (count == i.getCount()){
          portfolio.remove(i);
        }
        else {
          i.setNewCount(stock);
        }
      }
    }
    if (!isOwned){
      throw new IllegalArgumentException("Stock is not in portfolio");
    }
    removeCash(stock.getPrice()*stock.getCount());
  }

  public ArrayList<Stonk> getPortfolio() {
    return new ArrayList<Stonk>(portfolio);
  }

  private void setPortfolio(ArrayList<Stonk> portfolio){
    this.portfolio = new ArrayList<Stonk>(portfolio);
  }
  public ArrayList<Stonk> getWatchList() {
    return new ArrayList<>(watchList);
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
  }

  /**
   * Adds money.
   *
   * @param cash how much.
   */
  public void addCash(float cash) {
    if (cash < 0) {
      throw new IllegalArgumentException("Cant add a negative amount");
    }
    this.cash += cash;
  }

  /**
   * Removes money.
   *
   * @param cash how much.
   */
  private void removeCash(float cash) {
    if (cash < 0) {
      throw new IllegalArgumentException("Cannot remove a negative amount");
    }
    if (cash > this.cash){
      throw new IllegalArgumentException("Not enough cash");
    }
    this.cash -= cash;
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
    return encryptedPassword;
  }

  public String getUsername() {
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
}