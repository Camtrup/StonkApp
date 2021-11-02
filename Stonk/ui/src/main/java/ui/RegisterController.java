package ui;

import core.User;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;

/**
 * Controller for register page.
 */
public class RegisterController {
  
  @FXML
  private TextField age;
  @FXML
  private TextField cash;
  @FXML
  private Button exitBtn;
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private TextField password;
  @FXML
  private Button registerUser;
  @FXML
  private TextField username;

  /**
   * Login from the register page. 
   *
   * @throws IOException if not possible.
   */
  public void loginFromRegister() throws IOException {
    StonkApp app = new StonkApp();
    app.changeScene("mainPage.fxml");
  }

  /**
   * Back to login.
   */
  public void backToLogin() {
    StonkApp app = new StonkApp();
    app.changeScene("login.fxml");
  }

  /**
   * Register new user. 
   *
   * @throws IOException if one or more of the fields are not OK.
   */
  @FXML
  public void registerUser() throws IOException {
    int tempInt = -1;
    float tempFloat = -1;
    try {
      tempInt = Integer.parseInt(age.getText());
      tempFloat = Float.parseFloat(cash.getText());
      StonkApp.setStaticUser(new User(firstname.getText(), lastname.getText(),
          username.getText(), password.getText(), Float.parseFloat(cash.getText()),
          Integer.parseInt(age.getText()), new JSONArray(), true));
      loginFromRegister();
    } catch (Exception e) {
      if (tempInt == -1) {
        System.out.println(new IllegalArgumentException("Age must be an integer"));
      } else if (tempFloat == -1) {
        System.out.println(new IllegalArgumentException("Cash must be a number"));
      } else {
        System.out.println(e);
      }
    }
  }

}
