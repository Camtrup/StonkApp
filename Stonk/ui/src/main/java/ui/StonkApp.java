package ui;

import java.io.IOException;

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
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("fxml/login.fxml"));
    Parent parent = fxmlLoader.load();
    stage.setTitle("Stonk");
    stage.setScene(new Scene(parent, 292, 580));
    stage.setResizable(false);
    stage.show();
  }
  protected Scene getScene(){
    return stg.getScene();
  }

  protected static void setStage(Stage stage) {
    stg = stage;
  }
}