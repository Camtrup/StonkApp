package ui;

import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller fro profile.
 */
public class ProfileController {

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
  private Label illegalArgument;

  private User user;
  StonkApp app = new StonkApp();
  HttpHandler handler = new HttpHandler();
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
    System.out.println(user.getCash());
    try {
    float cash = Float.parseFloat(moneyAdd.getText());
    String resp = handler.addMoreValue(user.getUsername(), user.getPassword(), cash);
    if (resp.contains("200")){
      //user.addCash(cash);
      balance.setText(Float.toString(user.getCash()) + " $");
      addedPrompt.setText("Congrats, funds have been added");
    }
    else {
      //Feedback
      illegalArgument.setText(resp);
      System.out.println(resp);
    }
  }
    catch(IllegalArgumentException e){
      //Feedback
      System.out.println(e);
    }
    System.out.println(user.getCash());

  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    StonkApp.setStaticUser(null);
    app.changeScene("login.fxml");

  }

  public void deleteUser() {
    String resp = handler.removeUser(user.getUsername(), user.getPassword());
    if(resp.contains("200")){
      logOut();
    }
    else {
      //Feedback
      illegalArgument.setText(resp);
      System.out.println(resp);
    }
  }

  public void toMain() {
    app.changeScene("mainPage.fxml");
  }

  @FXML
  private void initialize() {
    this.user = StonkApp.getStaticUser();
  }


  // Functions for changing the colour of the buttons when hovering.
  public void btnHoverAddMoney(){
    moneyAddBtn.setStyle("-fx-background-color: #3f4652;");
  }
  public void btnNormalAddMoney(){
    moneyAddBtn.setStyle("-fx-background-color: #090a0c;");
  }
  public void btnHoverLogOut(){
    logOut.setStyle("-fx-background-color: black;");
  }
  public void btnNormalLogOut(){
    logOut.setStyle("-fx-background-color: grey;");
  }
  public void btnHoverDelete(){
    deleteUser.setStyle("-fx-background-color: black;");
  }  
  public void btnNormalDelete(){
    deleteUser.setStyle("-fx-background-color: grey;");
  }
  public void btnHoverBack(){
    toMain.setStyle("-fx-background-color: #3f4652;");
  }  
  public void btnNormalBack(){
    toMain.setStyle("-fx-background-color: #090a0c;");
  }

}
