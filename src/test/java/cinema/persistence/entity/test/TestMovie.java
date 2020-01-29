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

import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestMovie {

	
	@Autowired
	MovieRepository repoMovie;
	@Autowired
	PersonRepository repoPerson;

	@Autowired
	EntityManager entityManager; 

	@Test
	void testNewMovie() {
		Movie movie = new Movie("Joker", 2019);
		repoMovie.save(movie);
		var id = movie.getIdMovie();
		System.out.println("id new movies: " + movie.getIdMovie());
		assertNotNull(id);
	}
	@Test
	void testFindAll() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						//1
				new Movie("Interstellar",2014, 169),					//2	
				new Movie("Gran Torino", 2008, 116));
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
	void testFindByTitle() {
		String title = "Le Roi Lion";
		List<Movie> data = List.of( 
				new Movie("Parasite",2019, 132),					
				new Movie(title,2019),					
				new Movie(title, 1994));
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findByTitle(title);
		assertTrue(dataRead.stream().allMatch(m -> title.equals(m.getTitle())));
	}

	@Test
	void testFindByTitleContainingIgnoreCase() {
		String title = "Le Roi Lion";
		List<Movie> data = List.of( 
				new Movie("Parasite",2019, 132),					
				new Movie(title,2019),					
				new Movie(title, 1994));
		data.forEach(entityManager::persist);
		String partTitle = "lion";
		var dataRead = repoMovie.findByTitleContainingIgnoreCase(partTitle);
		assertTrue(dataRead.stream().allMatch(m -> title.equals(m.getTitle())));	
	}

	@Test
	void testFindByTitleAndYear() {
		String title = "Le Roi Lion";
		List<Movie> data = List.of( 
				new Movie("Parasite",2019, 132),					
				new Movie(title, 2019),					
				new Movie(title, 1994));
		data.forEach(entityManager::persist);
		var dataRead = repoMovie.findByTitleAndYear("Le Roi Lion", 2019);
		assertTrue(dataRead.stream().allMatch(m -> (title.equals(m.getTitle())) && (m.getYear() == 2019)));	
	}

	@Test
	void testFindByYear() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116));
		data.forEach(entityManager::persist);
		//when
		var year = 2019;
		var dataRead = repoMovie.findByYear(year);
		assertTrue(dataRead.stream().allMatch(m -> m.getYear() == year));
	}

	@Test
	void testFindByYearLessThan() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),					
				new Movie("Gran Torino", 2008, 116));
		data.forEach(entityManager::persist);
		//when
		var year = 2010;
		var dataRead = repoMovie.findByYearLessThan(year);
		assertTrue(dataRead.stream().allMatch(m -> m.getYear() < year));

	}

	@Test
	void testFindByYearGreaterThan() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite", 2019, 132),						
				new Movie("Interstellar", 2014, 169),					
				new Movie("Gran Torino", 2008, 116));
		data.forEach(entityManager::persist);

		//when
		Integer year = 2010;
		var dataRead = repoMovie.findByYearGreaterThan(year);
		assertTrue(dataRead.stream().allMatch(m -> m.getYear() > year));
	}

	@Test
	void testFindByYearBetween() {
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
				new Movie("Young", 2020, 118));
		data.forEach(entityManager::persist);

		var dataRead = repoMovie.findByYearBetween(year1, year2);
		//then
		System.out.println(dataRead);
		assertAll(
				() -> assertEquals(5, dataRead.size() ),			//assert true permet de valider le test seulement si le bolean réponse est true
				() -> assertTrue(dataRead.stream()			// les parenthèse permettent de mettre une chronologie aux 2 tests
						.mapToInt(Movie::getYear)
						.allMatch(y -> (y >= year1) && (y <= year2) )));
	}
	//assertAll(
	//			() -> assertTrue(movieReadOpt.isPresent()),
	//			() -> assertEquals(movie.getTitle(), movieReadOpt.get().getTitle())
	//			);

	@Test
	void testFindByYearAndTitle() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie("Le Roi Lion", 1994),
				new Movie("Le Roi Lion", 2019));
		data.forEach(entityManager::persist);
		var year = 1994;
		String title = "Le Roi Lion";
		var dataRead = repoMovie.findByYearAndTitle(year, title);
		//then
		assertTrue(dataRead.stream()		
				.allMatch(m-> (m.getYear() == year) && (m.getTitle() == title)));
	}

	@Test
	void testFindByTitleAndYearAndDuration() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie ("Le Roi Lion", 1994, 166),
				new Movie ("Le Roi Lion", 2019));
		data.forEach(entityManager::persist);
		var year = 1994;
		String title = "Le Roi Lion";
		var duration = 166;
		var dataRead = repoMovie.findByYearAndTitleAndDuration(year, title, duration);
		assertTrue(dataRead.stream()		
				.allMatch(m-> (m.getYear() == year) && (m.getTitle() == title) && (m.getDuration() == duration)));
	}

	@Test
	void testFindByDurationGreaterThan() {
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie ("Le Roi Lion", 1994, 166),
				new Movie ("Le Roi Lion", 2019));
		data.forEach(entityManager::persist);
		var duration = 130;
		var dataRead = repoMovie.findByDurationGreaterThan(duration);
		assertTrue(dataRead.stream().allMatch(m -> m.getDuration() > duration));		
	}

	@Test
	void testFindByDurationBetween() {
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie ("Le Roi Lion", 1994, 166),
				new Movie ("Le Roi Lion", 2019));
		data.forEach(entityManager::persist);
		var durationMin = 120;
		var durationMax = 150;
		var dataRead = repoMovie.findByDurationBetween(durationMin, durationMax);
		assertTrue(dataRead.stream().allMatch(m -> (m.getDuration() > durationMin) && (m.getDuration() < durationMax)));
	}

	@Test
	void testFindByDurationLessThanEqual() {
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite",2019, 132),						
				new Movie("Interstellar",2014, 169),						
				new Movie("Gran Torino", 2008, 116)	,
				new Movie("Very old", 1960, 138),
				new Movie ("Le Roi Lion", 1994, 166),
				new Movie ("Le Roi Lion", 2019));
		data.forEach(entityManager::persist);
		var duration = 140;
		var dataRead = repoMovie.findByDurationLessThanEqual(duration);
		assertTrue(dataRead.stream().allMatch(m -> m.getDuration() <= duration));
	}

	@Test
	void testFindByGenres() {
		var horror = new Genre("horror");		
		var action = new Genre("action");
		var fantasy = new Genre("fantasy");
		var adventure = new Genre("adventure");
		var animation = new Genre("animation");
		var genres = List.of(horror, action, fantasy, adventure, animation);
		genres.forEach(entityManager::persist);

		var joker = new Movie("Joker", 2019, 165);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116);	
		var impitoyable = new Movie("Impitoyable", 1992, 130);			
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var end = new Movie("Avengers: Endgame", 2019, 181);				
		var genres1 = List.of(horror);
		var genres2 = List.of(action, adventure);
		var genres3 = List.of(animation, adventure, fantasy);
		joker.setGenres(genres1);
		interstellar.setGenres(genres2);
		infwar.setGenres(genres3);
		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, infwar, end);
		movies.forEach(entityManager::persist);

		String genreRead = "adventure";
		var dataRead = repoMovie.findByGenresGenreIgnoreCase(genreRead);
		System.out.println(dataRead);
		assertTrue(dataRead.stream()
				.allMatch(m -> m.getGenres().stream()	
						.map(Genre::getGenre)
						.anyMatch(n -> n.equals(genreRead))	
						));
	}

	@Test
	void testFindByRatingGreaterThanEqual() {
		List<Movie> data = List.of(
				new Movie("Joker", 2019, 155, 9.9, null),
				new Movie("Parasite",2019, 132, 8.2, null),						
				new Movie("Interstellar",2014, 169, 3.7, null),						
				new Movie("Gran Torino", 2008, 116, 4.9, null),
				new Movie("Very old", 1960, 138, 1.3, null),
				new Movie ("Le Roi Lion", 1994, 166, 7.1, null),
				new Movie ("Le Roi Lion", 2019, 178, 8.5, null));
		data.forEach(entityManager::persist);
		var rating = 6.0;
		var dataRead = repoMovie.findByRatingGreaterThanEqual(rating);
		assertTrue(dataRead.stream().allMatch(m -> (m.getRating() > rating)));	
	}

	@Test
	void testFindBySynopsisContaining() {
		Movie m1 = new Movie("Joker", 2019);
		Movie m2 = new Movie("Parasite",2019, 132);				
		Movie m3 = new Movie("Interstellar",2014, 169);					
		Movie m4 = new Movie("Gran Torino", 2008, 116);
		List<Movie> data = List.of(m1, m2, m3, m4);
		String synoM2 = "L'histoire passionate des vers de terre irlandais en australie";
		String synoM3 = "Une découverte des planets dans leur intimité la plus secrete";
		m2.setSynopsis(synoM2);
		m3.setSynopsis(synoM3);
		data.forEach(entityManager::persist);

		String extrait = "vers";
		var dataRead = repoMovie.findBySynopsisContaining(extrait);
		assertTrue(dataRead.stream().allMatch(m -> m.getSynopsis().contains(extrait)));
	}

	@Test
	void testFindByAudiance() {
		Movie m1 = new Movie("Joker", 2019);
		Movie m2 = new Movie("Parasite",2019, 132);				
		Movie m3 = new Movie("Interstellar",2014, 169);					
		Movie m4 = new Movie("Gran Torino", 2008, 116);
		Movie m5 = new Movie("Very old", 1960, 138);
		List<Movie> data = List.of(m1, m2, m3, m4, m5);
		m1.setAudiance(Audiance.PG13);
		m2.setAudiance(Audiance.NC17);
		m3.setAudiance(Audiance.G);
		m4.setAudiance(Audiance.PG13);
		data.forEach(entityManager::persist);

		var dataRead = repoMovie.findByAudiance(Audiance.PG13);
		assertTrue(dataRead.stream().allMatch(m -> m.getAudiance() == Audiance.PG13));
	}

	@Test
	void testSaveMovieWithDirector() {
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
	void testFindByDirector() {   	
		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));		
		var persons = List.of(todd, clint, brad);
		persons.forEach(entityManager::persist);

		var joker = new Movie("Joker", 2019, 165, todd);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);	
		var snip = new Movie("American Sniper", 2014, 133, clint);		
		var bad = new Movie("Very Bad Trip", 2009, 100, todd);	
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, snip,bad, infwar);
		movies.forEach(entityManager::persist);

		var dataRead = repoMovie.findByDirector(clint);
		assertTrue(dataRead.stream().allMatch(m -> clint.equals(m.getDirector())));
	}
	
	@Test
	void testFindByDirectorName() {
		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));		
		var persons = List.of(todd, clint, brad);
		persons.forEach(entityManager::persist);

		var joker = new Movie("Joker", 2019, 165, todd);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);	
		var snip = new Movie("American Sniper", 2014, 133, clint);		
		var bad = new Movie("Very Bad Trip", 2009, 100, todd);	
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, snip,bad, infwar);
		movies.forEach(entityManager::persist);

		String Name = "Todd Phillips";
		var dataRead = repoMovie.findByDirectorName(Name);
		assertTrue(dataRead.stream().allMatch(m -> todd.equals(m.getDirector())));
	}

	@Test
	void testFindByDirectorNameEndingWith() {
		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));		
		var persons = List.of(todd, clint, brad);
		persons.forEach(entityManager::persist);

		var joker = new Movie("Joker", 2019, 165, todd);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);	
		var snip = new Movie("American Sniper", 2014, 133, clint);		
		var bad = new Movie("Very Bad Trip", 2009, 100, todd);	
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, snip,bad, infwar);
		movies.forEach(entityManager::persist);

		String partName = "lips";
		var dataRead = repoMovie.findByDirectorNameEndingWith(partName);
		assertTrue(dataRead.stream().allMatch(m -> todd.equals(m.getDirector())));
	}

	@Test
	void testFindByDirectorIdPerson() {
		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));		
		var persons = List.of(todd, clint, brad);
		persons.forEach(entityManager::persist);

		var joker = new Movie("Joker", 2019, 165, todd);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);	
		var snip = new Movie("American Sniper", 2014, 133, clint);		
		var bad = new Movie("Very Bad Trip", 2009, 100, todd);	
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, snip,bad, infwar);
		movies.forEach(entityManager::persist);
		var idDirector = clint.getIdPerson();
		//when
		var dataRead = repoMovie.findByDirectorIdPerson(idDirector);
		assertTrue(dataRead.stream().allMatch(m -> clint.equals(m.getDirector())));
	}

