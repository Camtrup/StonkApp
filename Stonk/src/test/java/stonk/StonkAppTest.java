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

public class StonkAppTest {

	private Stonk stonk;

	@BeforeEach
	public void setup() {
		this.stonk = new Stonk();

	}

	@Test
	@DisplayName("Constructor Test")
	public void teststonk() {

	}

	@Test
	@DisplayName("age over 18")
	public void testAge() {
	

	}



	@Test
	@DisplayName("Changes done?")
	public void testCheckIdentical() {
	
	}

}