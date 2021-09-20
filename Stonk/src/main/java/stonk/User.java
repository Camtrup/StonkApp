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


public User(String firstName, String lastName, String username, String password, float cash, int age, JSONArray portfolio){
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.cash = cash;
    this.age = age;
    this.portfolio = new JSONArray();
}

    public void setFirstName(String firstName){
    this.firstName = firstName;
    } 

    public void setLastName(String lastName){
    this.lastName = lastName;
    } 

    public void setUserName(String name){
    this.username = name;
    } 

    public void setPassword(String password){
    this.password = password;
    } 

    public void setCash(int cash){
        this.cash = cash;
        } 

    public void setAge(int age){
        if (age<18){
            throw new IllegalArgumentException("You need to be over 18 years");

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
    var user = new User("Karan", "Singh", "XXPussyDestroyerXX", "password", 98765438, 20,null);
    System.out.println(user);
}


}
