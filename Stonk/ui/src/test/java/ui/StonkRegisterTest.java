package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;


public class StonkRegisterTest extends ApplicationTest{

    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root,800,600));
        stage.show();
        StonkApp.stg = stage;
    }

    @Test 
    public void testRegisterFalse(){
        String[][] arr = new String[][]{{"#age","a","1","Age must be an integer"},
                                        {"#age","2","0","Cash must be a number"},
                                        {"#cash","a","1","Cash must be a number"},
                                        {"#cash","10000","0","First name cannot be blank"},
                                        {"#firstname","test","0","Last name cannot be blank"},
                                        {"#lastname","test","0","Username cannot be blank"},
                                        {"#username","test","1","Username is already registered"},
                                        {"#password","","0","Password cannot be blank"}};


        clickOn("#registerUserNew");
        for(String[] param : arr) {
            try {
                clickOn(param[0]).write(param[1]);
                clickOn("#registerUser");
            }
            catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(), param[3]);
            }
            clickOn(param[1]).eraseText(Integer.parseInt(param[2]));
        }
    }
    
    
    
}
