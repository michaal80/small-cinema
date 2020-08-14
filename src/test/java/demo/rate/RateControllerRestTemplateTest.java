package demo.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.movie.Movie;
import demo.movie.MovieRepository;
import demo.show.Show;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateControllerRestTemplateTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private RateController rateController;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	private Show createShow(Long id) {
		Movie movie = movieRepository.findById(id).get();
		LocalDateTime date = LocalDateTime.of(2020, Month.DECEMBER, 1, 20, 0, 0, 0);
		return new Show(movie, date, new BigDecimal(10));
	}

	@Test
	public void testRate() throws Exception {
		Show show = createShow(1L);
		ResponseEntity<String> response = restTemplate.postForEntity("/rates", show, String.class);
		System.out.println(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		List<Rate> list = rateController.findAll();
		assertNotEquals(0, list.size());

	}

}