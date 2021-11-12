package data;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.junit.Test;

public class DataHandlerTest {

    DataHandler handler = new DataHandler();

    @Test 
    public void checkReadAndWrite() throws IOException{
        JSONArray current = handler.getAllUsers();
        JSONArray test = new JSONArray();
        handler.writeToFile(test.toJSONString());
        assertTrue(handler.getAllUsers().equals(test));
        handler.writeToFile(current.toJSONString());
        
    }
}
