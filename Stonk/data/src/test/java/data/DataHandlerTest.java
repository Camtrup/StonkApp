package data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class DataHandlerTest {
    private DataHandler dataHandler;



    @BeforeEach
    public void setup(){
        dataHandler = new DataHandler(); 
    }

    @Test 
    public void checkFilePath(){
        assertEquals(1, 1);
    }
}
