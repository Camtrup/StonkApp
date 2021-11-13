package ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;


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

/**
 * Controller for main.
 */
public class MainController {
  private User user;
  // Stonk stock = new Stonk(); Bruker ikke ifølge spotbugs
  
  @FXML
  private GridPane gridpane;
  @FXML
  private Label cashMoneyFlow; // Added private (wasnt anything before)
  @FXML
  private Label equity;
  @FXML
  private Label fullName;
  @FXML
  private Label growth;
  @FXML
  private Label growthPercent;
  @FXML
  private Text balanceString;
  @FXML
  private TextField age;
  @FXML
  private TextField cash;
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private TextField password;
  @FXML
  private TextField searchBar;
  @FXML
  private TextField username;
  @FXML
  private VBox scrollPane;
  @FXML
  private Button watchList;
  @FXML
  private Button myStocks;
  @FXML
  private Button myProfile;
  @FXML
  private Button searchButton;
  @FXML
  private Label llegalArgument;

  private Float ecuityChange = (float) 0;
  private Float stockPriceChanged = (float) 0.0;
  private Float growthPerStock = (float) 0.0;
  private String stockOnWeb;
  HttpHandler handler = new HttpHandler();
  // public Float difference = (float) 0; - spotbugs - unused

  /**
   * Gets decimalform.
   * @param number of the given number.
   * @return in decimal format.
   */
  public String decimalform(Float number) {
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    return df.format(number);
  }

  /**
   * Displays your potifolio on main im fxml.
   */
  public void displayOnMain() {
    displayPortfolio();
    // Float difference = (ecuityChange - user.getCash());
    cashMoneyFlow.setText(Float.toString(user.getCash()) + "$");
    cashMoneyFlow.setStyle("-fx-text-fill: white;");
    fullName.setText((user.getFirstName()) + " " + (user.getLastName()));
    equity.setText((decimalform(user.getCash() + stockPriceChanged + ecuityChange)) + "$");
    growthPercent();
  }

  /**
   * Growth-precent to show.
   */
  public void growthPercent() {
    growth.setText(decimalform(stockPriceChanged) + "$");
    if (cashEarnedPercent() > 0) {
      growthPercent.setText("+" + decimalform(cashEarnedPercent()) + "%");
    } else {
      growthPercent.setText(decimalform(cashEarnedPercent()) + "%");
    }
    if (stockPriceChanged > 1000 || stockPriceChanged < -1000) {
      growthPercent.setLayoutX(190);
    }
  }

  /**
   * Percentage-calculator with colorchange for negative og positive.
   *
   * @return how much percent one has earned.
   */
  public float cashEarnedPercent() {
    Float totalEq = ecuityChange + user.getCash();
    if (stockPriceChanged == 0) {
      growthPercent.setStyle("-fx-text-fill: black;");
      return 0.0f;
    } else if (stockPriceChanged < 0) {
      growthPercent.setStyle("-fx-text-fill: #cc021d;");
      return (stockPriceChanged / totalEq) * 100;
    }
    return ((stockPriceChanged / totalEq) * 100);
  }

  /**
   * Navigates to stockpage.
   */
  public void toStockPage() {
    StonkApp app = new StonkApp();
    try {
      Stonk temp = new Stonk(searchBar.getText(), 0);
      if(Objects.isNull(temp)){
        throw new IllegalArgumentException("Could not find stock");
      }
      StockPageController.setStaticStock(temp);
      app.changeScene("stockPage.fxml");
    } catch (IllegalArgumentException | NullPointerException e) {
      System.out.println(e);
    }
  }

