package ui;

import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for stockpage.
 */
public class StockPageController extends SuperController {

  // DataHandler handler = new DataHandler(); Bruker ikke ifølge spotbugs
  private User user = null;
  private Stonk stock = null; // Is static and public so the mainController
  // can access it and send the stock-object forward

  HttpHandler handler = new HttpHandler();

  public StockPageController(User user, Stonk stock) {
    this.user = handler.getUser(user.getUsername(), user.getPassword());
    this.stock = new Stonk(stock.getTicker(), stock.getPrice(), stock.getCount(), stock.getName(), stock.getPriceChange());
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
  @FXML
  private Label feedBack;
  @FXML
  private Button buyBtn;
  @FXML
  private Button sellBtn;
  @FXML
  private Button backToMain;
  @FXML
  private Button addWatchList;

  /**
   * Is fired when the user clicks "EXIT".
   */
  public void backToMain() {
    super.changeScene("mainPage.fxml", user,(Stage) feedBack.getScene().getWindow());
  }

  /**
   * Is firen on initialize of fxml. Updates all the fields relevant to the stock.
   */
  @FXML
  public void updateStockPage() {
    stockTicker.setText(stock.getName());
    priceTicker.setText(Float.toString(stock.getPrice()) + " $");
    moneyFlow.setText(Float.toString(user.getCash()) + " $");
    totPrice1.setText(Float.toString(stock.getPrice()) + " $");

    priceChange.setText("" + stock.getPriceChange());

    for (Stonk i : user.getPortfolio()) {
      if (i.getTicker().equals(stock.getTicker())) {
        owning.setText(String.valueOf(i.getCount()));
      }
    }

    // coloring for pricechange
    if (stock.getPriceChange() < 0) {
      priceChange.setStyle("-fx-text-fill: Red;");
    } else {
      priceChange.setStyle("-fx-text-fill: #7fff00;");
    }
    priceChange.setText(stock.getPriceChange() + "%");

  }

  @FXML
  private void userFeedback(String resp){
    resp = resp.replace("400: ", "");
    resp = resp.replace("403: ", "");
    resp = resp.replace("404: ", "");
    resp = resp.replace("405: ", "");
    resp = resp.replace("406: ", "");
    feedBack.setText(resp);
    System.out.println(resp);
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
      int amount = -1;
      try{
        amount = Integer.parseInt(amountStock.getText());
      }
      catch (NumberFormatException e){
        totPrice1.setText("Invalid");
      }
      Float floatPrice = stock.getPrice() * amount;
      if (amount <= 0) {
        totPrice1.setText("Invalid");
      } else if (floatPrice > 10000) {
        totPrice1.setText(String.format("%.0f", floatPrice) + " $");
      } else {
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

  /**
   * Adds Stock to WatchList.
   */
  public void watchStock() {
    boolean isInWatchlist = checkForWatchList();
    String resp = handler.addOrRemoveWatchList(isInWatchlist, user.getUsername(), user.getPassword(),
        stock.getTicker());
    if (resp.contains("400")) {
      userFeedback(resp);
    } 
    user = handler.getUser(user.getUsername(), user.getPassword());
    checkForWatchList();
  }


  /**
   * Buys stock if amount is valid.
   */
  public void buy() {
    try {
      checkIfNum(amountStock);
      String resp = handler.buyOrSellStonk(false, user.getUsername(),
          user.getPassword(), stock.getTicker(), Integer.parseInt(amountStock.getText()));
      if (resp.contains("200")) {
        backToMain();
      } 
      else {
        userFeedback(resp);
      }
    } catch (IllegalArgumentException e) {
      userFeedback(e.getMessage());
    }
  }

  /**
   * Sells stocks if amount is valid.
   */
  public void sell() {
    try {
      checkIfNum(amountStock);
      String resp = handler.buyOrSellStonk(true, user.getUsername(),
          user.getPassword(), stock.getTicker(), Integer.parseInt(amountStock.getText()));
      if (resp.contains("200")) {
        backToMain();
      } else {
        userFeedback(resp);
      }
    } catch (IllegalArgumentException e) {
      userFeedback(e.getMessage());
    }
  }

  @FXML
  private boolean checkForWatchList(){
    for(Stonk i : user.getWatchList()){
      if(i.getTicker().equals(stock.getTicker())){
        addWatchList.setText("REMOVE FROM WATCHLIST");
        return true;
      }
    }
    addWatchList.setText("ADD TO WATCHLIST");
    return false;
  }

  @FXML
  private void initialize(){
    updateStockPage();
    checkForWatchList();
  }
}
