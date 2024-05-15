package net.cambium.examples.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TimeControllerTests.
 *
 *  Simple test to show that our REST API returns the current time. 
 *
 * @author Baruch Speiser, Cambium.
 */
@SpringBootTest
class TimeControllerTests {

	@Test
	void testNow() {
	  TimeController time = new TimeController();
	  System.out.println(time.now());
	}

}
