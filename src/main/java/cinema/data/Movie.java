package cinema.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Movie {
	//attributes
	private String title;
	private int year;
	private int duration;
	private Person director;
	private List<Person> actors;
	
	public Movie(String title, int year, int duration) {
		this(title, year, duration, null);
	}
	
	public Movie(String title, int year, Person director) {
		this(title, year, 0, director);
	}

	public Movie(String title, int year) {
		this(title, year, 0);
	}
	
public Movie(String title, int year, int duration, Person director) {
		super();
		this.title = Objects.requireNonNull(title);
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.actors = new ArrayList<Person>();
	}

// ou bien	
//	public Movie(String title, int year) {
//		super();
//		this.title = title;
//		this.year = year;
//	}


//getters and setters 
	
	public String getTitle() {
		return title;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public void setTitle(String title) {
		this.title = Objects.requireNonNull(title);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void addActor(Person actor) {
		this.actors.add(actor);
		
	}

	
	public void addAllActors(Collection<? extends Person> actors) {
		this.actors.addAll(actors);
		
	}

	public void addAllActors(Person... actors) {
		//for (var a : actors) {
			//this.addActor(a);
			//  ou bien
			this.addAllActors(Arrays.asList(actors));   //fait reférence à la methode précedente
		}

	public Iterator<Person> iteratorActors() {
		return this.actors.iterator();
	}
	
	public Stream<Person> streamActors() {
		return this.actors.stream();
	}
	
	
	@Override
	public String toString() {				//ne pas suivre les associations avec le toString
		// return title + " (" + year + ", "  + duration + ")";
		return title + " (" + year  
				+ (duration==0 ? "" : ", " + duration + "mn")
				+ ")";
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((title == null) ? 0 : title.hashCode());
//		result = prime * result + year;
//		return result;
//  }

	//ou bien 
	@Override
	public int hashCode() {
		return Objects.hash(title, year);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;  
		return this.title.equals(other.title)
			&& this.year == other.year;
		
		
//		if (title == null) {
//			if (other.title != null)
//				return false;
//		} else if (!title.equals(other.title))
//			return false;
//		if (year != other.year)
//			return false;
//		return true;
	}

	
		
		
	}


