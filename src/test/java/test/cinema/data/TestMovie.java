package test.cinema.data;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestMovie {

	@Test
	void testCreateData() {

		Movie movie = new Movie("Joker", 2019, 165);
		Movie movie2 = new Movie("Parasite",2019, 132);
		Movie movie3 = new Movie("Interstellar",2014);
		List<Movie> movies = List.of(movie, movie2, movie3);
		Movie oneMovie = movies.get(0);
		//new permet de crééer un objet, ensuite le type de l'ojbet à créer. L'ensemble : on dit qu'on 
		//fait appelle à un constructeur
		System.out.println("movies: "+ movies);
		System.out.println("Title: "+ movie2.getTitle());
		System.out.println("Movie tonight: "+ oneMovie);
		//
		//movie.setYear(1900);
		System.out.println(movie);
		System.out.println(movies.get(0));
		System.out.println(oneMovie);
		//add directors
		Person tp = new Person("Todd Phillips");
		movie.setDirector(tp);
		System.out.println(movie + " réalisé par1 " + movie.getDirector());
		System.out.println(movie + " réalisé par2 " 
					+ movie.getDirector().getName());
		//clint eastwood
		Person clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));
		System.out.println(clint + " à "+ clint.getAge().getAsInt() + " ans");
		Movie movieGT = new Movie("Gran Torino", 2008, 116, clint);
		Movie movieI = new Movie("Impitoyable", 1992, clint);
		System.out.println("Director of " + movieGT + " : " + movieGT.getDirector());
	}

	//	@Test
	//	void test2() {
	//		Movie movie = new Movie("V pour Vendetta", 2006, 132);
	//		System.out.println(movie);


	@Test
	void testEquals() {
		Movie movieJ = new Movie("Joker", 2019, 165);
		Movie movieP = new Movie("Parasite",2019, 132);
		Movie movie = movieJ;
		System.out.println(movieJ == movieP);
		System.out.println(movieJ == movieJ);
		System.out.println(movieJ == movie);
	}

	@Test
	void testEquals2() {
		Movie movieChaosI = new Movie("Chaos", 2005);
		Movie movieChaosII = new Movie("Chaos", 2005);
		System.out.println(movieChaosI == movieChaosII);
		System.out.println(movieChaosI.equals(movieChaosII));
	}
	
	
	
}
	


	

