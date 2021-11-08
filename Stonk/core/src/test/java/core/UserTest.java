package core;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	private User user;
    String username = "tagemb";

	@BeforeEach
    public void setup(){
        //user = handler.generateUser("casper");
        user = new User("Tage", "Berg", "tagemb", "123", 10000, 23, null, false);
        
        
    }

    @Test
    public void testAge(){
        assertEquals(23, user.getAge(), "Not correct age");
        assertThrows(IllegalArgumentException.class,() -> {
            user.setAge(17);
        });
    }

    @Test
    public void testCash(){
        assertEquals(10000, user.getCash(), "Not correct cash");

        
        //user.setCash(20000);
        //assertEquals(20000, user.getCash(), "setCash does not work");

/*         assertThrows(IllegalArgumentException.class,() -> {
            user.setCash(-1);
        }); */
    }

    @Test
    public void testUsername(){
        assertThrows(IllegalArgumentException.class,() -> {
            user.setUserName("");
        });
        assertTrue(user.getUserName() == "tagemb", "getUsername not correct");
        user.setUserName("XYX");
        assertFalse(user.getUserName() == "T", "setUserName not working");
        /* assertThrows(IllegalArgumentException.class,() -> {
            handler.findUser("tagemb");
        }); */
    }

    @Test
    public void testName(){
        assertTrue(user.getFirstName() == "Tage", "getfirstName not correct");
        user.setFirstName("X");
        assertFalse(user.getFirstName() == "Tage", "setFirstName not working");

        assertTrue(user.getLastName() == "Berg", "getfirstName not correct");
        user.setLastName("Y");
        assertFalse(user.getLastName() == "XXXX", "setFirstName not working");
    }
    @Test
    public void testPassword(){
        assertTrue(user.getPassword() == "123", "getPassword not correct");
        assertFalse(user.getPassword() == "YYYY", "setPassword not working");
    }
    @Test
    public void testPortfolio(){
        //JSONArray p = handler.getPortfolio(handler.findUser("tagemb"));
        //assertEquals(user.getPortfolio(), p, "getPortfolio should be false");
        /* 
        getPortfolio -> handler.getPortfolio -> handler.findUser(username) -> 

        user.addToPortfoilio("GME", 20);
        user.addToPortfoilio("BB", 20);
        assertEquals(user.getPortfolio(), true, "getPortfolio should be true");
        assertThrows(IllegalArgumentException.class,() -> {
            user.addToPortfoilio("GME", -120);
        });

        user.removeFromPortfolio("GME", 20);
        assertEquals(user.getPortfolio().size(), 1, "The length og the portfolio should be 1");
 */
    }



}