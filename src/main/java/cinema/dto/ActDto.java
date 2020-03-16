package cinema.dto;

import cinema.persistence.entity.ActId;

public class ActDto {
	
	private ActId id;
	private MovieLight movie;
	private PersonFull person;
	private String role;
	
	public ActId getId() {
		return id;
	}
	public void setId(ActId id) {
		this.id = id;
	}
	public MovieLight getMovie() {
		return movie;
	}
	public void setMovie(MovieLight movie) {
		this.movie = movie;
	}
	public PersonFull getPerson() {
		return person;
	}
	public void setPerson(PersonFull person) {
		this.person = person;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
