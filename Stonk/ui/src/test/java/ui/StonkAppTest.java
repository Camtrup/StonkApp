package ui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;



public class StonkAppTest extends ApplicationTest {
    private loginController controller;

    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root,800,600));
        stage.show();
        StonkApp.stg = stage;
    }

    @Test
    public void testLoginFalse() throws InterruptedException{
        try {
            clickOn("#username").write("c");
            clickOn("#password").write("2");
            clickOn("#login");
        }
        catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Username not in databse");
        }

        try {
            clickOn("#username").write("asper");
            clickOn("#password").write("2");
            clickOn("#login");
        }
        catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Password is incorrect");
        }
    }

    @Test
    public void testRegisterFalse(){
        try {
            clickOn("#registerUserNew");
            clickOn("#firstname").write("test");
            clickOn("#lastname").write("test");
            clickOn("#username").write("test");
            clickOn("#password").write("test");
            clickOn("#age").write("notInt");
            clickOn("#cash").write("notFloat");
            clickOn("#registerUser");
        }
        catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Age must be an integer");
        }

        try {
            clickOn("#age").eraseText(6).write("10");
            clickOn("#registerUser");
        }
        catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Cash must be a number");
        }

        try {
            clickOn("#cash").eraseText(8).write("10");
            clickOn("#registerUser");
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "You need to be over 18 years");
        }
    }

 

    @Test
    public void testLoginTrue() {
        clickOn("#username").write("casper");
        clickOn("#password").write("12345");
        clickOn("#login");
    }
    
}