package ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.WatchService;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

public class MainController {
    private User user; 
    //Stonk stock = new Stonk(); Bruker ikke ifølge spotbugs 

    
    @FXML Label cashMoneyFlow; 
    
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
    @FXML 
    private Label equity; 
    @FXML 
    private Label growth; 

    @FXML 
    private Label growthPercent; 

    private String StockOnWeb;
    private Float StockPriceChanged = (float) 0.0;
    private Float ecuityChange = (float) 0;
    public Float difference = (float) 0;


    public String decimalform(Float number){
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    return df.format(number);
}

    public void displayOnMain(){
        displayPortfolio();
        Float difference = (ecuityChange - user.getCash());
        cashMoneyFlow.setText(Float.toString(user.getCash()+difference) + "$" );
        cashMoneyFlow.setStyle("-fx-text-fill: white;");
        fullName.setText((user.getFirstName()) + " " + (user.getLastName()));
        equity.setText((decimalform(ecuityChange-difference + StockPriceChanged)) + "$" );
    
        growth.setText(decimalform(StockPriceChanged) + "$"  );
        if (cashEarnedPercent()>0){
            growthPercent.setText("+"+ decimalform(cashEarnedPercent())+ "%");
        }
        else{
        growthPercent.setText(decimalform(cashEarnedPercent())+ "%");
        }
        if (StockPriceChanged>1000 || StockPriceChanged<-1000){
            growthPercent.setLayoutX(190);
        }
    }

    public float cashEarnedPercent(){
        Float totalEq = ecuityChange + user.getCash();
        if (StockPriceChanged == 0){
            growthPercent.setStyle("-fx-text-fill: black;");
            return 0.0f;
        }
        else if (StockPriceChanged < 0){
            growthPercent.setStyle("-fx-text-fill: #cc021d;");
            return (StockPriceChanged/totalEq)*100;
        }
        return ((StockPriceChanged/totalEq)*100);
    }


    public void toStockPage(){
        StonkApp app = new StonkApp();
        try {
            StockPageController.stock.getStockInfo(searchBar.getText().toLowerCase());
            app.changeScene("stockPage.fxml");
        }
        catch(IllegalArgumentException e){

        }
    }
    public void openBrowser(){
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI("https://www.marketwatch.com/investing/stock/" + StockOnWeb));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    public void displayPortfolio(){
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        JSONArray json = user.getPortfolio();
        System.out.println(json);

        for (Object i : json){
            JSONObject tempObj = (JSONObject) i;
            ArrayList<String> tempArr = new ArrayList<>();
            tempArr.add(String.valueOf(tempObj.get("ticker")));
            tempArr.add(String.valueOf(tempObj.get("price")));
            tempArr.add(String.valueOf(tempObj.get("count")));
            //LEGG TIL CURRENTS PRICE OG NÅVERENDE VERDI
            arr.add(tempArr);
        }
        System.out.println(arr);

        if (arr.size() == 0){
            //"NO STOCKS IN PORTFOLIO"
        }
        else {
            for (ArrayList<String> row : arr){
                Stonk s = new Stonk();
                s.getStockInfo(row.get(0)); 
                System.out.println((row.get(0)));
                // to get how much you have eanred from Stpcks
                ecuityChange += (s.getPrice())*Float.parseFloat(row.get(2));
                StockPriceChanged += (s.getPrice())*Float.parseFloat(row.get(2))-Float.parseFloat(row.get(1))*Float.parseFloat(row.get(2));


                //Adds info
                Label l = new Label("_____________________________\n" + row.get(0).toUpperCase() + "\nAmount: " + row.get(2) + "\nAverage: " + row.get(1) + "\nCurrent: " + s.getPrice());
                Button b = new Button("Sell");
                Button more = new Button("more info");

                VBox h = new VBox(l);
                HBox hbox = new HBox(b, more);
                

                
                //Style of elements
                l.setStyle(
                    "-fx-font-size: 15;");
                b.maxHeight(l.getHeight());
                more.maxHeight(l.getHeight());
                more.setStyle("-fx-background-color: #090a0c; -fx-text-fill: linear-gradient(white, #d0d0d0); -fx-padding:10px;");
                b.setStyle("-fx-background-color: #090a0c; -fx-text-fill: linear-gradient(white, #d0d0d0);  -fx-padding:10px;");
                h.setPadding(new Insets(10,10,10,10));
                h.setStyle("-fx-background-color: #dbdbdb" );
                hbox.setSpacing(15);
                hbox.setMargin(b, new Insets(0, 0, 0, 70));
                hbox.setStyle("-fx-background-color: #dbdbdb; -fx-margin: auto" );

                b.setOnMouseClicked(Event -> {
                    searchBar.setText(row.get(0));
                    toStockPage();
                });

                
                more.setOnMouseClicked(Event -> {
                    StockOnWeb = row.get(0);
                    openBrowser();
                });

                scrollPane.getChildren().addAll(h);
                scrollPane.getChildren().addAll(hbox);
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
