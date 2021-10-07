package ui;

import core.DataHandler;
import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class StockPageController {

    DataHandler handler = new DataHandler();
    private User user; 
    public static Stonk stock = new Stonk();

    
    @FXML
    private Label moneyFlow; 
    
    @FXML
    private Label priceTicker; 

    @FXML
    private TextField username;
    @FXML
    private TextField amountStock;
    
    @FXML
    private Label priceChange; 



    @FXML
    public void updateStockPage(){
        moneyFlow.setText(Float.toString(user.getCash()) + " $");
        //priceTicker.setText(s.getName());
        // priceChange.set
        // owning.set
    } 

    @FXML
    public void initialize(){
        this.user = StonkApp.user;
    }


}


