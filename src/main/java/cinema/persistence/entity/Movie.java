package cinema.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity				//sa target n'est que des classes
@Table(name = "movies")			//nom de la table
public class Movie {

	//@Entity        // impossible car c'est un type derrière 
	//private String title;
	//par defaut tous les attributs persistent suaf avec le transient

	private Integer idMovie;  		//obligatoire en integer car au debut il n'y a pas de ID coté base de donné
	private String title;
	private Integer year; 			 //pour ne pas s'embeter on met en int en objet
	private Integer duration; 		//pour les champs NON obligatoire on passe par le objet de int :Integer
	private List<Genre> genres;
	private Double rating; 
	private String synopsis;
	private Audiance audiance;
	private Person director;
	private List<Person> actors;


	public Movie() {			//constructeur vide
		this(null,null);
	}

	public Movie(String title, Integer year) {
		this(title, year, null);
	}

	public Movie(String title, Integer year, Integer duration) {
		this(null, title, year, duration, null, null);
	}
	
	public Movie(String title, Integer year, Integer duration, Double rating, Person director) {
		this(null, title, year, duration, rating, director);
	}

	public Movie(String title, Integer year, Integer duration, Person director) {
		this(null, title, year, duration, null, director);
	}

	public Movie(Integer idMovie, String title, Integer year, Integer duration, Double rating, Person director) {
		super();
		this.idMovie = idMovie;
		this.title = title;
		this.year = year;
		this.duration = duration;
		this.genres = new ArrayList<Genre>();
		this.rating = rating;
		this.synopsis = "";
		this.audiance = null;
		this.director = director;
		this.actors = new ArrayList<Person>();
	}

	//getters and setters
	@Id										//le definit comme id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movie")
	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}

	@Column(nullable = false, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	@ManyToMany
	@JoinTable(name="genrementation",
			joinColumns = @JoinColumn(name="id_movie"),
			inverseJoinColumns = @JoinColumn(name="id_genre"))
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
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

	//director
	@ManyToOne//(fetch=FetchType.LAZY)			//en manytoOne il est cablé sur Eeager mais pas lazy
	@JoinColumn(name="id_director", nullable=true)
	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	//actors
	@ManyToMany //(fetch = FetchType.EAGER)
	@JoinTable(name="act",
	joinColumns=
	@JoinColumn(name="id_movie"),
	inverseJoinColumns=
	@JoinColumn(name="id_actor")
			)
	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}


	//to string method
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title);
		return builder.append(" (")
				.append(year)
				.append(')')	           // moins lourd que le string claissique pour la memoire
				.append('#')
				.append(idMovie)
				.toString();

	}


}
