package ui;

import java.io.IOException;

import core.Stonk;
import core.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SuperController {
    private StonkApp app = new StonkApp();

    public void changeScene(String fxml, User user, Stonk... stock){
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
