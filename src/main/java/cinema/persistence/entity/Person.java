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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity				
@Table(name = "persons")		
public class Person {

	private Integer idPerson;
	private String name;  		
	private LocalDate birthdate;
	private List<Nationality> nationalities;
	private String biography;
	private List<Movie> movies;
	
	@OneToMany(mappedBy = "person")
	private List<Act> acts = new ArrayList<Act>();
	
	
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
		this.biography = "";
		this.movies = new ArrayList<>();
	}

	//getter setters
	@Id										
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
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
	
	@ManyToMany
	@JoinTable(name="born",
	joinColumns=
	@JoinColumn(name="id_person"),
	inverseJoinColumns=
	@JoinColumn(name="id_nationalities"))
	public List<Nationality> getNationalities() {
		return nationalities;	
	}

	public void setNationalities(List<Nationality> nationalities) {
		this.nationalities = nationalities;
	}
	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

//	@ManyToMany(mappedBy = "actors")
	@Transient
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
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
