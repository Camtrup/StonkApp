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

  /**
   * Starts the app.
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("fxml/login.fxml"));
    Parent parent = fxmlLoader.load();
    stage.setTitle("Stonk");
    stage.setScene(new Scene(parent));
    stage.setResizable(false);
    stage.show();
  }

  public void newPane(FXMLLoader load) throws IOException{
    Stage stage = new Stage();
    Parent parent = load.load();
    stage.setTitle("Stonk");
    stage.setScene(new Scene(parent));
    stage.setResizable(false);
    stage.show();
  }

}