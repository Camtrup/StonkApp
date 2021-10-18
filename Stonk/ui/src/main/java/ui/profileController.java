package ui;

import core.User;
import javafx.fxml.FXML;
import data.DataHandler;

public class profileController {
    private User user; 
    StonkApp app = new StonkApp();
    
    public void logOut(){
        StonkApp.user = null;
        app.changeScene("login.fxml");
        
    }

    public void deleteUser(){
        DataHandler handler = new DataHandler();
        handler.deleteUser(handler.findUser(user.getUserName()));
        logOut();
    }
    public void toMain(){
        app.changeScene("mainPage.fxml");
    }
    @FXML
    private void initialize(){
        this.user = StonkApp.user;
    }
}
