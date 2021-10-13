package ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class loginControllerTest extends ApplicationTest{

    private loginController controller = new loginController();

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
  }

  @BeforeEach
  public void setup(){

  }

  @Test
  public void testController(){
    assertNotNull(controller);
  }
  
  @Test
  public void testLogin(){
      String username = "casper";
      String password = "12345";
      clickOn("#username").write(username);
      clickOn("#username").write(password);
      clickOn("#login");
  }
}
