package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StonkUserTest extends ApplicationTest{

    MockServerTest mock = new MockServerTest();

    @BeforeEach
    public void setup(){
        mock.setup();
    }
    @AfterEach
    public void stop(){
        mock.stop();
    }

    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    HttpHandler handler = new HttpHandler();


    @Test
    public void testRegisterAndDeleteProfile(){
        String[][] arr = new String[][]{{"#firstname","test"},
                                        {"#lastname","test"},
                                        {"#age","20"},
                                        {"#username","test2"},
                                        {"#password","test"},
                                        {"#cash","10000"},
                                        {"#registerUser",""},
                                        {"#myProfile",""},
                                        {"#moneyAdd","10"},
                                        {"#moneyAddBtn","0"},
                                        {"#deleteUser",""}
                                    };

        clickOn("#registerUserNew");
        for(String[] param : arr) {
            clickOn(param[0]).write(param[1]);
        }
    }
    
    @Test
    public void testLoginBackLogout(){
        String[][] arr = new String[][]{{"#username","test","0",""},
                                        {"#password","test","0",""},
                                        {"#login","","0",""},
                                        {"#watchList","","0",""},
                                        {"#myStocks","","0",""},
                                        {"#myProfile",""},
                                        {"#toMain",""},
                                        {"#myProfile",""},
                                        {"#logOut",""}};
        for(String[] param : arr) {
            clickOn(param[0]).write(param[1]);
        }
    }
}
