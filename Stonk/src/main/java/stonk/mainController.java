package stonk;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class mainController {

    private User user;
    
    @FXML
    private TextField cashMoneyFlow; 

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
