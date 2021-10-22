package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.User;

/**
 * JavaFX App
 */
public class StonkApp extends Application {

    private static Stage stg; //Used staticly for tests
    private static User user;
    private Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("fxml/login.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setTitle("Stonk");
        stage.setScene(new Scene(parent, 292,580));
        stage.setResizable(false);
        stage.show();
    }

    public void changeScene(String fxml){
        Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("fxml/" + fxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(pane== null){
            throw new IllegalArgumentException("file does not exist");
        }
        stg.getScene().setRoot(pane);
    }

    
    public static User getStaticUser(){
        return user;
    }

    public static void setStaticUser(User s){
        user = s;
    }

    public static Stage getStage(){
        return stg;
    }
    public static void setStage(Stage stage){
        stg = stage;
    }


    public static void main(String[] args) {
        launch(StonkApp.class, args);
    }

}