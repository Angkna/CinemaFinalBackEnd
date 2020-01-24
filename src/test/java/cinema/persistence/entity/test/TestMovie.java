package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestMovie {

	
	@Autowired
	MovieRepository repoMovie;
	
	@Autowired
	EntityManager entityManager; 
	
	@Test
	void test() {
		Movie movie = new Movie("Joker", 2019);
		repoMovie.save(movie);
		var id = movie.getIdMovie();
		System.out.println("id new movies: " + movie.getIdMovie());
		assertNotNull(id);
	}
	@Test
	void testSelectAll() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						//1
				new Movie("Interstellar",2014, 169),					//2	
				new Movie("Gran Torino", 2008, 116)	
				);
		data.forEach(entityManager::persist);
		// when 
		var dataRead = repoMovie.findAll();
		//then
		dataRead.forEach(System.out::println);
		assertEquals(data.size(), dataRead.size());
		assertTrue(dataRead.stream()
				.map(Movie::getTitle)
				.allMatch(tr -> data.stream()				//tous vrai
									.map(Movie::getTitle)
									.anyMatch(th -> th.equals(tr))		//au moins un vrai
							));
				
	}
	
	@Test
	void testFindById () {
		Movie movie = new Movie("Joker", 2019);
		entityManager.persist(movie);
		var id = movie.getIdMovie();
		//when
		var movieReadOpt = repoMovie.findById(id);
		System.out.println(movieReadOpt);
		assertAll(
				() -> assertTrue(movieReadOpt.isPresent()),
				() -> assertEquals(movie.getTitle(), movieReadOpt.get().getTitle())
				);

	}
	
	@Test
	void testFindByTittle() {
		String title = "Le Roi Lion";
		List<Movie> data = List.of( 
		new Movie("Parasite",2019, 132),					
		new Movie(title,2019),					
		new Movie(title, 1994)
		);
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findByTitleContainingIgnoreCase(title);
			System.out.println(dataRead);
	}
	
	@Test
	void testFindByYear2019() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	
				);
		data.forEach(entityManager::persist);
		//when
		var year = 2019;
		var dataRead = repoMovie.findByYear(year);
			System.out.println(dataRead);
	}
	
	
	@Test
	void testFindByYearSup2010() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite", 2019, 132),						
				new Movie("Interstellar", 2014, 169),					
				new Movie("Gran Torino", 2008, 116)	
			);
		data.forEach(entityManager::persist);
		
		//when
		Integer year = 2010;
		var dataRead = repoMovie.findByYearGreaterThan(year);
		System.out.println(dataRead);
	}
	
	
	@Test
	void testFindByYearInf2010() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),					
				new Movie("Gran Torino", 2008, 116)	
				);
		data.forEach(entityManager::persist);

		//when
		var year = 2010;
		var dataRead = repoMovie.findByYearLessThan(year);
		System.out.println(dataRead);
			
		}
	
	@Test
	void testFindByYearBetween1990And2019() {
		//given
		var year1 = 1990;
		var year2 = 2019;
		List<Movie> data = List.of(
				new Movie("Joker", year2),
				new Movie("Parasite",2019, 132),					
				new Movie("Interstellar",2014, 169),					
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 116),	
				new Movie("Old", year1, 118),
				new Movie("Young", 2020, 118)
				);
		data.forEach(entityManager::persist);
		
		var dataRead = repoMovie.findByYearBetween(year1, year2);
		//then
		System.out.println(dataRead);
		assertAll(
			() -> assertEquals(5, dataRead.size() ),			//assert true permet de valider le test seulement si le bolean réponse est true
			() -> 	assertTrue(dataRead.stream()			// les parenthèse permettent de mettre une chronologie aux 2 tests
						.mapToInt(Movie::getYear)
						.allMatch(y -> (y>= year1) && (y<= year2) )) );
			
	
		}
	//assertAll(
//			() -> assertTrue(movieReadOpt.isPresent()),
//			() -> assertEquals(movie.getTitle(), movieReadOpt.get().getTitle())
//			);
	@Test
	void testFindByTitleLeRoiLionAndYear1994() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie("Le Roi Lion", 1994),
				new Movie("Le Roi Lion", 2019)
				);
		data.forEach(entityManager::persist);
		var year = 1994;
		String title = "Le Roi Lion";
		var dataRead = repoMovie.findByYearAndTitle(year, title);
					//then
		System.out.println(dataRead);
		// TODO assert
		//		assertTrue(dataRead.stream()		
//				.mapToInt(Movie::getYear)
//				.allMatch(y -> (y= year) && (t <= (t=title)) ) );
		
		}
//	

	@Test
	void testFindByTitleLeRoiLionAndYear1994AndDuration() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie ("Le Roi Lion", 1994, 166),
				new Movie ("Le Roi Lion", 2019)
				);
		data.forEach(entityManager::persist);
		var year = 1994;
		String title = "Le Roi Lion";
		var duration = 166;
		var dataRead = repoMovie.findByYearAndTitleAndDuration(year, title, duration);
						
		System.out.println(dataRead);
		}
	
	@Test
	
	void testSaveWithDirector() {
		//Given
		Person person = new Person ("Todd Phillips", LocalDate.of(1970,12,20));
		Movie movie = new Movie("Joker", 2019, 165, person);
		entityManager.persist(person);   //already in cache
		repoMovie.save(movie);
		//var id = movie.getIdMovie();
		System.out.println(movie);
		System.out.println(person);
	}
	
	@Test
	void testfindByActorsNameEndingWith (){
		//given
			var joker = new Movie("Joker", 2019, 165);
			var roi = new Movie ("Le Roi Lion", 1994, 166);
			var parasite = new Movie("Parasite", 2019, 132);
			var mad = new Movie("Mad Max", 1987);
			var arme = new Movie ("L'arme Fatale", 1978);
		List<Movie> movies = List.of(joker, roi, parasite, mad, arme);
		movies.forEach(entityManager::persist);
		var mel = new Person("Mel Gibson");
		var whoopi = new Person("Whoopi Golberg");
		var danny = new Person("Danny Glover")
;		entityManager.persist(mel);
		entityManager.persist(whoopi);
		entityManager.persist(danny);
		roi.getActors().add(whoopi);
		mad.getActors().add(mel);
		Collections.addAll(arme.getActors(), mel, danny);
		entityManager.flush();
		//when
		var moviesWithMel = repoMovie.findByActorsNameEndingWith("Gibson");
		
		//then
		assertAll(
					() -> assertTrue(moviesWithMel.contains(mad)),
					() -> assertTrue(moviesWithMel.contains(arme)),
					() -> assertFalse(moviesWithMel.contains(roi))
				);
	}
	
	
	
	
	
	
}
