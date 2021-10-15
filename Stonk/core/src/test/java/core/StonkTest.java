package core;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



public class StonkTest {
    
	private User user;
    private Stonk stonk;
    DataHandler handler = new DataHandler();

	@BeforeEach
    public void setup(){
        stonk = new Stonk();
        stonk.getStockInfo("BB");
    }

    @Test
    public void testName(){
        assertEquals(stonk.getName(), "BlackBerry Ltd.");
    }

//getName(){
//getPrice(){
    
//getTicker(){

//getPriceChange() {


}
