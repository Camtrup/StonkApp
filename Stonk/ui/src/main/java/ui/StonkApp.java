package ui;

import java.io.IOException;

import core.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class StonkApp extends Application {

  private static Stage stg; // Used staticly for tests
  private static User user;
  // private Stage stage; Unused

  /**
   * Starts the app.
   */
  @Override
  public void start(Stage stage) throws IOException {
    setStage(stage);
    // stage.setResizable(false); Dupe?
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("fxml/login.fxml"));
    Parent parent = fxmlLoader.load();
    stage.setTitle("Stonk");
    stage.setScene(new Scene(parent, 292, 580));
    stage.setResizable(false);
    stage.show();
  }

  /**
   * Change scene. 
   */
  public void changeScene(String fxml) {
    Parent pane = null;
    try {
      pane = FXMLLoader.load(getClass().getResource("fxml/" + fxml));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (pane == null) {
      throw new IllegalArgumentException("file does not exist");
    }
    stg.getScene().setRoot(pane);
  }

  /**
   * Getter for the static user.
   *
   * @return the static user.
   */
  public static User getStaticUser() { // Using users constructor to bypass spotbugs
    if (user == null) {
      return null;
    }
    return new User(user.getFirstName(), user.getLastName(), 
        user.getUserName(), user.getPassword(), user.getCash(),
        user.getAge(), user.getPortfolio(), false);
  }

  /**
   * Sets the static user.
   *
   * @param s this is user. 
   * @throws NullPointerException if its null, this throws. 
   */
  public static void setStaticUser(User s) throws NullPointerException { //Using users constructor
    if (s == null) {
      user = null;
      return;
    }
    user = new User(s.getFirstName(), s.getLastName(), s.getUserName(),
        s.getPassword(), s.getCash(), s.getAge(),
        s.getPortfolio(), false);
  }

  // public static Stage getStage(){ Finner ikke hvor vi bruker den
  // return stg;
  // }
  protected static void setStage(Stage stage) {
    stg = stage;
  }

  public static void main(String[] args) {
    launch(StonkApp.class, args);
  }

}