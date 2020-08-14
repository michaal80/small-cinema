package demo.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import demo.consumer.MovieData;
import demo.consumer.OpenMovieDbService;

@RestController
public class DetailsController {

	@Autowired
	private OpenMovieDbService openMovieDbService;

	@GetMapping("/details/{id}")
	public MovieData findOne(@PathVariable String id) {
		return openMovieDbService.request(id);
	}

}