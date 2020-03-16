package cinema.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Act {
	
	@EmbeddedId
	private ActId id = new ActId();
	
	@ManyToOne
	@MapsId("movieId")
	@JoinColumn(name = "id_movie")
	private Movie movie;
	
	@ManyToOne
	@MapsId("personId")
	@JoinColumn(name = "id_actor")
	private Person person;
	
	private String role;
	
	public Act() {
		super();
	}

	public Act(ActId id, Movie movie, Person person, String role) {
		super();
		this.id = id;
		this.movie = movie;
		this.person = person;
		this.role = role;
	}
	
	public Act(Movie movie, Person person, String role) {
		this(new ActId(movie.getIdMovie(),person.getIdPerson()), movie, person, role);
	}
	
	public Act(Movie movie, Person person) {
		this(new ActId(movie.getIdMovie(),person.getIdPerson()), movie, person, null);
	}

	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Act [id=" + id + ", movie=" + movie + ", person=" + person + ", role=" + role + "]";
	}
}





//Bradley Cooper			Chris Kyle				American Sniper
//							Rocket (voice) 			Infinity war 
//							Phil					VBT

//Joaquin Phoenix     		Arthur Fleck			Joker
//
//Gene Hackman   			Little Bill Daggett 	Impittoyable
//
//Morgan Freeman 			Ned Logan				Impittoyable

//Brie Larson				Caroline Marvel			Marvel 


//////////////INFO 6 DEGRES DE KEVIN BAKON ///////////////////
//kevin bacon joue dans appolo 13 avec Tom Hanks
//tom joue dans PENTAGON PAPERS avc MERYL STREEP
//meryl joue dans mamma mia avec COLIN FIRTH
//COLIN FIRTH joue dans LE DISCOURS D'UN ROI avec HELENA BONHAM CARTER
//helena joue dans OCEAN'S 8 avec CATE BLANCHETT 
//kate joue dans lE SEIGNEUR DES ANNEAUX  avec VIGGO MORTENSEN
//viggo joue dans CAPTAIN FANTASTIC avec FRANK LANGELLA