//	@Test
//	void testFindByActorsName() {   	
//		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
//		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
//		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));		
//		var gene = new Person("Gene Hackman", LocalDate.of(1930, 1, 30));			
//		var morgan = new Person("Morgan Freeman", LocalDate.of(1937, 6, 1));
//		var persons = List.of(todd, clint, brad, gene, morgan);
//		persons.forEach(entityManager::persist);
//
//		var joker = new Movie("Joker", 2019, 165, todd);	
//		var parasite = new Movie("Parasite",2019, 132);
//		var interstellar = new Movie("Interstellar",2014, 169);		
//		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
//		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);			
//		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
//		var end = new Movie("Avengers: Endgame", 2019, 181);				
//		var actors1 = List.of(morgan);
//		var actors2 = List.of(gene, clint);
//		var actors3 = List.of(gene, morgan, brad);
//		joker.setActors(actors1);
//		interstellar.setActors(actors2);
//		infwar.setActors(actors3);
//		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, infwar, end);
//		movies.forEach(entityManager::persist);
//
//		String actorName = "Gene Hackman";
//		var dataRead = repoMovie.findByActorsName(actorName);
//		assertTrue(dataRead.stream()
//				.allMatch(m -> m.getActors().stream()	
//						.map(Person::getName)
//						.anyMatch(n -> n.equals(actorName))	
//						));
//	}
//
//	@Test
//	void testFindByActorsIdPerson() {
//		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
//		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
//		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));		
//		var gene = new Person("Gene Hackman", LocalDate.of(1930, 1, 30));			
//		var morgan = new Person("Morgan Freeman", LocalDate.of(1937, 6, 1));
//		var persons = List.of(todd, clint, brad, gene, morgan);
//		persons.forEach(entityManager::persist);
//
//		var joker = new Movie("Joker", 2019, 165, todd);	
//		var parasite = new Movie("Parasite",2019, 132);
//		var interstellar = new Movie("Interstellar",2014, 169);		
//		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
//		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);			
//		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
//		var end = new Movie("Avengers: Endgame", 2019, 181);				
//		var actors1 = List.of(morgan);
//		var actors2 = List.of(gene, clint);
//		var actors3 = List.of(gene, morgan, brad);
//		joker.setActors(actors1);
//		interstellar.setActors(actors2);
//		infwar.setActors(actors3);
//		var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, infwar, end);
//		movies.forEach(entityManager::persist);
//
//		var idActor = clint.getIdPerson();
//		var dataRead = repoMovie.findByActorsIdPerson(idActor);
//		assertTrue(dataRead.stream()
//				.allMatch(m -> m.getActors().stream()	
//						.map(Person::getIdPerson)
//						.anyMatch(n -> n.equals(idActor))	
//						));
//	}
//
//	@Test
//	void testfindByActorsNameEndingWith (){
//		//given
//		var joker = new Movie("Joker", 2019, 165);
//		var roi = new Movie ("Le Roi Lion", 1994, 166);
//		var parasite = new Movie("Parasite", 2019, 132);
//		var mad = new Movie("Mad Max", 1987);
//		var arme = new Movie ("L'arme Fatale", 1978);
//		List<Movie> movies = List.of(joker, roi, parasite, mad, arme);
//		movies.forEach(entityManager::persist);
//
//		var mel = new Person("Mel Gibson");
//		var whoopi = new Person("Whoopi Golberg");
//		var danny = new Person("Danny Glover")
//				;		entityManager.persist(mel);
//				entityManager.persist(whoopi);
//				entityManager.persist(danny);
//
//				roi.getActors().add(whoopi);
//				mad.getActors().add(mel);
//				Collections.addAll(arme.getActors(), mel, danny);
//				entityManager.flush();
//				//when
//				var moviesWithMel = repoMovie.findByActorsNameEndingWith("Gibson");
//
//				//then
//				assertAll(
//						() -> assertTrue(moviesWithMel.contains(mad)),
//						() -> assertTrue(moviesWithMel.contains(arme)),
//						() -> assertFalse(moviesWithMel.contains(roi))
//						);
//	}
//	
	@Test
	void testAct() {
		//Given
		Person person = new Person ("Todd Phillips", LocalDate.of(1970,12,20));
		entityManager.persist(person);   //already in cache
		Movie movie = new Movie("Joker", 2019, 165);
		movie.getActors().add(person);
		entityManager.persist(movie);
		entityManager.flush();

		String role = "JeanJoker";
		
	}

}
