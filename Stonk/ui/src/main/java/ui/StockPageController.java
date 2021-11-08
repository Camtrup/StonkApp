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
  private Stonk stock = null; // Is static and public so the mainController
  //can access it and send the stock-object forward

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
    StonkApp.setStaticUser(user);
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
        throw new IllegalArgumentException();
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Amount must be a number");
    }
  }

  // Add Stock to WatchList
  public void watchStock() {
    try {
      user.addToWatchList(stock.getTicker(), 1);
      backToMain();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Buys stock if amount is valid.
   */
  public void buy() {
    try {
      checkIfNum(amountStock);
      user.addToPortfoilio(stock.getTicker(), Integer.parseInt(amountStock.getText()));
      backToMain();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Sells stocks if amount is valid.
   */
  public void sell() {
    try {
      checkIfNum(amountStock);
      user.removeFromPortfolio(stock.getTicker(), Integer.parseInt(amountStock.getText()));
      backToMain();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @FXML
  public void initialize() {
    this.user = StonkApp.getStaticUser();
    for(Stonk i : user.getPortfolio()){
      if (i.getCount() == 0){
        this.stock = i;
      }
    }
  }

}
