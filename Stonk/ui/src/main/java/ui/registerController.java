package ui;
import core.User;

import java.io.IOException;

import org.json.simple.JSONArray;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class registerController {
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
        StonkApp app = new StonkApp();
        app.changeScene("mainPage.fxml");
    }

    @FXML
    public void registerUser() throws IOException{
        if(username.getText().isBlank() || password.getText().isBlank() || firstname.getText().isBlank() || lastname.getText().isBlank() || age.getText().isBlank()|| cash.getText().isBlank()){
            throw new IllegalArgumentException("You must fill out all inputfields");
        }
        else {
            try {
                StonkApp.user = new User(firstname.getText(), lastname.getText(), username.getText(), password.getText(), Float.parseFloat(cash.getText()), Integer.parseInt(age.getText()), new JSONArray(), true);
                loginFromRegister(); 
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }
    
}
