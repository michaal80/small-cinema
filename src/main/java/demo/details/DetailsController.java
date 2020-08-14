package demo.details;

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
	public OpenMovieDetails findOne(@PathVariable String id) {
		return openMovieDbService.request(id);
	}

}
