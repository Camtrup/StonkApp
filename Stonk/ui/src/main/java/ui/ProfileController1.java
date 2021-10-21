package ui;

import core.User;
import javafx.fxml.FXML;
import data.DataHandler;

public class ProfileController1 {
    private User user; 
    StonkApp1 app = new StonkApp1();
    
    public void logOut(){
        StonkApp1.user = null;
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
        this.user = StonkApp1.user;
    }
}
