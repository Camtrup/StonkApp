package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;


/**
 * Class datahandler.
 */
public class DataHandler {

  Gson gson = new Gson();

  // The filepath now dynamiclly finds the working directory of the user, and the
  // adds the path to the database
  private String file = System.getProperty("user.dir");

  // When running with "javafx:run", the working directory will be "ui".
  // This method removes the path into "ui", so the path finds the file in "data"
  private void adaptFilePath() {
    file = file.substring(0, file.indexOf("gr2135"))
        + "gr2135/Stonk/data/src/main/resources/database.json";
  }

  /**
   * Get all users.
   *
   * @return array of all users.
   */
  public JsonArray getAllUsers() {
    adaptFilePath();
    JsonArray object = null;
    JsonReader reader;
    try {
      InputStream inputStream = new FileInputStream(file);
      reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
      object = gson.fromJson(reader, JsonArray.class);
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return object;
  }

  /**
   * Writes the array to file.
   *
   * @param arr given array.
   * @throws IOException if it doesnt work.
   */
  public void writeToFile(String arr) throws IOException {
    adaptFilePath();
    FileOutputStream fileStream = new FileOutputStream(file, false);
    Writer writer = new OutputStreamWriter(fileStream, StandardCharsets.UTF_8);
    writer.write(arr);
    writer.close();
  }
}
