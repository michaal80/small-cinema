package demo.show;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import demo.Movie;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Movie movie;
	private LocalDateTime localDateTime;
	private BigDecimal price;

	protected Show() {
	}

	public Show(Movie movie, LocalDateTime localDateTime, BigDecimal price) {
		this.movie = movie;
		this.localDateTime = localDateTime;
		this.price = price;
	}

}