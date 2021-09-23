package stonk;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class mainController {

    private User user;
    DataHandler handler = new DataHandler();
    
    @FXML
    private Label cashMoneyFlow; 
    
    @FXML
    private Label fullName; 

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private TextField cash;


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
    
    public void updateMain(){
        DataHandler dataHandler = new DataHandler();
        user = dataHandler.isLoginValid(username.getText(), password.getText());
        System.out.println("hei");
        System.out.println(user.getFirstName());
        System.out.println(user.getCash());

        fullName.setText(" " + user.getFirstName());
        cash.setText(Float.toString(user.getCash()));

    }


    public static void main(String[] args) {
        mainController controller = new mainController();
        controller.displayCash();
    }

}
