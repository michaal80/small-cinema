package demo.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String iMDbId;

	protected Movie() {
	}

	public Movie(String iMDbId) {

		this.iMDbId = iMDbId;
	}

	@Override
	public String toString() {
		return String.format("Movie[id=%d, iMDbId='%s']", id, iMDbId);
	}

}