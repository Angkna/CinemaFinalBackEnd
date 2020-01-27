package cinema.persistence.entity.test;

/**
 * *this is not a unit test case
 */

import java.time.LocalDate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;




@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestMappingEntities {

	
	
	@Autowired
	PersonRepository repoPersons;
	@Autowired
	MovieRepository repoMovies;
	
	
	@Rollback(false)
	@Test
	void testSaveData() {
		var joaq = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28));  	
		var gege = new Person("Gerard Darmond", LocalDate.of(1948, 02, 29));    	
		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5))	;		
		var gene = new Person("Gene Hackman", LocalDate.of(1930, 1, 30));			
		var morgan = new Person("Morgan Freeman", LocalDate.of(1937, 6, 1));
		
		var persons = List.of(joaq, gege, todd, clint, brad, gene, morgan);
		persons.forEach(repoPersons::save);
		
		var joker = new Movie("Joker", 2019, 165, todd);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);	
		var snip = new Movie("American Sniper", 2014, 133, clint);		
		var bad = new Movie("Very Bad Trip", 2009, 100, todd);	
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var end = new Movie("Avengers: Endgame", 2019, 181);				
		var aven = new Movie("Avengers", 2012, 143);					
		var marvel = new Movie("Captain Marvel", 2019, 123);				
		var ultron = new Movie("Avengers : L'ere d'Ultron", 2015, 141);	
		//var pouvoirs = new Movie("Les pleins pouvoirs", 1997);
				
		var movies = List.of(joker,parasite,interstellar,granTorino, impitoyable, snip,bad, infwar, 
									end,aven,marvel,ultron);
		movies.forEach(repoMovies::save);
		
	}

	@Rollback(false)
	@Test
		void testAddDirectorToInterstellar () {
			var movies = repoMovies.findByTitleContainingIgnoreCase("Interstellar");
			if (movies.size()>0) {
					var interstellar = movies.stream().findFirst().get();
					var chris =  new Person ("Christopher Nolan", LocalDate.of(1970, 7, 30));
					repoPersons.save(chris);
					interstellar.setDirector(chris);
			}
	}
			
	@Rollback(false)
	@Test
	void testFindTheDirectorOfMovie() {
			var movies = repoMovies.findByTitleContainingIgnoreCase("Interstellar");
			if (movies.size()>0) {
					var interstellar = movies.stream().findFirst().get();
					var director = interstellar.getDirector();
					System.out.println(director);
			}
	}
	
	@Rollback(false)		
	@Test
	void testAddBatman() {
			var batman = new Movie("Batman: The Dark Night", 2008, 153, null);
			repoMovies.save(batman);
		}
		
//	
//	@Rollback(false)		
//	@Test
//	void testAddNolanToBatman() {
//		Movie Batman = repoMovies.findByTitle("Batman: The Dark Night");
//		//var persons = repoPersons.findByName("Christopher Nollan");
//		
//		if (movies.size()>0) {
//				//var Batman = movies.stream().findFirst().get();
//				Person  Chris  = repoPersons.findByName("Christopher Nollan")
//							.stream().findFirst().get();
//				//var chris =  new Person ("Christopher Nollan", LocalDate.of(1970, 7, 30));
//				//repoPersons.save(chris);
//				Batman.setDirector((Person)Chris);
//		}
//}
	
	
	@Rollback(false)		
	@Test
	void testAddNolanToBatman2() {
		Movie batman = new Movie("Batman: The Dark Night", 2008, 153, null);
		repoMovies.save(batman);
		repoMovies.flush();
		var nolan = repoPersons.findByName("Christopher Nolan")
				.stream().findFirst().get();
		batman.setDirector(nolan);
		repoMovies.flush();
	}
	
	@Rollback(false)		
	@Test
	void testSelectByDirector() {
		var data1 = repoMovies.findByDirectorNameEndingWith("Eastwood");
		var nolan = repoPersons.findByName("Christopher Nolan")
				.stream().findFirst().get();
		var data2 = repoMovies.findByDirector(nolan);
		System.out.println(data1);
		System.out.println(data2);
		
	}
	
	
	@Test
	void scenario06SelectPersonByYear() {
		var data = repoPersons.findByBirthdateYear(1930);
		System.out.println(data);
	}
	
	
	@Rollback(false)
	@Test
	void scenario07MovieWithActor() {
		var impitoyable = repoMovies.findByTitle("Impitoyable")
						.stream().findFirst().get();
		var clint = repoPersons.findByName("Clint Eastwood")
						.stream().findFirst().get();
		var gene = repoPersons.findByName("Gene Hackman")
						.stream().findFirst().get();
		impitoyable.setActors(List.of(clint, gene));
		repoMovies.flush();
	}
	
	@Test
	void testLazyActors () {
		//read a mvoie : select the movie + its director
		var impitoyable = repoMovies.findByTitle("Impitoyable")
				.stream().findFirst().get();
		// read actors
		var actors = impitoyable.getActors();
		System.out.println(actors);
	}

	@Rollback(false)
	@Test
	void scenario09AddActorsToAMovie() {
		var impitoyable = repoMovies.findByTitle("Impitoyable")
						.stream().findFirst().get();
		var morgan = repoPersons.findByName("Morgan Freeman")
				.stream().findFirst().get();
		impitoyable.getActors().add(morgan);
		repoMovies.flush();
	}
	
	
}

	


