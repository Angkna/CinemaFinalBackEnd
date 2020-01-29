package cinema.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ActId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer movieId;
	private Integer personId;
	
	public ActId() {
		//super();
	}
	
	public ActId(Integer movieId, Integer actorId) {
		super();
		this.movieId = movieId;
		this.personId = actorId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer actorId) {
		this.personId = actorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActId other = (ActId) obj;
		return Objects.equals(getMovieId(), other.getMovieId()) && Objects.equals(getPersonId(), other.getPersonId());
	}
	
	
	
	
	


}
