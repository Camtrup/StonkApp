package ui;

import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import data.DataHandler;





public class ProfileController {

    @FXML
    private Label balance;
    @FXML
    private Button MoneyAddBtn;
    @FXML
    private TextField MoneyAdd;
    
    @FXML
    private Label name;
    
    @FXML
    private Label AddedPrompt;
    

    private User user; 
    private StockPageController StockPageCon;
    StonkApp app = new StonkApp();
    
    public void displayOnProfile(){
        name.setText(user.getFirstName() +" "+ (user.getLastName()));
        balance.setText(Float.toString(user.getCash()) + " $");
    }

    public void addMoney(){
        StockPageCon.checkIfNum(MoneyAdd);
        float cash = Float.parseFloat(MoneyAdd.getText());
        //user.setCash((user.getCash()) + cash);
        balance.setText(Float.toString(user.getCash()) + " $");
        AddedPrompt.setText("Congrats, more funds have been added");
}   
    public void logOut(){
        StonkApp.setStaticUser(null);
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
        this.user = StonkApp.getStaticUser();
    }
}
