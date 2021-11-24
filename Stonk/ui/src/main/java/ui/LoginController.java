package ui;

import core.User;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for loginpage.
 */
public class LoginController extends SuperController {

  @FXML
  private Button login;
  @FXML
  private Button registerUserNew;
  @FXML
  private TextField password;
  @FXML
  private TextField username;
  @FXML
  private Label feedBack;

  private User user;

  private HttpHandler handler = new HttpHandler();

  /**
   * Logs the user in.
   *
   * @throws IOException if not possible.
   */
  public void login() {
    super.changeScene("mainPage.fxml", user);
  }

  /**
   * Checks if login is valid.
   */
  @FXML
  public void isLoginValid() {
    feedBack.setText(" ");
    if (username.getText().isBlank() || password.getText().isBlank()) {
      feedBack.setText("All fields must be filled out");
    } else {
      User temp = new User(username.getText(), password.getText());
      String resp = handler.isLoginValid(temp.getUsername(), temp.getPassword());
      if (resp.contains("200")) {
        user = handler.getUser(temp.getUsername(), temp.getPassword());
        login();
      } else {
        resp = resp.replace("400: ", "");
        feedBack.setText(resp);
      }
    }
  }

  /**
   * Register new user.
   *
   * @throws IOException if something is wrong.
   */
  public void registerUserNew() throws IOException {
    super.changeScene("newUser.fxml", null);

  }

}
