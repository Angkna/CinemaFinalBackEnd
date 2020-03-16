package cinema.dto;

import java.util.List;

import cinema.persistence.entity.Audiance;

public class MovieFull extends MovieLight {
	
	//attributes
	private Integer duration;
	private List<String> genres;
	private Double rating; 
	private String synopsis;
	private Audiance audiance;
	private List<PersonFull> actors;
	
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Audiance getAudiance() {
		return audiance;
	}
	public void setAudiance(Audiance audiance) {
		this.audiance = audiance;
	}
	public List<PersonFull> getActors() {
		return actors;
	}
	public void setActors(List<PersonFull> actors) {
		this.actors = actors;
	}
	
	
}


