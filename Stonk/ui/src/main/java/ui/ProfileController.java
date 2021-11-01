package ui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import data.DataHandler;

public class ProfileController {

  @FXML
  private Label balance;
  @FXML
  private Button moneyAddBtn;
  @FXML
  private TextField moneyAdd;
  @FXML
  private Label name;
  @FXML
  private Label addedPrompt;

  @FXML
  private Text startedWith;

  private User user;
  DataHandler handler = new DataHandler();
  StonkApp app = new StonkApp();
  private Object putCash;

  // Float differ = MainController.difference;
  public void displayOnProfile() {
    name.setText(user.getFirstName() + " " + (user.getLastName()));
    balance.setText(Float.toString(user.getCash()) + " $");

  }

  public void addValue() {
    float cash = Float.parseFloat(moneyAdd.getText());
    user.addMoney(cash);
    balance.setText(Float.toString(user.getCash()) + " $");
    addedPrompt.setText("Congrats, funds have been added");
  }

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
