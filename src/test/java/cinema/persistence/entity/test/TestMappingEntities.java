package cinema.persistence.entity.test;

/**
 * *this is not a unit test case
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistence.entity.Act;
import cinema.persistence.entity.ActId;
import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.ActRepository;
import cinema.persistence.repository.GenreRepository;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;




@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestMappingEntities {

	
	
	@Autowired
	PersonRepository repoPersons;
	@Autowired
	MovieRepository repoMovies;
	@Autowired
	GenreRepository repoGenres;
	
	@Autowired
	ActRepository actRepository;
	
	
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
		var brie = new Person("Brie Larson",LocalDate.of(1989, 10, 1));
		var kevin = new Person("Kevin Bacon", LocalDate.of(1958, 8, 8));
		var tom = new Person("Tom Hanks",LocalDate.of(1956, 7, 9));
		var meryl = new Person ("Meryl Streep",LocalDate.of(1949, 6, 22));
		var colin = new Person("Colin Firth", LocalDate.of(1960, 9, 10));
		var helena = new Person ("Helena Bonham Carter", LocalDate.of(1966, 5, 26));
		var kate = new Person ("Cate Blanchett",LocalDate.of(1969, 5, 14));
		var viggo = new Person ("Viggo Mortensen", LocalDate.of(1968, 7, 20));
		var franck = new Person ("Frank Langella",LocalDate.of(1938, 1, 1));
		
		var persons = List.of(joaq, gege, todd, clint, brad, gene, morgan, brie, kevin, tom, meryl, colin, helena,
									kate, viggo, franck
									);
		persons.forEach(repoPersons::save);
		
		//genre
		var horror = new Genre("horror");		
		var action = new Genre("action");
		var fantasy = new Genre("fantasy");
		var adventure = new Genre("adventure");
		var animation = new Genre("animation");
		var amour = new Genre ("amour");
		var genres = List.of(horror, action, fantasy, adventure, animation, amour);
		genres.forEach(repoGenres::save);
		
		
		//films
		var joker = new Movie("Joker", 2019, 165, 8.6, todd);	
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
		roi.setGenres(genres4);
		anneaux.setGenres(genres2);
		marvel.setGenres(genres2);
		
		
		
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
		joker.setAudiance(Audiance.G);
		parasite.setAudiance(Audiance.PG);
		interstellar.setAudiance(Audiance.NC17);	
		granTorino.setAudiance(Audiance.R);
		infwar.setAudiance(Audiance.PG13);
		end.setAudiance(Audiance.G);	
		aven.setAudiance(Audiance.NC17);		
		ultron.setAudiance(Audiance.PG);
		pouvoirs.setAudiance(Audiance.G);
		ap13.setAudiance(Audiance.G);
		paper.setAudiance(Audiance.NC17);
		mamma.setAudiance(Audiance.PG);
		roi.setAudiance(Audiance.G);
		ocean.setAudiance(Audiance.NC17);
		anneaux.setAudiance(Audiance.PG);
		fantastic.setAudiance(Audiance.G);
		

		//biography
//		brie.setBiography("Brie Larson has built an impressive career as an acclaimed television actress, rising feature film star and emerging recording artist. A native of Sacramento, Brie started studying drama at the early age of 6.");
//		brad.setBiography("Bradley Charles Cooper was born on January 5, 1975 in Philadelphia, Pennsylvania. His mother, Gloria (Campano), is of Italian descent, and worked for a local NBC station. ");
//		gene.setBiography("Eugene Allen Hackman was born in San Bernardino, California, the son of Ann-nnsylvania Dutch (German), English, and Scottish ancestry, partly by way of Canada, where his mother was born.");
		
		
		movies.forEach(repoMovies::save);
		
	}
	
	@Rollback(false)
	@Test
	void DegresKevinBacon () {
		var joaq = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28));  	
		var gege = new Person("Gerard Darmond", LocalDate.of(1948, 02, 29));    	
		var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));		
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));		
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5))	;		
		var gene = new Person("Gene Hackman", LocalDate.of(1930, 1, 30));			
		var morgan = new Person("Morgan Freeman", LocalDate.of(1937, 6, 1));
		var brie = new Person("Brie Larson",LocalDate.of(1989, 10, 1));
		var kevin = new Person("Kevin Bacon", LocalDate.of(1958, 8, 8));
		var tom = new Person("Tom Hanks",LocalDate.of(1956, 7, 9));
		var meryl = new Person ("Meryl Streep",LocalDate.of(1949, 6, 22));
		var colin = new Person("Colin Firth", LocalDate.of(1960, 9, 10));
		var helena = new Person ("Helena Bonham Carter", LocalDate.of(1966, 5, 26));
		var kate = new Person ("Cate Blanchett",LocalDate.of(1969, 5, 14));
		var viggo = new Person ("Viggo Mortensen", LocalDate.of(1968, 7, 20));
		var franck = new Person ("Frank Langella",LocalDate.of(1938, 1, 1));
		
		var persons = List.of(joaq, gege, todd, clint, brad, gene, morgan, brie, kevin, tom, meryl, colin, helena,
									kate, viggo, franck
									);
		persons.forEach(repoPersons::save);

		//films
		var joker = new Movie("Joker", 2019, 165, 8.6, todd);	
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
		movies.forEach(repoMovies::save);
		repoMovies.flush();
		//ajout acteur aux films
		
//		var movie = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
//		var person = repoPersons.findByName("Morgan Freeman").stream().findFirst().get();
//		var act = new Act(movie, person, "Gérard Bouchard");
//		actRepository.save(act);
		
		//apollon13
		var act = new Act(ap13, tom);
		var act2 = new Act(ap13, kevin);
		//entagon papers
		var act3 = new Act(paper, tom);
		var act4 = new Act(paper, meryl);
		//mamma mia
		var act5 = new Act(mamma, meryl);
		var act6 = new Act(mamma, colin);
		// discours roi
		var act7 = new Act(roi, colin);
		var act8 = new Act(roi, helena);
		
		//ocean8
		var act9 = new Act(ocean, kate);
		var act10 = new Act(ocean, helena);
		//SDA
		var act11 = new Act(anneaux, kate);
		var act12 = new Act(anneaux, viggo);
		//captainfantastic
		var act13 = new Act(fantastic, franck);
		var act14 = new Act(fantastic, viggo);
	
		
		var acttotal =  List.of(act,act2,act3,act4,act5,act6,act7,act8,act9,act9,act10,act11,act12,act13,act14);
	
		acttotal.forEach(actRepository::save);
		repoMovies.flush();
		//test
	//START//
//		var filmActeurDegresInconnu = repoMovies.findByActorsNameEndingWith("Hanks");
//		var filmDeKevin = repoMovies.findByActorsNameEndingWith("Bacon");
//	
//		var idDeKevin = new ArrayList<>();
//		 filmDeKevin.stream()
//				.mapToInt(Movie::getIdMovie)
//				.forEach(i -> idDeKevin.add(i));
//				//.collect(Collectors.toList() );
//				//.forEach(new ArrayList<E>());
//		 System.out.println("id des films de kevin" + idDeKevin);
//		
//		 	var idDeInconnu = new ArrayList<>();
//		 	filmActeurDegresInconnu.stream()
//					.mapToInt(Movie::getIdMovie)
//					.forEach(i -> idDeInconnu.add(i));
//		
//		System.out.println("id des films de inconnus " +  idDeInconnu);	 	
			 	
			 	
//		//Comparer et stocker les résultats dans Liste de comparaison
//	     ArrayList<Object> listeComparaison = new ArrayList<Object>();
//	     for(Object o:idDeInconnu ){
//	    	 if(idDeKevin.contains(o)) {
//	    		 listeComparaison.add("");
//	    	 		System.out.println("1 degrès");
////	    		 	if (listecomparaison.i
////	    		 	break;	
//	     } else {
//	    		 System.out.println("pas de  degrès");
//
//	     }
//	     }
//	     	System.out.println("liste de Comparaison = " + listeComparaison);	
	//END//	
	
//		////////////dire que si c'est nul alors on a un degres 
//	    //for(Object n:listeComparaison ){
//	    	 	if(listeComparaison.isEmpty()
//	    	 		 System.out.println("1 degrès"));
//	     }
	    	 
	    	 
	    	 
//	    	 if (listisEmpty()  
//	    	 System.out.println("1 degrès"));
//	     } else {
//	    	 System.out.println("fuck pas de degrès");
//	     }
		
		
		
			
		
	}
	
	
		
	@Rollback(false)
	@Test
	void scenarioMovieWithActors22() {
			//apollon13
			var ap13 = repoMovies.findByTitle("Apollon 13")
								.stream().findFirst().get();
			var kevin = repoPersons.findByName("Kevin Bacon")
							.stream().findFirst().get();
			var tom = repoPersons.findByName("Tom Hanks")
							.stream().findFirst().get();
			ap13.setActors(List.of(tom, kevin));
			repoMovies.save(ap13);
			repoMovies.flush();
			//pentagon papers
			var paper = repoMovies.findByTitle("Pentagon Papers")
					.stream().findFirst().get();
			var meryl = repoPersons.findByName("Meryl Streep")
					.stream().findFirst().get();
			paper.setActors(List.of(tom, meryl));
			repoMovies.flush();
			//mamma mia
			var mamma = repoMovies.findByTitle("Mamma Mia!")
					.stream().findFirst().get();
			var colin = repoPersons.findByName("Colin Firth")
					.stream().findFirst().get();
			
			mamma.setActors(List.of(meryl, colin));
			repoMovies.flush();
			// discours roi
			var roi = repoMovies.findByTitle("Le discours d un roi")
					.stream().findFirst().get();
			var helena = repoPersons.findByName("Helena Bonham Carter")
					.stream().findFirst().get();
			roi.setActors(List.of(colin, helena));
			repoMovies.flush();
			//ocean8
			var ocean = repoMovies.findByTitle("Ocean s 8")
					.stream().findFirst().get();
			var kate = repoPersons.findByName("Cate Blanchett")
					.stream().findFirst().get();
			ocean.setActors(List.of(kate, helena));
			repoMovies.flush();
			//SDA
			var anneaux = repoMovies.findByTitle("Le seigneur des anneaux")
					.stream().findFirst().get();
			var viggo = repoPersons.findByName("Viggo Mortensen")
					.stream().findFirst().get();
			anneaux.setActors(List.of(kate, viggo));
			repoMovies.flush();
			//captainfantastic
			var fantastic = repoMovies.findByTitle("Captain Fantastic")
					.stream().findFirst().get();
			var frank = repoPersons.findByName("Frank Langella")
					.stream().findFirst().get();
			fantastic.setActors(List.of(frank, viggo));
			repoMovies.flush();
			
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
//		var nolan = repoPersons.findByName("Christopher Nolan")
//				.stream().findFirst().get();
//		var data2 = repoMovies.findByDirector(nolan);
		System.out.println(data1);
		//System.out.println(data2);
		
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
	
	@Rollback(false)
	@Test
	void testAddAct() {
		var movie = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		var person = repoPersons.findByName("Morgan Freeman").stream().findFirst().get();
		var act = new Act(movie, person, "Gérard Bouchard");
		actRepository.save(act);
		System.out.println(act);
	}
}

	


