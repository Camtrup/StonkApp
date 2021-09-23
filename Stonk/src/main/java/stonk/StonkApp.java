package stonk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class StonkApp extends Application {

    private static Stage stg; 

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        stage.setTitle("Stonk");
        stage.setScene(new Scene(fxmlLoader, 800,600));
        stage.show();
    }

    public void changeScene(String fxml, String username) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        pane.setId(username);
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(StonkApp.class, args);
    }

}