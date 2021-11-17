package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
  private User user;
  String username = "tagemb";

  @BeforeEach
  public void setup() {
    // user = handler.generateUser("casper");
    user = new User("Tage", "Berg", "tagemb", "123", 10000, 23, new ArrayList<Stonk>(), new ArrayList<Stonk>(), false);

  }

  @Test
  public void testAge() {
    assertEquals(23, user.getAge(), "Not correct age");
    assertThrows(IllegalArgumentException.class, () -> {
      user.setAge(17);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      user.setAge(0);
    });

  }

  @Test
  public void testCash() {
    assertEquals(10000, user.getCash(), "Not correct cash");
    assertThrows(IllegalArgumentException.class, () -> {
      user.addCash(-2);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      user.addCash(0);
    });
    user.addCash(100);
    assertThrows(IllegalArgumentException.class, () -> {
      user.removeCash(-2);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      user.removeCash(200000);
    });
    user.removeCash(100);

    // user.setCash(20000);
    // assertEquals(20000, user.getCash(), "setCash does not work");

    /*
     * assertThrows(IllegalArgumentException.class,() -> { user.setCash(-1); });
     */
  }

  @Test
  public void testUsername() {
    assertThrows(IllegalArgumentException.class, () -> {
      user.setUserName("");
    });
    assertTrue(user.getUsername() == "tagemb", "getUsername not correct");
    user.setUserName("XYX");
    assertFalse(user.getUsername() == "T", "setUserName not working");
    /*
     * assertThrows(IllegalArgumentException.class,() -> {
     * handler.findUser("tagemb"); });
     */
  }

  @Test
  public void testName() {
    assertThrows(IllegalArgumentException.class, () -> {
      user.setFirstName("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      user.setLastName("");
    });
    assertTrue(user.getFirstName() == "Tage", "getfirstName not correct");
    user.setFirstName("X");
    assertFalse(user.getFirstName() == "Tage", "setFirstName not working");

    assertTrue(user.getLastName() == "Berg", "getfirstName not correct");
    user.setLastName("Y");
    assertFalse(user.getLastName() == "XXXX", "setFirstName not working");
  }

  @Test
  public void testPassword() {
    assertTrue(user.getPassword() == "123", "getPassword not correct");
    assertFalse(user.getPassword() == "YYYY", "setPassword not working");
    assertThrows(IllegalArgumentException.class, () -> {
      user.setPassword("");
    });
  }

  @Test
  public void testEncrypted() {
    assertEquals(user.encryptPassword("123"), "202cb962ac59075b964b07152d234b70");
  }

  @Test
  public void testPortfolio() {
    assertThrows(IllegalArgumentException.class, () -> {
      user.addToPortfoilio("apple", -1);
    });
    
    user.addToPortfoilio("gme", 1);
    assertEquals(1, user.getPortfolio().size());
    user.removeFromPortfolio("gme", 1);
    assertEquals(0, user.getPortfolio().size());
    
    assertThrows(IllegalArgumentException.class, () -> {
      user.removeFromPortfolio("apple", -1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      user.removeFromPortfolio("apple", 10);
    });

  }

  @Test
  public void testWatchlist() {

    user.addToWatchList("gme", 1);
    assertThrows(IllegalArgumentException.class, () -> {
      user.addToWatchList("gme", 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      user.removeFromWatchList("dnb", 1);
    });
    user.removeFromWatchList("gme", 1);

  }

  @Test
  public void testUser() {
    User user2 = new User("test3", "test3", "test3", "123", 10000, 22, new ArrayList<Stonk>(), new ArrayList<Stonk>(),
        true);
    assertEquals(10000, user2.getCash());
    assertEquals("test3", user2.getFirstName());
    assertEquals("test3", user2.getLastName());
  }
}