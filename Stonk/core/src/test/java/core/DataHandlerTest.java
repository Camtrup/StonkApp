package core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.chart.PieChart.Data;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class DataHandlerTest {
    
	private User user;
    private Stonk stonk;
    DataHandler handler = new DataHandler();

	@BeforeEach
    public void setup(){
        stonk = new Stonk();
        stonk.getStockInfo("BB");
        handler.setCash(0, 100);
        handler.addToPortfoilio(0, "BB", stonk.getPrice(), 20);
    }  


    @Test
    public void testPortfolio(){

    }
}
