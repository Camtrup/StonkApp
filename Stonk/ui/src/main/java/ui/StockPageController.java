package ui;

import java.io.IOException;

import core.DataHandler;
import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;




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
    private Label stockTicker; 


    public void backToMain(){
        StonkApp app = new StonkApp();
        try {
            app.changeScene("mainPage.fxml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    public void updateStockPage(){
        stockTicker.setText(stock.getName());
        priceTicker.setText(Float.toString(stock.getPrice()));
        moneyFlow.setText(Float.toString(user.getCash()) + " $");
        float priceChangeFloat = Float.parseFloat(stock.getPriceChange());
        priceChange.setText(stock.getPriceChange());
        priceChange.setStyle( "-fx-text-fill: Red;");
        if (priceChangeFloat > 0){
            priceChange.setStyle( "-fx-text-fill: Green;");
        }
        priceChange.setText(stock.getPriceChange());

    } 

    @FXML
    public void initialize(){
        this.user = StonkApp.user;
    }


}


