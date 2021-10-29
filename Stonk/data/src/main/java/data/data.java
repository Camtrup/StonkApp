package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class data {
    private String filePath = getClass().getClassLoader().getResource("database.json").toString();
    private File file = new File(getClass().getClassLoader().getResource("database.json").getFile());

    private String path = System.getProperty("user.dir") + "/Stonk/data/src/main/resources/database.json";

    JSONParser parser = new JSONParser();

    public static void main(String[] args) throws IOException, ParseException {
        data d = new data();
        System.out.println(d.file);
        

        FileReader f = new FileReader(d.path);
        JSONObject s = (JSONObject) d.parser.parse(f);
        System.out.println(s.toJSONString());


    }
}
