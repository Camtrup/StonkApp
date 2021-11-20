package ui;

import core.User;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for register page.
 */
public class RegisterController extends SuperController{
  
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
  @FXML
  private Label feedBack;

  private User user = null;

  HttpHandler handler = new HttpHandler();

  /**
   * Login from the register page. 
   *
   * @throws IOException if not possible.
   */
  public void loginFromRegister() throws IOException {
    super.changeScene("mainPage.fxml", user);
  }

  /**
   * Back to login.
   */
  public void backToLogin() {
    super.changeScene("login.fxml", user);
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

      if(firstname.getText().isBlank() || lastname.getText().isBlank() || username.getText().isBlank() || password.getText().isBlank()){
        throw new IllegalArgumentException("All fields must be filled out");
      }

      User temp = new User(username.getText(), password.getText());
      String resp = handler.newUser(firstname.getText(), lastname.getText(), username.getText(),
          password.getText(), tempFloat, tempInt);
      if (resp.contains("200")) {
        user = handler.getUser(temp.getUsername(), temp.getPassword());
        loginFromRegister();
      } else {
        feedBack.setText(resp);
        System.out.println(resp);
      }

    } catch (IllegalArgumentException e) {
      if (tempInt == -1) {
        feedBack.setText("Age must be an integer");
      } else if (tempFloat == -1) {
        feedBack.setText("Cash must be a number");
      } 
      else {
        feedBack.setText(e.getMessage());
      }
    }
  }
}
