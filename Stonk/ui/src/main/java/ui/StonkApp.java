package ui;

import core.User;
import java.io.IOException;
import java.lang.reflect.Constructor;

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
  public void changeScene(String fxml, User user) {
    Parent pane = null;
    try {
      FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/" + fxml));
      if(fxml.contains("main")){
        load.setController(new MainController(user));
      }
      else if(fxml.contains("profile")){
        load.setController(new ProfileController(user));
      }
      else if(fxml.contains("stock")){
        load.setController(new StockPageController(user));
      }
      pane = load.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (pane == null) {
      throw new IllegalArgumentException("file does not exist");
    }
    stg.getScene().setRoot(pane);
  }

  protected Scene getScene(){
    return stg.getScene();
  }

  protected static void setStage(Stage stage) {
    stg = stage;
  }
}