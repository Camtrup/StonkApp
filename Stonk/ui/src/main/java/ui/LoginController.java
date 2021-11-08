package ui;

import core.User;
import java.io.IOException;
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
      User temp = null; //SEND REQUEST TIL SERVER OM GYLDIG LOGIN
      StonkApp.setStaticUser(temp);
      login();
    }  catch (IllegalArgumentException | IOException e) {
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
