package ui;

import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for stockpage.
 */
public class StockPageController {

  // DataHandler handler = new DataHandler(); Bruker ikke ifølge spotbugs
  private User user;
  private static Stonk stock = null; // Is static and public so the mainController
  //can access it and send the stock-object forward

  HttpHandler handler = new HttpHandler();

  public static void setStaticStock(Stonk s){
    stock = new Stonk(s.getTicker(), s.getCount());
  }


  @FXML
  private Label moneyFlow;
  @FXML
  private Label owning;
  @FXML
  private Label priceChange;
  @FXML
  private Label priceTicker;
  @FXML
  private Label stockTicker;
  @FXML
  private Label totPrice1;
  @FXML
  private TextField amountStock;
  @FXML
  private TextField username;

  /**
   * Is fired when the user clicks "EXIT".
   */
  public void backToMain() {
    StonkApp app = new StonkApp();
    StonkApp.setStaticUser(handler.getUser(user.getUsername(), user.getPassword()));
    app.changeScene("mainPage.fxml");
  }

  /**
   * Is firen on initialize of fxml.
   * Updates all the fields relevant to the stock.
   */
  @FXML
  public void updateStockPage() {
    stockTicker.setText(stock.getName());
    priceTicker.setText(Float.toString(stock.getPrice()) + " $");
    moneyFlow.setText(Float.toString(user.getCash()) + " $");
    totPrice1.setText(Float.toString(stock.getPrice()) + " $");

    char priceChangeFloat = stock.getPriceChange().charAt(0); // Checks if priceChange is negative
    priceChange.setText(stock.getPriceChange());

    for (Stonk i : user.getPortfolio()) {
      if (i.getTicker().equals(stock.getTicker())) {
        owning.setText(String.valueOf(i.getCount()));
      }
    }

    // coloring for pricechange
    if (priceChangeFloat == '-') {
      priceChange.setStyle("-fx-text-fill: Red;");
    } else {
      priceChange.setStyle("-fx-text-fill: #7fff00;");
    }
    priceChange.setText(stock.getPriceChange());

  }

  /**
   * Updates the total price.
   *
   * @throws NumberFormatException if it doesnt work.
   */
  public void updateTotalPrice() throws NumberFormatException {
    if (!amountStock.getText().equals("")) {
      amountStock.setStyle("-fx-text-fill: black; -fx-color: black;");
      amountStock.setStyle("-fx-text-fill: black;");
      Float floatPrice = stock.getPrice() * Float.parseFloat(amountStock.getText());
      if (Float.parseFloat(amountStock.getText()) <= 0) {
        totPrice1.setText("Invalid");
      }
      else if (floatPrice>10000){
        totPrice1.setText(String.format("%.0f", floatPrice) + " $");
      }
      else {
        totPrice1.setText(String.format("%.2f", floatPrice) + " $");
      }
    }
  }

  /**
   * Checks is number for validation.
   *
   * @param number getting from textfield.
   */
  public void checkIfNum(TextField number) {
    try {
      Integer.parseInt(number.getText());
      if (number.getText().equals("")) {
        throw new IllegalArgumentException("Cannot be blank");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Amount must be a number");
    }
  }

  // Add Stock to WatchList
  public void watchStock() {
    String resp = handler.addOrRemoveWatchList(false, user.getUsername(), user.getPassword(), stock.getTicker());
    if(resp.contains("200")){
      backToMain();
    }
    else{
      //Feedback
      System.out.println(resp);
    }
  }

  // Add Stock to WatchList
  public void removeWatchStock() {
    String resp = handler.addOrRemoveStonk(true, user.getUsername(), user.getPassword(), stock.getTicker(), 1);
    if(resp.contains("200")){
      backToMain();
    }
    else{
      //Feedback
      System.out.println(resp);
    }
  }


  /**
   * Buys stock if amount is valid.
   */
  public void buy() {
      try {
        checkIfNum(amountStock);
        String resp = handler.buyOrSellStonk(false, user.getUsername(), user.getPassword(), stock.getTicker(), Integer.parseInt(amountStock.getText()));
          if (resp.contains("200")){
            backToMain();
          }
          else {
            //Feedback
            System.out.println(resp);
          }
      }
      catch(IllegalArgumentException e){
        //Feedback
        System.out.println(e);
      }
  }

  /**
   * Sells stocks if amount is valid.
   */
  public void sell() {
      try {
        checkIfNum(amountStock);
        String resp = handler.buyOrSellStonk(true, user.getUsername(), user.getPassword(), stock.getTicker(), Integer.parseInt(amountStock.getText()));
        if (resp.contains("200")){
          backToMain();
        }
        else {
          //Feedback
          System.out.println(resp);
        }
      }
      catch(IllegalArgumentException e){
        //Feedback
        System.out.println(e);
      }
  }

  @FXML
  public void initialize() {
    this.user = StonkApp.getStaticUser();
  }

}
