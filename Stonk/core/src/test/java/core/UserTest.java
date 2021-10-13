package core;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
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

    @Test
    public void testCash(){
        assertThrows(IllegalArgumentException.class,() -> {
            user.setCash(-1);
        });
    }

    @Test
    public void testUsername(){
        assertThrows(IllegalArgumentException.class,() -> {
            user.setCash(-1);
        });
    }


}