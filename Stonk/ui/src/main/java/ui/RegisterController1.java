package ui;
import core.User;

import java.io.IOException;

import org.json.simple.JSONArray;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController1 {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private TextField cash;

    @FXML
    private Button registerUser;
     

    public void loginFromRegister() throws IOException{
        StonkApp1 app = new StonkApp1();
        app.changeScene("mainPage.fxml");
    }

    @FXML
    public void registerUser() throws IOException{
        int tempInt = -1;
        float tempFloat = -1;
        try {
            tempInt = Integer.parseInt(age.getText());
            tempFloat = Float.parseFloat(cash.getText());
            StonkApp1.user = new User(firstname.getText(), lastname.getText(), username.getText(), password.getText(), Float.parseFloat(cash.getText()), Integer.parseInt(age.getText()), new JSONArray(), true);
            loginFromRegister(); 
        }
        catch(Exception e) {
            if(tempInt == -1){
                System.out.println(new IllegalArgumentException("Age must be an integer"));
            }
            else if(tempFloat == -1) {
                System.out.println(new IllegalArgumentException("Cash must be a number"));
            }
            else{
                System.out.println(e);
            }
        }
    }
    
    
}
