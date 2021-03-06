package demo.details;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.config.ApiKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class OpenMovieDbServiceImp implements OpenMovieDbService {

	private RestTemplate restTemplate;
	private ApiKey apiKey;

	@Override
	public OpenMovieDetails request(String id) {
		String url = buildUrl(id);
		log.info("request to: " + url);
		return restTemplate.getForObject(url, OpenMovieDetails.class);
	}

	private String buildUrl(String id) {
		StringBuilder url = new StringBuilder();
		url.append("http://www.omdbapi.com/?apikey=").append(apiKey.getValue()).append("&i=").append(id);
		return url.toString();
	}

}
