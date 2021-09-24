package stonk;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class StonkTest{


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