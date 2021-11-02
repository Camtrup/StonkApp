package ui;

import core.User;
import data.DataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Controller fro profile.
 */
public class ProfileController {

  @FXML
  private Button moneyAddBtn;
  @FXML
  private Label addedPrompt;
  @FXML
  private Label balance;
  @FXML
  private Label name;
  @FXML
  private Text startedWith;
  @FXML
  private TextField moneyAdd;

  private User user;
  DataHandler handler = new DataHandler();
  StonkApp app = new StonkApp();
  // private Object putCash; checkstyle - unused field

  // Float differ = MainController.difference;
  /**
   * Display on portfolio.
   */
  public void displayOnProfile() {
    name.setText(user.getFirstName() + " " + (user.getLastName()));
    balance.setText(Float.toString(user.getCash()) + " $");

  }

  /**
   * Add value.
   */
  public void addValue() {
    float cash = Float.parseFloat(moneyAdd.getText());
    user.addMoney(cash);
    balance.setText(Float.toString(user.getCash()) + " $");
    addedPrompt.setText("Congrats, funds have been added");
  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    StonkApp.setStaticUser(null);
    app.changeScene("login.fxml");

  }

  public void deleteUser() {
    handler.deleteUser(user.getUserName());
    logOut();
  }

  public void toMain() {
    app.changeScene("mainPage.fxml");
  }

  @FXML
  private void initialize() {
    this.user = StonkApp.getStaticUser();
  }
}
