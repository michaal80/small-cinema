package demo.details;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import demo.SmallCinemaApp;

@SpringBootTest(classes = SmallCinemaApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class OpenMovieDbServiceTest {
	@LocalServerPort
	private int port;

	@Autowired
	private OpenMovieDbService openMovieDbService;

	@Test
	public void testOMDb() throws JsonMappingException, JsonProcessingException {
		OpenMovieDetails openMovieDetails = openMovieDbService.request("tt0232500");
		assertEquals("The Fast and the Furious", openMovieDetails.getTitle());

	}

	@Test
	public void testOMDb_NotFound() throws JsonMappingException, JsonProcessingException {
		OpenMovieDetails openMovieDetails = openMovieDbService.request("aaaaa");
		assertEquals("False", openMovieDetails.getResponse());

	}

}