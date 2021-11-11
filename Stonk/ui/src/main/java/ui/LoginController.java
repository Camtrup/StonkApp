package ui;

import java.io.IOException;

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
  public void login() {
    StonkApp app = new StonkApp();
    app.changeScene("mainPage.fxml");
  }

  /**
   * Checks if login is valid.
   */
  @FXML
  public void isLoginValid() {
    User temp = new User(username.getText(), password.getText());
    String resp = handler.isLoginValid(temp.getUsername(), temp.getPassword());
    if(resp.contains("200")){
      StonkApp.setStaticUser(handler.getUser(temp.getUsername(), temp.getPassword()));
      login();
    }
    else{
      //feedback
      System.out.println(resp);
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
