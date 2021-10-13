package ui;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StonkAppTest extends ApplicationTest{
    
    private StonkApp controller;
    private Parent root;

    @Override
    public void start(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("main/resources/ui/fxml/login.fxml"));
        System.out.println(fxmlLoader.getClass().toString());
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    } 

    @Test
    public void testLogin(){
        // String username = "casper";
        // String password = "12345";
        // clickOn("#username").write(username);
        // clickOn("#password").write(password);
        // clickOn("#login");
        //root.lookup("#login").toString();
        System.out.println("eyeyeyeyes");
    }

}
