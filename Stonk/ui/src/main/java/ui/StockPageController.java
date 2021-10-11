package ui;

import org.json.simple.JSONObject;

import core.DataHandler;
import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;




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
    @FXML 
    private Label owning;


    public void backToMain(){
        StonkApp app = new StonkApp();
        app.changeScene("mainPage.fxml");
    }


    @FXML
    public void updateStockPage(){
        stockTicker.setText(stock.getName());
        priceTicker.setText(Float.toString(stock.getPrice()));
        moneyFlow.setText(Float.toString(user.getCash()) + " $");
        
        char priceChangeFloat = stock.getPriceChange().charAt(0); //Checks if priceChange is negative
        priceChange.setText(stock.getPriceChange());

        for (Object i : user.getPortfolio()){
            JSONObject temp = (JSONObject) i;
            if(temp.get("ticker").equals(stock.getTicker())){
                owning.setText(String.valueOf(temp.get("count")));
            }
        }


        if (priceChangeFloat == '-'){
            priceChange.setStyle( "-fx-text-fill: Red;");
        }
        else {
            priceChange.setStyle( "-fx-text-fill: Green;");
        }
        priceChange.setText(stock.getPriceChange());

    } 

    @FXML
    public void initialize(){
        this.user = StonkApp.user;

    }


}