  /**
   * Opens the marketwatch.com website with more information about the stock clicked on.
   */
  public void openBrowser() {
    Desktop d = Desktop.getDesktop();
    try {
      d.browse(new URI("https://www.marketwatch.com/investing/stock/" + stockOnWeb));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public void portfolioAndWatchList(Boolean portOrWatch, String sellOrBuy, String AverageOrWhenAdded, String watchListStar){
    scrollPane.getChildren().clear();
    watchList.setStyle(watchListStar);
    ArrayList<Stonk> getStock = null;
    if (portOrWatch == false){
      getStock = user.getWatchList();
    }else{
    getStock =user.getPortfolio();
    }

    //scrollPane.getChildren().clear();
    //watchList.setStyle("-fx-graphic: url('https://img.icons8.com/ios/25/000000/star--v2.png')");

    ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();

    for (Stonk i : getStock) {
      ArrayList<String> tempArr = new ArrayList<>();
      tempArr.add(String.valueOf(i.getTicker()));
      tempArr.add(String.valueOf(i.getPrice()));
      tempArr.add(String.valueOf(i.getCount()));
      tempArr.add(String.valueOf(i.getName()));
      // LEGG TIL CURRENTS PRICE OG NÅVERENDE VERDI
      arr.add(tempArr);
    }

    if (arr.size() == 0) {
      // "NO STOCKS IN PORTFOLIO/WATCHLIST"
    } else {
      for (ArrayList<String> row : arr) {
        Stonk s = new Stonk(row.get(0), Integer.parseInt(row.get(2)));
        // to get how much you have eanred from Stocks
        ecuityChange += (s.getPrice()) * Float.parseFloat(row.get(2));
        growthPerStock = ((s.getPrice()) * Float.parseFloat(row.get(2))
        - Float.parseFloat(row.get(1)) * Float.parseFloat(row.get(2)));
        stockPriceChanged += growthPerStock;
        // Adds info
        Label l = new Label("____________________\n" 
            + row.get(0).toUpperCase());
        Label fullName = new Label(" " + row.get(3) + " ");
        Label numbers = new Label("Amount: " + row.get(2)+
            "\n"+ AverageOrWhenAdded+ " " + String.format("%.2f", Float.parseFloat(row.get(1)))+ " $" + "\n\nCurrent: " + s.getPrice() +" $");
        Label valueNow = new Label("Value now: " +  String.format("%.2f",(s.getPrice()*(Float.parseFloat(row.get(2))))) + " $");
        Label growth = new Label("Growth: " + String.format("%.2f", growthPerStock) + " $ \n");
            
        Button b = new Button(sellOrBuy);
        Button more = new Button("more info");

        
        // Style of elements
        fullName.setStyle("-fx-font-size: 12;");
        l.setStyle("-fx-font-size: 21; -fx-text-alignment: center; -fx-alignment:center; -fx-margin:auto;");
        numbers.setStyle("-fx-font-size: 15;");
        growth.setStyle("-fx-font-size: 15;");
        valueNow.setStyle("-fx-font-size: 15;");

        if (growthPerStock>0){
          growth.setStyle("-fx-font-size: 16; -fx-text-alignment: center; -fx-alignment:center; -fx-text-fill:green;");
        }else if ((growthPerStock<0)){
          growth.setStyle("-fx-font-size: 16; -fx-text-alignment: center; -fx-alignment:center; -fx-text-fill:red;");
        }
        //b.maxHeight(l.getHeight());
        //more.maxHeight(l.getHeight());
        more.setStyle("-fx-background-color: #090a0c;" 
            + "-fx-text-fill: linear-gradient(white, #d0d0d0); -fx-padding:10px;");
        b.setStyle("-fx-background-color: #090a0c;"
            + " -fx-text-fill: linear-gradient(white, #d0d0d0);  -fx-padding:10px 23px;");

        VBox h = new VBox();
        if (portOrWatch == true){
        h = new VBox(l,fullName,numbers, valueNow, growth);

        }else{
        h = new VBox(l,fullName,numbers, growth);
        } 

        //VBox h = new VBox(l,fullName,numbers, valueNow, growth);

        h.setPadding(new Insets(0, 10, 10, 10));
        h.setStyle("-fx-background-color: #dbdbdb");
        HBox hbox= new HBox(b, more);
        hbox.setSpacing(15);
        hbox.setMargin(b, new Insets(0, 0, 0, 55));
        hbox.setStyle("-fx-background-color: #dbdbdb; -fx-margin: auto");

        b.setOnMouseClicked(event -> {
          searchBar.setText(row.get(0));
          toStockPage();
        });

        more.setOnMouseClicked(event -> {
          stockOnWeb = row.get(0);
          openBrowser();
        });

        scrollPane.getChildren().addAll(h);
        scrollPane.getChildren().addAll(hbox);
      }
    }
}

  /**
   * Displays portfoilo on the main page.
   */
  public void displayPortfolio() {
    portfolioAndWatchList(true, "sell", "average:", "-fx-graphic: url('https://img.icons8.com/ios/25/000000/star--v2.png')");
  }
  
  /**
   * Displays watchList on the main page when clickking on the star.
   */
  public void showWatchList(){
    portfolioAndWatchList(false, "Buy", "when added:", "-fx-graphic: url('https://img.icons8.com/fluency/25/000000/star.png')" );
  }

  /**
   * Navigates to profile.
   */
  public void toProfile() {
    StonkApp app = new StonkApp();
    app.changeScene("profile.fxml");

  }
  /**
   * Initializes on start.
   */
  @FXML
  private void initialize() {
    this.user = StonkApp.getStaticUser();
    String resp = handler.save();
    if (resp.contains("400")){
      llegalArgument.setText(resp);
      System.out.println(resp);
    }
    displayOnMain();
  }


  // tried fixing the button hover problem in a easier way, maybe still working
/*  public void buttonHover(Button button){
  myStocks.setStyle("-fx-background-color: #3f4652;");
}
public void buttonNormal(Button button){
  button.setStyle("-fx-background-color: #090a0c;");
}

@FXML
private void ColorHoverButton() {
  Button[] buttons = {myStocks, myProfile, searchButton};
    for (Button button : buttons) {
      button.setStyle("-fx-background-color: #red; ");
  }
}  */


// Functions for changing the colour of the buttons when hovering.
  public void btnHoverMyStocks(){
    myStocks.setStyle("-fx-background-color: #3f4652;");
  }
  public void btnNormalMyStocks(){
    myStocks.setStyle("-fx-background-color: #090a0c;");
  }
  public void btnHoverSearch(){
    searchButton.setStyle("-fx-background-color: #3f4652;");
  }
  public void btnNormalSearch(){
    searchButton.setStyle("-fx-background-color: #090a0c;");
  }
  public void btnHoverProfile(){
    myProfile.setStyle("-fx-background-color: #3f4652; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
  }  
  public void btnNormalProfile(){
    myProfile.setStyle("-fx-background-color: #090a0c; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
  }

}
