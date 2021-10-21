package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;


public class StonkLoginTest extends ApplicationTest{

    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root,800,600));
        stage.show();
        StonkApp1.stg = stage;
    }


    @Test
    public void testLoginFalse() {
        try {
            clickOn("#username").write("tes");
            clickOn("#password").write("1");
            clickOn("#login");
        }
        catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Username not in databse");
        }

        try {
            clickOn("#username").write("t");
            clickOn("#password").write("2");
            clickOn("#login");
        }
        catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Password is incorrect");
        }
    }

    @Test
    public void testLoginTrue() {
        clickOn("#username").write("test");
        clickOn("#password").write("test");
        clickOn("#login");
    }
    
}
