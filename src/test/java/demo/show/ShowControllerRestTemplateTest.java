package demo.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.movie.Movie;
import demo.movie.MovieRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
public class ShowControllerRestTemplateTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private ShowController showController;

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
	public void testAddShow() throws Exception {
		Show show = createShow(4L);
		ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "admin").postForEntity("/shows", show,
				String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		List<Show> list = showController.findAll();
		List<String> strings = list.stream().map(s -> s.getMovie().getIMDbId()).collect(Collectors.toList());
		assertTrue(strings.contains("tt1013752"));

	}

	@Test
	public void testUpdateShow() throws Exception {
		Show updateShow = createShow(1L);
		updateShow.setLocalDateTime(LocalDateTime.of(2020, Month.DECEMBER, 1, 22, 0, 0, 0));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(updateShow), headers);

		ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "admin").exchange("/shows/9",
				HttpMethod.PUT, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		Show show = showController.findOne(9L).get();
		assertEquals(22, show.getLocalDateTime().getHour());
	}

	@Test
	public void testUpdateNonExistingShow() throws Exception {
		Show updateShow = createShow(50L);
		updateShow.setLocalDateTime(LocalDateTime.of(2020, Month.DECEMBER, 1, 22, 0, 0, 0));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(updateShow), headers);

		ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "admin").exchange("/shows/9",
				HttpMethod.PUT, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		Show show = showController.findOne(9L).get();
		assertEquals(22, show.getLocalDateTime().getHour());
	}

	@Test
	public void testFindAll() throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity("/shows", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

}