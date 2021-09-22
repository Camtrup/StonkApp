package stonk;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class registerController {
    
    @FXML
    private Button loginFromRegister; 

    public void loginFromRegister() throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("mainPage.fxml");
    }
}
