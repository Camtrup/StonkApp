package stonk;

import java.io.IOException;

import org.json.simple.JSONArray;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class loginController {


    private Stage stage; 
    private Scene scene; 
    private User user;
    private int userIndex;


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
            user = dataHandler.isLoginValid(username.getText().toString(), password.getText().toString());
            userIndex = dataHandler.findUser(username.getText());
            if(user.equals(null)){
            
                throw new IllegalArgumentException("Password is incorrect");
            }
            else{
            login();
            }
        }
    
        catch(IllegalArgumentException e){
            System.out.println(e);
        } 
    }


    public void registerUser(ActionEvent event) throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("newUser.fxml");
    }

}
