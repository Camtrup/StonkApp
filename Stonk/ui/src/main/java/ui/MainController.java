package ui;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {
    private User user; 
    Stonk stock = new Stonk();

    
    @FXML
    private Label cashMoneyFlow; 
    
    @FXML
    private Label fullName; 
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private TextField cash;
    @FXML
    private TextField searchBar;
    @FXML
    private Text balanceString; 
    @FXML
    private GridPane gridpane;
    @FXML 
    private VBox scrollPane; 

    public void displayOnMain(){
        cashMoneyFlow.setText(Float.toString(user.getCash()) + " $");
        cashMoneyFlow.setStyle("-fx-text-fill: white;");
        fullName.setText((user.getFirstName()) + " " + (user.getLastName()));
        displayPortfolio();
    }

    public void toStockPage(){
        StonkApp app = new StonkApp();
        try {
            StockPageController.stock.getStockInfo(searchBar.getText());
            app.changeScene("stockPage.fxml");
        }
        catch(IllegalArgumentException e){

        }
    }

    public void displayPortfolio(){
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        JSONArray json = user.getPortfolio();

        for (Object i : json){
            JSONObject tempObj = (JSONObject) i;
            ArrayList<String> tempArr = new ArrayList<>();
            tempArr.add(String.valueOf(tempObj.get("ticker")));
            tempArr.add(String.valueOf(tempObj.get("price")));
            tempArr.add(String.valueOf(tempObj.get("count")));
            //LEGG TIL CURRENTS PRICE OG NÅVERENDE VERDI
            arr.add(tempArr);
        }

        if (arr.size() == 0){
            //"NO STOCKS IN PORTFOLIO"
        }
        else {
            for (ArrayList<String> row : arr){
                Stonk s = new Stonk();
                s.getStockInfo(row.get(0)); 


                //Adds info
                Label l = new Label("-\n" + row.get(0).toUpperCase() + "\nAmount: " + row.get(2) + "\nAverage: " + row.get(1) + "\nCurrent: " + s.getPrice());
                Button b = new Button("Sell");
                VBox h = new VBox(l, b);
                
                //Style of elements
                b.maxHeight(l.getHeight());
                h.setPadding(new Insets(10,10,10,10));

                b.setOnMouseClicked(Event -> {
                    searchBar.setText(row.get(0));
                    toStockPage();
                });
                
                scrollPane.getChildren().addAll(h);
            }
        }
    }

    public void toProfile(){
        StonkApp app = new StonkApp();
        app.changeScene("profile.fxml");
    }

    @FXML
    private void initialize(){
        this.user = StonkApp.getStaticUser();
        displayOnMain();
    }

}
