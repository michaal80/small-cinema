package movie;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import demo.movie.Movie;

public class MovieTest {
	@Test
	public void testMovie() {

		Movie movie = new Movie("tt0232500");
		movie.toString();
		assertTrue(movie.toString().contains("tt0232500"));

	}
}
