package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestCinema {

	private List<Movie> movies;
	private List<Person> persons;
	
	
	@BeforeEach
	void initData () {
//		persons = List.of(				//list of ne peut plus etre modifi� post cr�ation ==immutable
//				new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),
//				new Person("Gerard Darmond", LocalDate.of(1948, 02, 29)),
//				new Person("Todd Phillips"),
//				new Person("Clint Eastwood", LocalDate.of(1930, 5, 31))
//				);
		
		// persons = new ArrayList<Person>();   ou bien :
		persons = new ArrayList<>();
		Collections.addAll(persons,
				new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),   	//0
				new Person("Gerard Darmond", LocalDate.of(1948, 02, 29)),     	//1
				new Person("Todd Phillips", LocalDate.of(1970, 12, 20)), 		//2
				new Person("Clint Eastwood", LocalDate.of(1930, 5, 31)),		//3
				new Person("Bradley Cooper", LocalDate.of(1975, 1, 5))	,		//4
				new Person("Gene Hackman", LocalDate.of(1930, 1, 30)),			//5
				new Person("Morgan Freeman", LocalDate.of(1937, 6, 1))		//6
				
				);	

		
		var clint = persons.get(3);
		var todd = persons.get(2);
		movies = new ArrayList<>();
		Collections.addAll(movies, 
				new Movie("Joker", 2019, 165, todd),					//0
				new Movie("Parasite",2019, 132),						//1
				new Movie("Interstellar",2014, 169),					//2	
				new Movie("Gran Torino", 2008, 116, clint),				//3
				new Movie("Impitoyable", 1992, 130, clint),				//4
				new Movie("American Sniper", 2014, 133, clint),			//5
				new Movie("Very Bad Trip", 2009, 100, todd),			//6
				new Movie("Avengers: Infinity War", 2018, 149),		//7
				new Movie("Avengers: Endgame", 2019, 181),				//8	
				new Movie("Avengers", 2012, 143),						//9
				new Movie("Captain Marvel", 2019, 123),				//10
				new Movie("Avengers : L'ère d'Ultron", 2015, 141)		//11
				);
		movies.get(0).addActor(persons.get(0));
		movies.get(4).addAllActors(persons.get(3),persons.get(5),persons.get(6));
//		movies.get(0).addActor(persons.get(0));
//		movies.get(4).addAllActors(persons.get(3),persons.get(5),persons.get(6));
		
		var actorsParasite = List.of(
				new Person("Kang-ho Song"),
				new Person("Yeo-jeong JoKa"),
				new Person("Woo-sik Choi"),
				new Person("Jeong-eun Lee"));
		persons.addAll(actorsParasite);
		movies.get(1).addAllActors(actorsParasite);
	
	}
	
	@Test
	void testModifiableList() {
		System.out.println(persons);
		System.out.println(persons.getClass());
		persons.add(new Person("Bradley Cooper", LocalDate.of(1975, 1, 5)));
		System.out.println(persons);
		System.out.println(movies);
	}
	
		@Test
		void displayMoviesForI() {
			for (int i=0; i< movies.size() ; i++) { //avec le inf strict plus besoin de faire le -1
				var movie = movies.get(i);
				System.out.println(" - " + movie + " directed by " + movie.getDirector() );
			}
		}
	
		@Test  				//encore mieux  � 2 clauses seulement, utilise n+1 � chaque tour
		void displayMoviesForEach() {				//on met en place un parcours sur une donn�e "it�rable"
			for (var movie: movies) { 
				System.out.println(" - " + movie + " directed by " + movie.getDirector() );
			}
		}
		
		@Test 			
		void totalDurationOfMoviesDirectedByClint() {
			int totalDuration = 0;
			var clint = persons.get(3);
			for (var movie: movies) {
				if (clint.equals(movie.getDirector())) {
					System.out.println(movie);
					totalDuration = totalDuration + movie.getDuration();
				}
			}
			System.out.println("Total duration of movies directed bye Clint is " 
			+ totalDuration + " min");
		}
		
		@Test
		void testSortedMovies1 () {
			SortedSet<Movie> sortedMovies = new TreeSet<>(
					Comparator.comparing(Movie::getYear, Comparator.reverseOrder() ) 
					.thenComparing(Movie::getTitle)
					);
					
					//(m1, m2) -> -1);
			sortedMovies.addAll(movies);
			System.out.println(movies);
		}
		
		@Test
		void testSortMovies () {
			Collections.sort(movies, 
					Comparator.comparing(Movie::getYear)
					.thenComparing(Movie::getTitle) );
			System.out.println(movies);
		}

		@Test
		void totalDurationOfMoviesDirectedByClintStreaming() {
			var clint = persons.get(3);
			int totalDuration = movies.stream()
				.filter(m -> clint.equals(m.getDirector()))						//on passe par un lambda car ici la fonction recuperation des films de clint n'existe pas en tant que telle
				//.forEach(System.out::println);							//pour montrer le r�sultat du filtre "::" fait r�f�rence � des fonctions
				// 	.map(m -> m.getDuration()) ou plus simple car il existe deja une fonction duration
				.mapToInt(Movie::getDuration)       //stream sp�cifique  sur les entiers, sinon via autoboxing transformation en Integer
				//.forEach(System.out::println);
				.sum();
			    System.out.println("Total Duration of movies directed by Clint is : " + totalDuration + " min");
		}
		
		@Test
		void premiereAnneeDesFilmsAvengers() {
			
			var optFirtsYear = movies.stream()	
				.filter (m -> m.getTitle().contains("Avengers"))
				//.forEach(System.out::println);
				// .mapToInt(m -> m.getYear())
				.mapToInt(Movie::getYear)
				.min();
			if (optFirtsYear.isPresent()) {
				System.out.println("First year : " + optFirtsYear.getAsInt());
			} else {
				System.out.println("Pas de r�sultat");
			}
			
		
//				//	.forEach(System.out::println);
//					.sum();
//		
		}

		@Test
		void premiereEtDerniereAnneeDesFilmsAvengers() {
			var stats = movies.stream()	
					.filter (m -> m.getTitle().contains("Avengers"))
					.mapToInt(Movie::getYear)
					.summaryStatistics();
			System.out.println("First year : "  + stats.getMin() );
			System.out.println("Last year : "  + stats.getMax() );
//			if (optFirtsYear.isPresent()) {
//				System.out.println("First year : " + optFirtsYear.getAsInt());
//			} else {
//				System.out.println("Pas de résultat");
//			}
		}
		
		@Test
		void testListeChronologiqueAvengersMovie() {
			var avengersMovies = movies.stream() 
				.filter (m -> m.getTitle().contains("Avengers"))
				//.collect(collector >Objet constitu� de plusieurs fonctions:
										// supplier, accumulateur, combiner, finisher //la seule methode dans map reduce qui ne prend pas de fonction en parametre
				// .collect(Collectors.toList());    //c'est pas le meilleur si pas besoin de l'ordre des valeurs
				.collect(Collectors.toCollection(() -> new TreeSet<>(
						Comparator.comparing(Movie::getYear)				//le to list ou tocollection pour reverser dans une collection les infos
						) ) );
			System.out.println(avengersMovies);
			
		}
		
		@Test
		void titlesAvengersMovies() {
			var joinedTitles = movies.stream() 
				.filter (m -> m.getTitle().contains("Avengers"))
				.map(Movie::getTitle)
				.collect(Collectors.joining(", "));
						
			System.out.println(joinedTitles);
		}
		
		@Test
		void testLimit() {
			movies.stream()
				.filter ((Movie m) -> m.getYear() > 1900)  // ou bien "m" tout cours
				.limit(5)
				.forEach(System.out::println);	
		}
		
		@Test
		void testNomberOfFilmSupTo2h () {
			long supTo2h = movies.stream()
				.filter( (Movie m) -> m.getDuration() >= 120 )
				.count();
			System.out.println("nb de film de plus de 2h : " + supTo2h);
		}
		
		@Test
		void titreLePlusLong () {
			var maxLength = movies.stream()
				.map(Movie::getTitle)
				.mapToInt(String::length)
				//.forEach(System.out::println);
				.max()
				.getAsInt(); 			
			System.out.println(maxLength);
			var titleMaxLenght = movies.stream()
				.map(Movie::getTitle)
				.filter(t -> t.length() == maxLength)
				.collect(Collectors.toList());
			System.out.println("Film au titre le plus long (" + maxLength + ") caract�res " + titleMaxLenght);
				}
		
		@Test
		void nbMoviesByYear() {
			var res = movies.stream()
				//.map(Movie::getYear) on pourrait le laisser mais on peut mettre directement se paramtre dans le groupingby
				.collect(Collectors.groupingBy(Movie::getYear, Collectors.counting() ));
			System.out.println(res);
		}
		
