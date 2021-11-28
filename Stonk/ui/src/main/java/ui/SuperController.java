package ui;

import core.Stonk;
import core.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * The function for running stonkApp.
 */
public class SuperController {
  private StonkApp app = new StonkApp();

  /**
   * Changing the scene. 
   *
   * @param fxml String.
   * @param user Carries the user onwards.
   * @param stage stage.
   * @param stock stock.
   */
  public void changeScene(String fxml, User user, Stage stage, Stonk... stock) {
    stage.close();
    try {
      FXMLLoader load = new FXMLLoader(SuperController.class.getResource("fxml/" + fxml));
      if (fxml.contains("main")) {
        load.setController(new MainController(user));
      } else if (fxml.contains("profile")) {
        load.setController(new ProfileController(user));
      } else if (fxml.contains("stock")) {
        load.setController(new StockPageController((user), stock[0]));
      }
      app.newPane(load);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
