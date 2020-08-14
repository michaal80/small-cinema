package demo.details;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import demo.consumer.MovieData;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DetailsControllerTest {

	@Autowired
	private DetailsController detailsController;

	@Test
	public void testFindOne() {

		MovieData movieData = detailsController.findOne("tt0232500");

		assertEquals("tt0232500", movieData.getImdbID());
		log.info(movieData.getTitle());

	}

}
