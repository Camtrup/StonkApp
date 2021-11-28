package ui;

import core.Stonk;
import core.User;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for main.
 */
public class MainController extends SuperController {
  private User user = null;

  @FXML
  private GridPane gridpane;
  @FXML
  private Label cashMoneyFlow;
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
  private Label feedBack;

  private Float ecuityChange = (float) 0;
  private Float stockPriceChanged = (float) 0.0;
  private Float growthPerStock = (float) 0.0;
  private String stockOnWeb;
  private VBox h1;

  HttpHandler handler = new HttpHandler();

  /**
   * Gets user from httpHandler.
   *
   * @param user gets all the information about the logged in user.
   */
  public MainController(User user) {
    this.user = handler.getUser(user.getUsername(), user.getPassword());
  }

  /**
   * Gets decimalform.
   *
   * @param number of the given number.
   * @return in decimal format.
   */
  public String decimalform(Float number) {
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    return df.format(number);
  }

  /**
   * Displays your potifolio and money statistics on main site.
   */
  public void displayOnMain() {
    displayPortfolio();
    cashMoneyFlow.setText((decimalform(user.getCash())) + "$");
    fullName.setText((user.getFirstName()) + " " + (user.getLastName()));
    equity.setText((decimalform(user.getCash() + stockPriceChanged + ecuityChange)) + "$");
    growthPercent();
  }

  /**
   * Growth-precent of you acount balance.
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
    try {
      Stonk temp = new Stonk(searchBar.getText(), 0);
      super.changeScene("stockPage.fxml", user, (Stage) feedBack.getScene().getWindow(), temp);
    } catch (IllegalArgumentException | NullPointerException e) {
      e.printStackTrace();
      feedBack.setStyle("-fx-text-fill: Red;");
      feedBack.setText("Could not find stock");
    }
  }

  /**
   * Opens the marketwatch.com website with more information about the stock
   * clicked on.
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

  /**
   * for showing PortFolio and WatchList on the main page.
   *
   * @param portOrWatch        Boolean.
   * @param sellOrBuy          String.
   * @param averageOrWhenAdded String.
   * @param watchListStar      String.
   */
  public void portfolioAndWatchList(Boolean portOrWatch, String sellOrBuy, 
      String averageOrWhenAdded, String watchListStar) {
    scrollPane.getChildren().clear();
    watchList.setStyle(watchListStar);
    ArrayList<Stonk> getStock = null;
    if (portOrWatch == false) { // Either shows porfolio or watchList
      getStock = user.getWatchList();
    } else {
      getStock = user.getPortfolio();
    }

    ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();

    for (Stonk i : getStock) {
      ArrayList<String> tempArr = new ArrayList<>();
      tempArr.add(String.valueOf(i.getTicker()));
      tempArr.add(String.valueOf(i.getPrice()));
      tempArr.add(String.valueOf(i.getCount()));
      tempArr.add(String.valueOf(i.getName()));
      // Adds the current price and the stock information to an ArrayList
      arr.add(tempArr);
    }

    if (arr.size() == 0) {
      feedBack.setText("No stocks in potfolio/watchList");
      // "No stocks in potfolio/watchList"
    } else {
      for (ArrayList<String> row : arr) {
        // gets a stock from the arrayList:
        Stonk s = new Stonk(row.get(0), Integer.parseInt(row.get(2))); 

        // to calculate how much you have earned from Stocks
        ecuityChange += (s.getPrice()) * Float.parseFloat(row.get(2));
        growthPerStock = ((s.getPrice()) * Float.parseFloat(row.get(2))
            - Float.parseFloat(row.get(1)) * Float.parseFloat(row.get(2)));
        stockPriceChanged += growthPerStock;

        // Adds info
        Label l = new Label("_____________________\n" 
            + row.get(0).toUpperCase()); // shows the stock name
        Label growth = new Label("Growth: " + String.format("%.2f", growthPerStock) + " $ \n");
        growth.setStyle("-fx-font-size: 15;");

        Label fullName = new Label(" " + row.get(3) + " ");
        fullName.setStyle("-fx-font-size: 12;");
        l.setStyle("-fx-font-size: 21; -fx-text-alignment: center;" 
            + " -fx-alignment:center; -fx-margin:auto;");

        Label numbers = new Label("Amount: " + row.get(2) + "\n" + averageOrWhenAdded
            + " " + String.format("%.2f", Float.parseFloat(row.get(1))) 
                + " $" + "\n\nCurrent: " + s.getPrice() + " $");
        numbers.setStyle("-fx-font-size: 15;");

        Label valueNow = new Label(
            "Value now: " + String.format("%.2f", (s.getPrice()
                * (Float.parseFloat(row.get(2))))) + " $");
        valueNow.setStyle("-fx-font-size: 15;");

        // changes color of growth to red or green if you have lost or earned money
        if (growthPerStock > 0) {
          growth.setStyle("-fx-font-size: 16; -fx-text-alignment: center; -fx-alignment:center;"
              + "-fx-text-fill:green;");
        } else if (growthPerStock < 0) {
          growth
              .setStyle("-fx-font-size: 16; -fx-text-alignment: center;" 
                  + " -fx-alignment:center; -fx-text-fill:red;");
        }

        Button more = new Button("more info");
        Button b = new Button(sellOrBuy);
        more.setStyle(
            "-fx-background-color: #090a0c;" 
                + " -fx-text-fill: linear-gradient(white, #d0d0d0); -fx-padding:10px;");
        b.setStyle("-fx-background-color: #090a0c;"
            + " -fx-text-fill: linear-gradient(white, #d0d0d0);  -fx-padding:10px 23px;");

        // removes value now if watchlist is showing
        if (portOrWatch == true) {
          h1 = new VBox(l, fullName, numbers, valueNow, growth);
        } else {
          h1 = new VBox(l, fullName, numbers, growth);
        }

        h1.setPadding(new Insets(0, 10, 10, 10));
        h1.setStyle("-fx-background-color: #dbdbdb");
        HBox hbox = new HBox(b, more);
        hbox.setSpacing(15);
        HBox.setMargin(b, new Insets(0, 0, 0, 45));
        hbox.setStyle("-fx-background-color: #dbdbdb; -fx-margin: auto");

        b.setOnMouseClicked(event -> {
          searchBar.setText(row.get(0)); // sends you to site for buying/selling stocks
          toStockPage();
        });
        more.setOnMouseClicked(event -> {
          stockOnWeb = row.get(0); // searches up the stock on marketwatch.com
          openBrowser();
        });
        scrollPane.getChildren().addAll(h1, hbox);
      }
    }
  }

  /**
   * Displays portfoilo on the main page.
   */
  public void displayPortfolio() {
    portfolioAndWatchList(true, "sell", "average:",
        "-fx-graphic: url('https://img.icons8.com/ios/25/000000/star--v2.png')");
  }

  /**
   * Displays watchList on the main page when clickking on the star.
   */
  public void showWatchList() {
    portfolioAndWatchList(false, "Buy", "when added:",
        "-fx-graphic: url('https://img.icons8.com/fluency/25/000000/star.png')");
  }

  /**
   * Navigates to profile site.
   */
  public void toProfile() {
    super.changeScene("profile.fxml", user, (Stage) feedBack.getScene().getWindow());
  }

  /**
   * Initializes on start.
   */
  @FXML
  private void initialize() {
    String resp = handler.save();
    if (resp.contains("400")) {
      feedBack.setText(resp);
      System.out.println(resp);
    }
    displayOnMain();
  }

}
