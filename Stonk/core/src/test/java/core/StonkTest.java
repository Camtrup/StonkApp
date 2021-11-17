package core;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class StonkTest {

    private Stonk stonk;

	@BeforeEach
    public void setup(){
        stonk = new Stonk("BB", 3);
    }

    @Test
    public void testName(){
        assertEquals(stonk.getName(), "BlackBerry Ltd.");
    }

    @Test
    public void testPrice(){
        assertTrue(stonk.getPrice()>0);
    }


    @Test
    public void testTicker(){
        assertTrue(stonk.getTicker().getClass().equals(String.class));
    }
  

    @Test
    public void testPriceChange(){
        assertTrue(stonk.getPriceChange().getClass().equals(String.class));
    }

    @Test
    public void testContructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            stonk = new Stonk(null, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stonk = new Stonk("", 1);
        });
    }
    @Test
    public void testAverage(){
        stonk.setNewCount(new Stonk("BB", 3));
        assertEquals(0,stonk.getCount());

        assertThrows(IllegalArgumentException.class , () -> {
            stonk.setNewCount(new Stonk("gme", 2));
        }); 

    }

    @Test 
    public void testCount(){
        assertThrows(IllegalArgumentException.class , () -> {
            stonk.setNewAverage(new Stonk("gme", 2));
        }); 
    }

}
