package core;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

	private User user;
    DataHandler handler = new DataHandler();

	@BeforeEach
    public void setup(){
        user = new User("Tage", "Berg", "tagemb", "123", 10000, 23, null, false );
        //handler.newUser(user);
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
        assertEquals(1000, user.getCash(), "Not correct cash");
        user.setCash(20000);
        assertEquals(20000, user.getCash(), "setCash does not work");

        assertThrows(IllegalArgumentException.class,() -> {
            user.setCash(-1);
        });
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
        user.setPassword("XXX");
        assertFalse(user.getPassword() == "YYYY", "setPassword not working");
    }
    @Test
    public void testPortfolio(){
        assertNotEquals(user.getPortfolio(), true, "getPortfolio should be false");
        user.addToPortfoilio("GME", 20);
        user.addToPortfoilio("BB", 20);
        assertEquals(user.getPortfolio(), true, "getPortfolio should be true");
        assertThrows(IllegalArgumentException.class,() -> {
            user.addToPortfoilio("GME", -120);
        });

        user.removeFromPortfolio("GME", 20);
        assertEquals(user.getPortfolio().size(), 1, "The length og the portfolio should be 1");

    }



}