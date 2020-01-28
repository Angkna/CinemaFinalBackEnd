package cinema.persistence.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ActId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Movie movie;
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "id_movie")
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_actor")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
