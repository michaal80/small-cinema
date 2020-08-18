package demo.details;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DetailsControllerTest {

	@Autowired
	private DetailsController detailsController;

	@Test
	public void testFindOne() {

		ResponseEntity<OpenMovieDetails> response = detailsController.findOne("tt0232500");
		assertEquals("tt0232500", response.getBody().getImdbID());
		log.info(response.getBody().getTitle());

	}

	@Test
	public void testFindNonExistingOne() {

		ResponseEntity<OpenMovieDetails> response = detailsController.findOne("aaaa");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

}
