package integration;


import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.SuperController;
import rest.StonkRestApplication;

public class IntegrationTest extends ApplicationTest {

    @Override 
    public void start(final Stage stage) throws Exception{
        SpringApplication.run(StonkRestApplication.class);
        final FXMLLoader loader = new FXMLLoader(SuperController.class.getResource("fxml/login.fxml"));
        final Parent root = loader.load();
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    @Test
    public void testApp() {
        clickOn("#registerUserNew");
        clickOn("#firstname").write("test");
        clickOn("#lastname").write("test");
        clickOn("#age").write("20");
        clickOn("#username").write("test");
        clickOn("#password").write("test");
        clickOn("#cash").write("100000");
        clickOn("#registerUser");
        clickOn("#myProfile");
        clickOn("#moneyAdd").write("10");
        clickOn("#moneyAddBtn");
        clickOn("#logOut");
        clickOn("#username").write("test");
        clickOn("#password").write("test");
        clickOn("#login");
        clickOn("#searchBar").write("apple");
        clickOn("#searchButton");
        clickOn("#amountStock").write("-1");
        clickOn("#buyBtn");
        clickOn("#sellBtn");
        clickOn("#addWatchList");
        clickOn("#amountStock").eraseText(2);
        clickOn("#amountStock").write("1");
        clickOn("#sellBtn");
        clickOn("#buyBtn");
        clickOn("#watchList");
        clickOn("#searchBar").write("apple");
        clickOn("#searchButton");
        clickOn("#amountStock").write("2");
        clickOn("#sellBtn");
        clickOn("#amountStock").eraseText(1);
        clickOn("#amountStock").write("1");
        clickOn("#sellBtn");
        clickOn("#myProfile");
        clickOn("#deleteUser");
    }
}


