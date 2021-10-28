package restapi;
import com.google.firebase.database.*;

import java.io.IOException;

public class ShowDbChanges implements Runnable {
    public void run() {
        FireStoreHandler fbs = null;
        try {
            fbs = new FireStoreHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatabaseReference ref = fbs.getDb()
                .getReference("/");
        ref.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }


            public void onCancelled(DatabaseError error) {
                System.out.print("Error: " + error.getMessage());
            }
        });


    }
}