package ui;

import core.Stonk;
import core.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * The function for running stonkApp.
 */
public class SuperController {
  private StonkApp app = new StonkApp();

  /**
   * The function for changing the different fxml files to show another site.
   *
   * @param fxml  is a parameter for wich fxml file to access.
   * @param user  is a parameter for the user that is logged on.
   * @param stock is a parameter for what stock you are searching up.
   * @throws IllegalArgumentException if argument is not allowed
   */
  public void changeScene(String fxml, User user, Stonk... stock) {
    Parent pane = null;
    try {
      FXMLLoader load = new FXMLLoader(SuperController.class.getResource("fxml/" + fxml));
      if (fxml.contains("main")) {
        load.setController(new MainController(user));
      } else if (fxml.contains("profile")) {
        load.setController(new ProfileController(user));
      } else if (fxml.contains("stock")) {
        load.setController(new StockPageController((user), stock[0]));
      }
      pane = load.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (pane == null) {
      throw new IllegalArgumentException("file does not exist.");
    }
    app.getScene().setRoot(pane);
  }
}
