package data;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.junit.jupiter.api.Test;

public class DataHandlerTest {

    Gson gson =  new Gson();
    DataHandler handler = new DataHandler();

    @Test 
    public void checkReadAndWrite() throws IOException{
        JsonArray current = handler.getAllUsers();
        JsonArray test = new JsonArray();
        handler.writeToFile(gson.toJson(test));
        assertTrue(handler.getAllUsers().equals(test));
        handler.writeToFile(gson.toJson(current));
        
    }
}
