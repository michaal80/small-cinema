package demo.details;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface OpenMovieDbService {
	@Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000))
	OpenMovieDetails request(String id);
}
