package demo.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import demo.Movie;
import demo.MovieRepository;
import demo.show.Show;
import demo.show.ShowRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Config {

	@Bean
	public ApiKey loadApiKey() throws IOException {
		List<String> strings = Files.readAllLines(Paths.get("apikey"));
		return new ApiKey(strings.get(0));
	}

	@Bean
	public CommandLineRunner loadMoviesFromFile(MovieRepository movieRepository, ShowRepository showRepository) {
		return (args) -> {
			List<Movie> movies = new ArrayList<>();
			List<String> result = Files.readAllLines(Paths.get("src/main/resources/movies"));
			result.forEach(id -> {
				movies.add(new Movie(id));
			});
			movieRepository.saveAll(movies);

			showRepository.save(new Show(movies.get(0), LocalDateTime.of(2020, Month.DECEMBER, 1, 20, 0, 0, 0),
					new BigDecimal(10)));
			showRepository.save(new Show(movies.get(1), LocalDateTime.of(2020, Month.DECEMBER, 2, 20, 0, 0, 0),
					new BigDecimal(15)));
			showRepository.save(new Show(movies.get(2), LocalDateTime.of(2020, Month.DECEMBER, 3, 20, 0, 0, 0),
					new BigDecimal(20)));
			log.info("movie list: ");
			movieRepository.findAll().forEach(m -> log.info(m.getIMDbId()));

			log.info("show list: ");
			showRepository.findAll().forEach(m -> log.info(m.getMovie().getIMDbId()));

		};
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
