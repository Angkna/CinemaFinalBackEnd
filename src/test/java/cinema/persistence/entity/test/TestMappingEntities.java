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

import cinema.persistence.entity.Audiance;
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
		var brie = new Person("Brie Larson");
		var kevin = new Person("Kevin Bacon");
		var tom = new Person("Tom Hanks");
		var meryl = new Person ("Meryl Streep");
		var colin = new Person("Colin Firth");
		var helena = new Person ("Helena Bonham Carter");
		var kate = new Person ("Cate Blanchett");
		var viggo = new Person ("Viggo Mortensen");
		var franck = new Person ("Frank Langella");
		var nolan = new Person ("Christopher Nolan", LocalDate.of(1970, 7, 30));
		
		var persons = List.of(joaq, gege, todd, clint, brad, gene, morgan, brie, kevin, tom, meryl, colin, helena,
									kate, viggo, franck, nolan
									);
		persons.forEach(repoPersons::save);
		
		//genre
		var horror = "horror";		
		var action = "action";
		var fantasy = "fantasy";
		var adventure = "adventure";
		var animation = "animation";
		var amour = "amour";
//		var genres = List.of(horror, action, fantasy, adventure, animation, amour);
//		genres.forEach(repoGenres::save);
		
		
		//films
		var joker = new Movie("Joker", 2019, 165, 9.3, todd);	
		var parasite = new Movie("Parasite",2019, 132);
		var interstellar = new Movie("Interstellar",2014, 169);		
		var granTorino= new Movie("Gran Torino", 2008, 116, clint);	
		var impitoyable = new Movie("Impitoyable", 1992, 130, clint);	
		var snip = new Movie("American Sniper", 2014, 133, 8.1, clint);		
		var bad = new Movie("Very Bad Trip", 2009, 100, 5.1, todd);	
		var infwar = new Movie("Avengers: Infinity War", 2018, 149);
		var end = new Movie("Avengers: Endgame", 2019, 181);				
		var aven = new Movie("Avengers", 2012, 143);					
		var marvel = new Movie("Captain Marvel", 2019, 123);				
		var ultron = new Movie("Avengers : L'ere d'Ultron", 2015, 141);	
		var pouvoirs = new Movie("Les pleins pouvoirs", 1997);
		var ap13 = new Movie("Apollon 13",1995);
		var paper = new Movie("Pentagon Papers", 2018);
		var mamma = new Movie("Mamma Mia!" ,2008);
		var roi = new Movie ("Le discours d un roi",2011);
		var ocean = new Movie("Ocean s 8", 2018);
		var anneaux = new Movie ("Le Seigneur des anneaux", 2002);
		var fantastic = new Movie ("Captain Fantastic", 2016);
		
		var movies = List.of(joker,parasite,interstellar,granTorino, impitoyable, snip,bad, infwar, 
							end,aven,marvel,ultron, pouvoirs, ap13, paper, mamma, roi, ocean, anneaux,
							fantastic
							);
		//genres
		var genres1 = List.of(horror);
		var genres2 = List.of(action, adventure);
		var genres3 = List.of(animation, adventure, fantasy);
		var genres4 = List.of(action);
		var genres5 = List.of(adventure);
		var genres6 = List.of(amour);
		
		joker.setGenres(genres1);
		interstellar.setGenres(genres2);
		infwar.setGenres(genres3);
		aven.setGenres(genres4);
		granTorino.setGenres(genres5);
		mamma.setGenres(genres6);
		
		//synopsis
		infwar.setSynopsis("Des mechants veulent voler un gant magique pour dominer l univers mouhahahahha");
		parasite.setSynopsis("des humains tels des parasites veulent voler la maison de gens riches");
		end.setSynopsis("c est la fin de la guerre pour le gant magique et ca finit pas vraiment bien pour certains");
		anneaux.setSynopsis("un anneaux pour les controller tous et dans les tenebres les lier !!");
		mamma.setSynopsis("film de fille #romantisme #chanson, #amour #3papasWTF");
		ap13.setSynopsis("direction les tenebres de la lune");
		snip.setSynopsis("l hisoire vrai d un sniper americain dans les tenebres de la guerre de l irak ");
		
		//audiance
		snip.setAudiance(Audiance.NC17);
		marvel.setAudiance(Audiance.PG);
		bad.setAudiance(Audiance.PG13);
		
		
		
		movies.forEach(repoMovies::save);
		
	}

	@Rollback(false)
	@Test
		void testAddDirectorToInterstellar () {
			var movies = repoMovies.findByTitleContainingIgnoreCase("Interstellar");
			if (movies.size()>0) {
					var interstellar = movies.stream().findFirst().get();
					var chris =  repoPersons.findByName("Christopher Nolan").stream().findFirst().get();
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

	


