package ui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    private Button moneyAddBtn;
    @FXML
    private TextField moneyAdd;
    @FXML
    private Label name;
    @FXML
    private Label addedPrompt;
    

    private User user; 
    DataHandler handler = new DataHandler();
    StonkApp app = new StonkApp();
    private Object putCash;
    //Float differ = MainController.difference;
    public void displayOnProfile(){
        name.setText(user.getFirstName() +" "+ (user.getLastName()));
        balance.setText(Float.toString(user.getCash()) + " $");
    //    balance.setText(Float.toString(user.getCash()+ MainController.difference) + "$" );

    }


    public void addMoney(){
        //StockPageCon.checkIfNum(MoneyAdd);
        float cash = Float.parseFloat(moneyAdd.getText());
        System.out.println(cash);
        JSONArray userArray = handler.getAllUsers();
        JSONObject user1 = (JSONObject) userArray.get(userArray.indexOf(handler.findUser(user.getUserName())));
        float currentCash = Float.parseFloat(user1.get("cash").toString());
        putCash = user1.put("cash", (currentCash + cash));
        //((user.getCash()) + cash);
        balance.setText(Float.toString(user.getCash()) + cash+" $");
        addedPrompt.setText("Congrats, " + (currentCash + cash)+ "$ funds have been added");
}

    public void logOut(){
        StonkApp.setStaticUser(null);
        app.changeScene("login.fxml");
        
    }

    public void deleteUser(){
        handler.deleteUser(user.getUserName());
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
