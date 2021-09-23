package stonk;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainController {

    private User user;
    DataHandler handler = new DataHandler();
    
    @FXML
    private TextField cashMoneyFlow; 

    @FXML
    private TextField searchBar;

    @FXML
    public void toStockPage(){
        Parent s = searchBar.getScene().getRoot();
        user = handler.generateUser(s.getId());
        
    }

    @FXML
    public void displayCash(){
        cashMoneyFlow.setText(Float.toString(user.getCash()));
    }

    public void setUser(User user){
        this.user = user; 
    }
    
    public static void main(String[] args) {
        mainController controller = new mainController();
        controller.displayCash();
    }

}
