package ui;

import java.io.IOException;

import core.DataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class loginController {

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
    public void isLoginValid() throws IOException {
        DataHandler dataHandler = new DataHandler();
        try {
            StonkApp.user = dataHandler.isLoginValid(username.getText().toString(), password.getText().toString());
            if(StonkApp.user.equals(null)){
                throw new IllegalArgumentException("Password is incorrect");
            }
            else{
            login();
            }
        }
    
        catch(IllegalArgumentException e){
            System.out.println(dataHandler);
            System.out.println(e);
        } 
    }

    public void registerUserNew() throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("newUser.fxml");
    }

}
