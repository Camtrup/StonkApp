package stonk;

import org.json.simple.JSONArray;

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
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.firstName = firstName;
    } 

    public void setLastName(String lastName){
        if(lastName.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.lastName = lastName;
    } 

    public void setUserName(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if(handler.findUser(name) != -1){
            throw new IllegalArgumentException("Username is already registered");
        }
        this.username = name;
    } 

    public void setPassword(String password){
        if(password.isBlank()){
            throw new IllegalArgumentException("Password cannot be blank");
        }
        this.password = password;
    } 

    public void setCash(float cash){
        if(cash < 0){
            throw new IllegalArgumentException("Cant set a negative balance");
        }
        this.cash = cash;
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


    public String getUserName()
    {
        return username;
    } 


    public String getPassword()
    {
        return password;
    } 


    public String getFirstName()
    {
        return firstName;
    } 

    public String getLastName(){
        return lastName;
    } 
    public float getCash(){
        DataHandler d = new DataHandler();
        return d.getCash(d.findUser(username));
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
