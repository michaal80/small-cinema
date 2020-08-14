package demo.rate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.movie.Movie;
import demo.movie.MovieRepository;

/**
 * 
 * @author mike
 *
 *         An endpoint in which their customers (i.e. moviegoers) can leave a
 *         review rating (from 1-5 stars) about a particular movie
 */
@RestController
public class RateController {

	@Autowired
	private RateRepository rateRepository;
	@Autowired
	private MovieRepository movieRepository;

	@PostMapping("/rates")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Rate rate) {

		Movie movie = movieRepository.findById(rate.getMovie().getId()).get();
//		Rate rate = new Rate(movie, review);
		rateRepository.save(rate);
	}

	@GetMapping("/rates")
	public List<Rate> findAll() {
		return (List<Rate>) rateRepository.findAll();
	}
}
