package stonk;

import java.io.IOException;

import org.json.simple.JSONArray;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class registerController {
    private Stage stage; 
    private Scene scene; 
    private User user;
    private int userIndex;


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
        app.changeScene("mainPage.fxml", user.getUserName());
    }

    @FXML
    public void registerUser() throws IOException{
        if(username.getText().isBlank() || password.getText().isBlank() || firstname.getText().isBlank() || lastname.getText().isBlank() || age.getText().isBlank()|| cash.getText().isBlank()){
            throw new IllegalArgumentException("You must fill out all inputfields");
        }
        else {
            DataHandler dataHandler = new DataHandler();

            try {
                dataHandler.newUser(username.getText(), password.getText(), firstname.getText(), lastname.getText(), Integer.parseInt(age.getText()), Integer.parseInt(cash.getText()), new JSONArray());
                user = dataHandler.isLoginValid(username.getText(), password.getText());
                System.out.println(user);
                loginFromRegister();
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }
    
}
