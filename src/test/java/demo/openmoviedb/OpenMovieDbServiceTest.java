package demo.openmoviedb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import demo.SmallCinemaApp;
import demo.consumer.MovieData;
import demo.consumer.OpenMovieDbService;

@SpringBootTest(classes = SmallCinemaApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class OpenMovieDbServiceTest {
	@LocalServerPort
	private int port;

	@Autowired
	private OpenMovieDbService openMovieDbService;

	@Test
	public void testOMDb() throws JsonMappingException, JsonProcessingException {
		MovieData movieData = openMovieDbService.request("tt0232500");
		assertEquals(movieData.getTitle(), "The Fast and the Furious");

	}

}