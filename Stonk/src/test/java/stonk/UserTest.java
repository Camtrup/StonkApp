package stonk;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

	private User user;
    
	@BeforeEach
    public void setup(){
        user = new User("Tage", "Berg", "tagemb", "123", 10000, 23, null, false );
    }

    @Test
    public void testAge(){
        assertThrows(IllegalArgumentException.class,() -> {
            user.setAge(17);
        });
    }


}