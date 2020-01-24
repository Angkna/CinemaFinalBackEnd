package cinema.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity				
	@Table(name = "persons")		
public class Person {

	
	private Integer idPerson;
	private String name;  		
	private LocalDate birthdate;
	private List<String> nationalities;
	
	
	///constructeur
	
	public Person() {
		super();
	}
	
	public Person(String name) {
		this (name, null);
	}

	public Person(String name, LocalDate birthdate) {
		this(null, name, birthdate);
	}

	public Person(Integer idPerson, String name, LocalDate birthdate) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.birthdate = birthdate;
		this.nationalities = new ArrayList<>();
	}

	//getter setters
	@Id										
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persons")
	
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	@Column(nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = true)
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
	
	

	//to string method
	  @Override
	  public String toString() {
		  StringBuilder builder = new StringBuilder(name);
		  return builder.append(" (")
		  		.append(Objects.toString(birthdate, "unknown"))
		  		.append(')')	           // moins lourd que le string claissique pour la memoire
		  		.append(" #")
		  		.append( idPerson)
				.toString();
		  	
	  }
	
	
}
