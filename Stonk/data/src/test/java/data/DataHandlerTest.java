package data;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class DataHandlerTest {

    private String currentData = "";
    DataHandler handler = new DataHandler();

    @BeforeEach
    public void setup(){
        currentData = handler.getAllUsers().toJSONString();
    }
    
    @AfterAll
    public void returnState() throws IOException{
        handler.writeToFile(currentData);
    }


    @Test 
    public void checkReadAndWrite() throws IOException{
        JSONArray test = new JSONArray();
        handler.writeToFile(test.toJSONString());
        assertTrue(handler.getAllUsers().equals(test));
        
    }
}
