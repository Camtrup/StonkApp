package ui;

import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller fro profile.
 */
public class ProfileController extends SuperController {

  @FXML
  private Button moneyAddBtn;
  @FXML
  private Button logOut;
  @FXML
  private Button deleteUser;
  @FXML
  private Button toMain;
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
  @FXML
  private Label feedBack;

  private User user = null;
  HttpHandler handler = new HttpHandler();

  /**
   * Displays name and balance on profile side.
   *
   * @param user is a parameter for the user that is logged on. 
   */
  public ProfileController(User user) {
    this.user = handler.getUser(user.getUsername(), user.getPassword());
  }

  /**
   * Displays name and balance on profile side.
   */
  public void displayOnProfile() {
    name.setText(user.getFirstName() + " " + (user.getLastName()));
    balance.setText(Float.toString(user.getCash()) + " $");
  }

  /**
   * Adds more cash to the users cash balance.
   *
   * @throws IllegalArgumentException if argument is not allowed.
   */
  public void addValue() {
    try {
      float cash = Float.parseFloat(moneyAdd.getText());
      if (cash < 0) {
        throw new IllegalArgumentException("write a positive number");
      }
      String resp = handler.addMoreValue(user.getUsername(), user.getPassword(), cash);
      this.user = handler.getUser(user.getUsername(), user.getPassword());

      if (resp.contains("200")) {
        balance.setText(Float.toString(user.getCash()) + " $");
        feedBack.setText("");
        // super.changeScene("profile.fxml", user);
        addedPrompt.setText("Congratulations! More money added!");

      } else {
        System.out.println(resp);
      }
    } catch (IllegalArgumentException e) {
      addedPrompt.setText("");
      feedBack.setText("Error: " + e.getMessage());
    }

  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    super.changeScene("login.fxml", null);
  }

  /**
   * Deletes the user.
   */
  public void deleteUser() {
    String resp = handler.removeUser(user.getUsername(), user.getPassword());
    if (resp.contains("200")) {
      logOut();
    } else {
      feedBack.setText(resp);
    }
  }

  /**
   * Back to main page.
   */
  public void toMain() {
    super.changeScene("mainPage.fxml", user);
  }
}