//		@Test   //� corriger 
//		void nbMoviesByDirector() {
//			var nbMovieByDir =	movies.stream()
//				.map(m -> m.getDirector())
//				.filter(Objects::nonNull)
//				.collect(Collectors.groupingBy(UnaryOperator.identify(),
//						Collectors.counting() ));
//			System.out.println(nbMovieByDir);
////			
////			// ou bien
//			
//		@Test
//		void nbMoviesByDirector() {
//			 nbMovieByDir = movies.stream()
//				.filter(m -> Objects.nonNull(m.getDirector() ))
//				.collect(Collectors.groupingBy(Movie::getDirector,
//						Collectors.counting()						
//						));
//			System.out.println(nbmoviesbydir);
//			}
			
//}
		@Test
		void filmographies() {
			 var filmographies = movies.stream()
				.filter(m -> Objects.nonNull(m.getDirector() ))
				.collect(Collectors.groupingBy(Movie::getDirector,
						//Collectors.toList())); par defaut c'est celui la
						Collectors.toCollection(
								()->new TreeSet<>(Comparator.comparing(Movie::getYear,
										Comparator.reverseOrder()))
								)));
									
			System.out.println(filmographies);
			}
		
		
		@Test
		void dpersonByDecade () {
			var res = persons.stream()
				.collect(Collectors.groupingBy((Person p) -> p.getBirthdate().getYear() / 10
						));
				
				System.out.println(res);
		}
		
		@Test
		void TestParasite () {
			movies.stream() 
				.filter (m -> m.getTitle().equals("Parasite"))
				//.forEach(System.out::println);
				.findFirst()
				.ifPresent(System.out::println);
				//forEach(action;)
		}
		
		@Test
		void TestParasite2() {
			var movie = movies.get(1);
			movie.streamActors()
					.forEach(System.out::println);
				//.map(Movie.getActors)
		}
		
		@Test
		void TestParasite3avecVirguleenmodeStream() {
			var movie = movies.get(1);
			var actorsText = movie.streamActors()
				.map(Person::getName)	
				.collect(Collectors.joining(", "));
			System.out.println(actorsText);
		}
		@Test
		void TestParasite3avecVirguleItérable() {   // c'est une vieille methode
			var movie = movies.get(1);
			for(var it = movie.iteratorActors(); it.hasNext(); ) {
				var actor = it.next();
			
			System.out.println(actor);
				
			}
		}
}

