package demo.show;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author mike
 *
 *         An internal endpoint in which they (i.e. the cinema owners) can
 *         update show times and prices for their movie catalog.
 * 
 *         An endpoint in which their customers (i.e. moviegoers) can fetch
 *         movie times.
 * 
 */
@RestController
@Slf4j
@AllArgsConstructor
public class ShowController {

	private ShowRepository showRepository;

	@GetMapping("/shows")
	public List<Show> findAll() {
		return (List<Show>) showRepository.findAll();
	}

	@PostMapping("/shows")
	@ResponseStatus(HttpStatus.CREATED)
	public Show add(@RequestBody Show newShow) {
		log.info("adding " + newShow.getMovie().getIMDbId());
		return showRepository.save(newShow);
	}

	@GetMapping("/shows/{id}")
	public Optional<Show> findOne(@PathVariable Long id) {
		return showRepository.findById(id);
	}

	@PutMapping("/shows/{id}")
	public Show update(@RequestBody Show newShow, @PathVariable Long id) {
		Show show = showRepository.findById(id).get();
		show.setLocalDateTime(newShow.getLocalDateTime());
		show.setMovie(newShow.getMovie());
		show.setPrice(newShow.getPrice());
		return showRepository.save(show);

	}

}
