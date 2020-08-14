package demo.rate;

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
public class Rate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Movie movie;
	private Integer rate;

	protected Rate() {
	}

	public Rate(Movie movie, Integer rate) {
		this.movie = movie;
		this.rate = rate;
	}

}