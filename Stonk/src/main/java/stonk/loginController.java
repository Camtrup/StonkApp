package stonk;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class loginController {

    @FXML
    private Button login; 
    @FXML
    private Button register; 
    
    public void login(ActionEvent event) throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("mainPage.fxml");
    }

    public void registerUser(ActionEvent event) throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("newUser.fxml");
    }

}
