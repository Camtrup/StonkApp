package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import data.DataHandler;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private float cash;
    private int age;
    private JSONArray portfolio;
    DataHandler handler = new DataHandler();


public User(String firstName, String lastName, String username, String password, float cash, int age, JSONArray portfolio, boolean isNewUser){
    if(isNewUser){
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(username);
        setPassword(password);
        setCash(cash);
        setAge(age);
        this.portfolio = portfolio;
        handler.newUser(username, password, firstName, lastName, age, cash, new JSONArray());
    }
    else {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.age = age;
        this.portfolio = portfolio;
    }
}

    public User(){
        firstName = "This is a dummy, used as temp";
    }

    public void addToPortfoilio(String ticker, int count){
        if(count <= 0){
            throw new IllegalArgumentException("Amount of stocks cant be negative or 0");
        }
        Stonk stock = new Stonk();
        stock.getStockInfo(ticker);
        setCash(cash - (stock.getPrice() * count));
        handler.addToPortfoilio(handler.findUser(username), ticker, stock.getPrice(), count);
    }

    public void removeFromPortfolio(String ticker, int count){
        if(count <= 0){
            throw new IllegalArgumentException("Amount of stocks cant be negative or 0");
        }
        Stonk stock = new Stonk();
        stock.getStockInfo(ticker);
        stock.getPrice();
        handler.removeFromPortfolio(handler.findUser(username), ticker, count);
        setCash(cash + (stock.getPrice() * count));
    }



    public JSONArray getPortfolio(){
        return handler.getPortfolio(handler.findUser(username));
    }

    public void setFirstName(String firstName){
        if(firstName.isBlank()){
            throw new IllegalArgumentException("First name cannot be blank");
        }
        this.firstName = firstName;
    } 

    public void setLastName(String lastName){
        if(lastName.isBlank()){
            throw new IllegalArgumentException("Last name cannot be blank");
        }
        this.lastName = lastName;
    } 

    public void setUserName(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if(handler.findUser(name) != -1){
            throw new IllegalArgumentException("Username is already registered");
        }
        this.username = name;
    } 

    private void setPassword(String password){
        if(password.isBlank()){
            throw new IllegalArgumentException("Password cannot be blank");
        }
        this.password = password;
    } 

    private void setCash(float cash){
        if(cash < 0){
            throw new IllegalArgumentException("Cant set a negative balance");
        }
        this.cash = cash;
        if (handler.findUser(username)!=-1){
            handler.setCash(handler.findUser(username), cash);
        }
        } 

    public void setAge(int age){
        if (age<18){
            throw new IllegalArgumentException("You need to be over 18 years");
        }
        if(String.valueOf(age).isEmpty()){
            throw new IllegalArgumentException("Age cannot be empty");
        }
        this.age = age;
        } 

    public User isLoginValid(String username, String password){
        JSONObject temp = handler.isLoginValid(username, password);
        return new User(temp.get("firstname").toString(),
                        temp.get("lastname").toString(),
                        temp.get("username").toString(),
                        temp.get("password").toString(),
                        Float.parseFloat(temp.get("cash").toString()),
                        Integer.parseInt(temp.get("age").toString()),
                        (JSONArray) temp.get("portfolio"),
                        false);
    }

    public String getUserName(){
        return username;
    } 


    public String getPassword(){
        return password;
    } 


    public String getFirstName(){
        return firstName;
    } 

    public String getLastName(){
        return lastName;
    } 
    public float getCash(){
        return cash;
    } 

    public int getAge(){
        return age;
    }

	@Override
	public String toString() {
        return "Hello " + username + " you have " + cash + " dollars in your account. Happy trading!!"; 
    }

public static void main(String[] args) {
}
}
