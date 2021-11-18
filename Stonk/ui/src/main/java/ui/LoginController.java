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
  private Label error;

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
    User temp = new User(username.getText(), password.getText());
    String resp = handler.isLoginValid(temp.getUsername(), temp.getPassword());
    if (resp.contains("200")) {
      user =  handler.getUser(temp.getUsername(), temp.getPassword());
      login();
    } else {
      // feedback
      error.setStyle("-fx-text-fill: red; -fx-text-alignment: center;");
      error.setText(resp);
      System.out.println(resp);
    }
  }

  public void btnHoverLogin() {
    login.setStyle("-fx-background-color: #3f4652;");

  }

  public void btnNormalLogin() {
    login.setStyle("-fx-background-color: #090a0c;");

  }

  public void btnHoverRegister() {
    registerUserNew.setStyle("-fx-background-color: #3f4652;");

  }

  public void btnNormalRegister() {
    registerUserNew.setStyle("-fx-background-color: #090a0c;");

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
