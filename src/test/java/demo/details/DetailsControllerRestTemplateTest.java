package demo.details;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DetailsControllerRestTemplateTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testFindDetailsOfMovie() throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity("/details/tt0232500", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.toString().contains("The Fast and the Furious"));

	}

}