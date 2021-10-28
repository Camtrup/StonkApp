package restapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FireStoreHandler {


    FirebaseDatabase db;

    public FireStoreHandler() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("key.json").getFile());

        FileInputStream fis = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(fis))
            .setDatabaseUrl("https://stonk-3ca9b-default-rtdb.europe-west1.firebasedatabase.app")
            .build();

        FirebaseApp.initializeApp(options);

        db = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getDb() {
        return db;
    }


}
