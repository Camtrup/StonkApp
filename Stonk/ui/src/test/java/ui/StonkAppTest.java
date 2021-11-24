package ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class StonkAppTest extends ApplicationTest {

    
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
        StonkApp.setStage(stage);
    }

    @Test
    public void testLoginTrue() {
        mock.setup();
        clickOn("#username").write("test");
        clickOn("#password").write("test");
        clickOn("#login");
        clickOn("#myProfile");
        mock.stop();
    }
}