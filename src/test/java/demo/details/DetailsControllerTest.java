package demo.details;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DetailsControllerTest {

	@Autowired
	private DetailsController detailsController;

	@Test
	public void testFindOne() {

		ResponseEntity<OpenMovieDetails> details = detailsController.findOne("tt0232500");
		System.out.println(details.getBody());

		assertEquals("tt0232500", details.getBody().getImdbID());
		log.info(details.getBody().getTitle());

	}

}
