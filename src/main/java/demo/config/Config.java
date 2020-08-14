package demo.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import demo.Movie;
import demo.MovieRepository;
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
	public CommandLineRunner loadMoviesFromFile(MovieRepository repository) {
		return (args) -> {
			List<String> result = Files.readAllLines(Paths.get("src/main/resources/movies"));
			result.forEach(id -> repository.save(new Movie(id)));

			log.info("Movie ids found with findAll():");
			log.info("-------------------------------");
			for (Movie movie : repository.findAll()) {
				log.info(movie.toString());
			}
			log.info("");

		};
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
