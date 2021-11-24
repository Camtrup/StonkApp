package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StonkBuyTest extends ApplicationTest{


    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root,800,600));
        stage.show();
        StonkApp.setStage(stage);
    }

    MockServerTest mock = new MockServerTest();

    @BeforeEach
    public void setup(){
        mock.setup();
    }
    @AfterEach
    public void stop(){
        mock.stop();
    }

    //Tests all the vital functions around buying and selling stocks
    //Iterates through the array and sends the arguments into the clickOn- and write-functions
    //Compares the output with the last argument which is the throwable
    @Test
    public void testStocks(){
        String[][] arr = new String[][]{{"#username","test","0",""},
                                        {"#password","test","0",""},
                                        {"#login","","0",""},
                                        {"#searchBar","","0",""},
                                        {"#searchButton","","0","Cannot be blank"},
                                        {"#searchBar","apple","0",""},
                                        {"#searchButton","","0",""},
                                        {"#addWatchList","","0",""},
                                        {"#addWatchList","","0",""},
                                        {"#amountStock","-1","0",""},
                                        {"#sellBtn","","0","Amount of stocks cant be negative or 0"},
                                        {"#buyBtn","","0","Amount of stocks cant be negative or 0"},
                                        {"#amountStock","","2",""},
                                        {"#amountStock","1","0",""},
                                        {"#sellBtn","","0",""},  
                                        };

        for(String[] param : arr) {
            try {
                clickOn(param[0]).write(param[1]);
            }
            catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(), param[3]);
            }
            if(param[2] != "0"){
                clickOn(param[0]).eraseText(Integer.parseInt(param[2]));
            }
        }
    }
}