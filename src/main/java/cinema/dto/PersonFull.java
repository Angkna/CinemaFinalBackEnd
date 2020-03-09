package cinema.dto;

import java.time.LocalDate;
import java.util.List;


public class PersonFull {
	
	private Integer idPerson;
	private String name;
	private LocalDate birthdate;
	private List<String> nationalities;
	private String biography;
	
	public Integer getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public List<String> getNationalities() {
		return nationalities;
	}
	public void setNationalities(List<String> nationalities) {
		this.nationalities = nationalities;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	

}
