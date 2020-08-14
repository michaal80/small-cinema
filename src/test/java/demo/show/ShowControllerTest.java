package demo.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;

import demo.movie.Movie;
import demo.movie.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ShowControllerTest {

	@Autowired
	private ShowController showController;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void testFindAll() {
		Show show = createShow(1L);
		showRepository.save(show);

		List<Show> result = showController.findAll();

		assertEquals(show.getMovie().getIMDbId(), result.get(0).getMovie().getIMDbId());
	}

	private Show createShow(Long id) {
		Movie movie = movieRepository.findById(id).get();
		LocalDateTime date = LocalDateTime.of(2020, Month.DECEMBER, 1, 20, 0, 0, 0);
		return new Show(movie, date, new BigDecimal(10));
	}

	@Test
	public void testAddShow() throws JsonProcessingException {
		Show show = createShow(1L);
		showController.add(show);
		List<Show> result = showController.findAll();
		assertEquals(show.getMovie().getIMDbId(), result.get(0).getMovie().getIMDbId());

	}

	@Test
	public void testFindOne() throws JsonProcessingException {
		Show show = createShow(1L);
		showRepository.save(show);

		Optional<Show> result = showController.findOne(show.getId());

		assertTrue(result.isPresent());

	}

	@Test
	public void testUpdateShow() {
		Show show = showController.findAll().get(0);

		Show newShow = createShow(5L);
		newShow.setLocalDateTime(LocalDateTime.of(2020, Month.DECEMBER, 1, 21, 45, 0, 0));

		showController.update(newShow, show.getId());
		assertEquals(LocalDateTime.of(2020, Month.DECEMBER, 1, 21, 45, 0, 0),
				showController.findAll().get(0).getLocalDateTime());

	}

}
