package ui;

import java.io.IOException;

import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button login; 
    @FXML
    private Button register; 
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    


    public void login() throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("mainPage.fxml");

    }

    @FXML
    public void isLoginValid() {
        try {
            User temp = new User();
            StonkApp.setStaticUser(temp.isLoginValid(username.getText().toString(), password.getText().toString()));
            login();
        }
    
        catch(IllegalArgumentException | IOException e){
            System.out.println(e);
        } 
    }

    public void registerUserNew() throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("newUser.fxml");
    }

}
