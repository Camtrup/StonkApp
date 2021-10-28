package restapi;

import com.google.firebase.database.*;

public class Main {
    
public static void main(String[] args) {

    Thread t = new Thread(new ShowDbChanges());

        t.run();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
    
}

}


