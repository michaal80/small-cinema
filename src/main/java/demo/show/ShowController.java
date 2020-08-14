package demo.show;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ShowController {

	@Autowired
	private ShowRepository showRepository;

	@GetMapping("/shows")
	List<Show> findAll() {
		return (List<Show>) showRepository.findAll();
	}

	@PostMapping("/shows")
	@ResponseStatus(HttpStatus.CREATED)
	Show add(@RequestBody Show newShow) {
		log.info("adding " + newShow.getMovie().getIMDbId());
		return showRepository.save(newShow);
	}

	@GetMapping("/shows/{id}")
	Optional<Show> findOne(@PathVariable Long id) {
		return showRepository.findById(id);
	}

	@PutMapping("/shows/{id}")
	Optional<Show> update(@RequestBody Show newShow, @PathVariable Long id) {

		return showRepository.findById(id).map(show -> {
			show.setLocalDateTime(newShow.getLocalDateTime());
			show.setMovie(newShow.getMovie());
			show.setPrice(newShow.getPrice());
			return showRepository.save(show);
		});
	}

}
