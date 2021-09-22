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
    setFirstName(firstName);
    setLastName(lastName);
    setUserName(username);
    setPassword(password);
    setCash(cash);
    setAge(age);
    setPortfolio(portfolio);
    if(isNewUser){
        handler.newUser(username, password, firstName, lastName, age, cash, new JSONArray());
    }
}
    public void setPortfolio(JSONArray portfolio){
        this.portfolio = portfolio;
    }

    public JSONArray getPortfolio(){
        DataHandler d = new DataHandler();
        return d.getPortfolio(d.findUser(username));
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
        if(username.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if(handler.findUser(username) != -1){
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
