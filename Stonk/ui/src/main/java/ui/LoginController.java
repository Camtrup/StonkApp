package ui;

import java.io.IOException;

import Stonk.rest.HttpHandler;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller for loginpage.
 */
public class LoginController {

  @FXML
  private Button login;
  @FXML
  private Button register;
  @FXML
  private TextField password;
  @FXML
  private TextField username;

  private HttpHandler handler = new HttpHandler();

  /**
   * Logs the user in.
   *
   * @throws IOException if not possible.
   */
  public void login() throws IOException {
    StonkApp app = new StonkApp();
    app.changeScene("mainPage.fxml");
  }

  /**
   * Checks if login is valid.
   */
  @FXML
  public void isLoginValid() {
    try {
      User temp = new User(username.getText(), password.getText());
      StonkApp.setStaticUser(handler.getUser(temp.getUsername(), temp.getPassword()));
      login();
    }  catch (IllegalArgumentException | IOException | NullPointerException e) {
      System.out.println(e);
    }
  }

  /**
   * Register new user. 
   *
   * @throws IOException if something is wrong.
   */
  public void registerUserNew() throws IOException {
    StonkApp app = new StonkApp();
    app.changeScene("newUser.fxml");
  }

}
