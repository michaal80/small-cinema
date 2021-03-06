package demo.details;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * 
 * @author mike
 * 
 *         An endpoint in which their customers (i.e. moviegoers) can fetch
 *         details about one of their movies.
 *
 */
@RestController
@AllArgsConstructor
public class DetailsController {

	private OpenMovieDbService openMovieDbService;

	@GetMapping("/details/{id}")
	public ResponseEntity<OpenMovieDetails> findOne(@PathVariable String id) {
		OpenMovieDetails openMovieDetails = openMovieDbService.request(id);

		Map<String, ResponseEntity<OpenMovieDetails>> actions = new HashMap<>();
		actions.put("True", ResponseEntity.ok(openMovieDetails));
		actions.put("False", ResponseEntity.notFound().build());

		return actions.get(openMovieDetails.getResponse());
	}

}
