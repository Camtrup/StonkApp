package stonk;

import java.io.IOException;

import org.json.simple.JSONArray;

// import javafx.fxml.FXML; uvisst om vi bruker denne 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
import javafx.scene.Node;



public class StonkController {

    private Stage stage; 
    private Scene scene; 
    private User user;
    private int userIndex;

    public void fromLoginToRegister(ActionEvent event) throws IOException{
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("newUser.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
    }
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
    public void registerUser(ActionEvent event) throws IOException{
        if(username.getText().isBlank() || password.getText().isBlank() || firstname.getText().isBlank() || lastname.getText().isBlank() || age.getText().isBlank()|| cash.getText().isBlank()){
            throw new IllegalArgumentException("You must fill out all inputfields");
        }
        else {
            DataHandler dataHandler = new DataHandler();

            try {
                dataHandler.newUser(username.getText(), password.getText(), firstname.getText(), lastname.getText(), Integer.parseInt(age.getText()), 10000, new JSONArray());
                user = dataHandler.isLoginValid(username.getText(), password.getText());
                fromRegisterToMain(event);
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }

    @FXML
    public void isLoginValid(ActionEvent event) throws IOException {
        System.out.println();
        DataHandler dataHandler = new DataHandler();
        try {
            user = dataHandler.isLoginValid(username.getText().toString(), password.getText().toString());
            userIndex = dataHandler.findUser(username.getText());
            if(user.equals(null)){
                throw new IllegalArgumentException("Password is incorrect");
            }
            else {
                fromLoginToMain(event);
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        } 
    }


    public void fromLoginToMain(ActionEvent event) throws IOException{
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();


    }

    public void fromRegisterToMain(ActionEvent event) throws IOException{
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField searchbar;

    @FXML
    public void generateStockPage(){
        Stonk stock = new Stonk();
        stock.getStockInfo(searchbar.getText();
        Stage stonkStage = new Stage();
        stonkStage.setTitle(stock.getName());
        
        Label price = new Label("Price : " + stock.getPrice());
        Label ticker = new Label(stock.getTicker().toUpperCase());
        Button buy = new Button("Buy stock");
        
        TextField buyCount = new TextField();
        buyCount.setPromptText("Amount of stocks");
        
        buy.setOnAction(Event ->{
            try{
                buyStocks(stock, Integer.parseInt(buyCount.getText()));
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
            }
        });
        
        VBox x = new VBox(ticker, price, buyCount, buy);
        stonkStage.setScene(new Scene(x));
        stonkStage.show();
    }

    private void buyStocks(Stonk stock, int count){
            DataHandler handler = new DataHandler();
            handler.addToPortfoilio(userIndex, stock.getTicker(), stock.getPrice(), count);
    }



public static void main(String[] args) {
    DataHandler d = new DataHandler();
    d.getAllUsers();
}

}
