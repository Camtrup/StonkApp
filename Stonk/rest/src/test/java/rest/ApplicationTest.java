package rest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = rest.StonkRestApplication.class)
public class ApplicationTest {

	@Autowired
	private StonkRestController controller;

	@Test
	public void contextLoads() {
		assertThat(!controller.equals(null));
	}
	@Test
	public void testnoe() {
		assertEquals(1,1);
	}

}
