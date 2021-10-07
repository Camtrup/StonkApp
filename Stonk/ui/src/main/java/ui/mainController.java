package ui;

import java.io.IOException;

import core.DataHandler;
import core.Stonk;
import core.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class mainController {
    DataHandler handler = new DataHandler();
    private User user; 
    Stonk s = new Stonk();

    
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
    private TextField searchbar;
    @FXML
    private Text balanceString; 
    @FXML
    private GridPane gridpane;
    @FXML
    private TextField searchBar;


    public void updateMain(){
        Parent s = searchBar.getScene().getRoot();
        user = handler.generateUser(s.getId());
        System.out.println(user.getUserName());
        displayOnMain();
    }


    public void displayOnMain(){
        cashMoneyFlow.setText(Float.toString(user.getCash()) + " $");
        cashMoneyFlow.setStyle("-fx-text-fill: white;");
        fullName.setText((user.getFirstName()) + "  " + (user.getLastName()));
    }

    public void toStockPage() throws IOException{
        StonkApp app = new StonkApp();
        app.changeScene("stockPage.fxml",user.getUserName());
    }

}
