package demo.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import demo.movie.Movie;
import demo.movie.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RateControllerTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RateController rateController;

	@Test
	public void testRate() {

		Movie movie = movieRepository.findById(1L);

		rateController.add(5, movie.getId());
		List<Rate> rates = rateController.findAll();

		assertEquals(5, rates.get(0).getRate());

	}

}
