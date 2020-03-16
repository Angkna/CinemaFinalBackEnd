package cinema.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cinema.persistence.entity.Act;
import cinema.dto.MovieLight;
import cinema.dto.MovieFull;

import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

import cinema.persistence.repository.ActRepository;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IMovieService;


@Service
@Transactional
public class MovieService implements IMovieService { 


	@Autowired
	MovieRepository movieRepository;
	@Autowired
	PersonRepository personRepository;

	@Autowired
	ActRepository actRepository;
	@Autowired
	ModelMapper mapper;


	@Override
	public List<MovieLight> getAllMovies() {
		List<Movie> movieEntities = movieRepository.findAll();
		var listSimpleMovie = movieEntities.stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toList());
		return listSimpleMovie;
	}

	@Override
	public Optional<MovieFull> getMovieById(int idMovie) {
		return movieRepository.findById(idMovie).map(m -> mapper.map(m, MovieFull.class));
	}

	@Override
	public Set<MovieLight> getMovieByTitle(String title) {
		return movieRepository.findByTitle(title).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}	

	@Override
	public Set<MovieLight> getMovieByTitleContainingIgnoreCase(String title) {
		return movieRepository.findByTitleContainingIgnoreCase(title).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByTitleAndYear(String title, int year) {
		return movieRepository.findByTitleAndYear(title, year).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}
	
	@Override
	public Set<MovieLight> getMovieByTitleContainingIgnoreCaseAndYear(String title, int year) {
		return movieRepository.findByTitleContainingIgnoreCaseAndYear(title, year).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByYear(int year) {
		return movieRepository.findByYear(year).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByYearLessThan(int year) {
		return movieRepository.findByYearLessThan(year).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByYearGreaterThan(int year) {
		return movieRepository.findByYearGreaterThan(year).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override

	public Set<MovieLight> getMovieByYearBetween(int year, int year2) {
		return movieRepository.findByYearBetween(year, year2).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByYearAndTitleAndDuration(int year, String title, int duration) {
		return movieRepository.findByYearAndTitleAndDuration(year, title, duration).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDurationGreaterThan(int duration) {
		return movieRepository.findByDurationGreaterThan(duration).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDurationBetween(int duration1, int duration2) {
		return movieRepository.findByDurationBetween(duration1, duration2).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDurationLessThanEqual(int duration) {
		return movieRepository.findByDurationLessThanEqual(duration).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}
	
	@Override
	public Set<MovieLight> getMovieByGenresIgnoreCase(String genre) {
		return movieRepository.findByGenresIgnoreCase(genre).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}	

	@Override
	public Set<MovieLight> getMovieByRatingGreaterThanEqual(double rating) {
		return movieRepository.findByRatingGreaterThanEqual(rating).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieBySynopsisContaining(String recherche) {
		return movieRepository.findBySynopsisContaining(recherche).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByAudiance(Audiance audiance) {
		return movieRepository.findByAudiance(audiance).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDirector(Person person) {
		return movieRepository.findByDirector(person).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDirectorName(String name) {
		return movieRepository.findByDirectorName(name).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDirectorNameEndingWith(String name) {
		return movieRepository.findByDirectorNameEndingWith(name).stream()
				.map(m -> mapper.map(m, MovieLight.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public Set<MovieLight> getMovieByDirectorId(int idDirector) {
		//var directOpt =personRepository.findById(idDirector); 
		//return directOpt.map(d-> movieRepository.findByDirector(d)).orElseGet( () -> Collections.emptySet());
		return null;
	}

//	@Override
//	public Set<MovieLight> getMovieByActorsName(String name) {
//		return movieRepository.findByActorsName(name).stream()
//				.map(m -> mapper.map(m, MovieLight.class))
//				.collect(Collectors.toCollection(HashSet::new));
//	}
//
//	@Override
//	public Set<MovieLight> getMovieByActorsIdPerson(int idActor) {
//		return movieRepository.findByActorsIdPerson(idActor).stream()
//				.map(m -> mapper.map(m, MovieLight.class))
//				.collect(Collectors.toCollection(HashSet::new));
//	}
//
//	@Override
//	public Set<MovieLight> getMovieByActorsNameEndingWith(String name) {
//		return movieRepository.findByActorsNameEndingWith(name).stream()
//				.map(m -> mapper.map(m, MovieLight.class))
//				.collect(Collectors.toCollection(HashSet::new));
//	}

//	@Override
//	public MovieFull addMovie(MovieFull movieFull) {		
//		Movie movie = mapper.map(movieFull, Movie.class);
//		movieRepository.save(movie);
//		mapper.map(movie, movieFull);
//		return movieFull;
//	}
	
	@Override
	public MovieLight addMovie(MovieLight movieLight) {		
		Movie movie = mapper.map(movieLight, Movie.class);
		movieRepository.save(movie);
		mapper.map(movie, movieLight);
		return movieLight;
	}

	@Override
	public Optional<MovieFull> modifyMovie(MovieFull movie) {
		var optMovie = movieRepository.findById(movie.getIdMovie());
		optMovie.ifPresent(m ->  {
				m.setTitle(movie.getTitle());
				m.setYear(movie.getYear());
				m.setDuration(movie.getDuration());
				m.setRating(movie.getRating());
				m.setSynopsis(movie.getSynopsis());
				m.setAudiance(movie.getAudiance());
				m.setGenres(movie.getGenres());
				//m.setDirector(movie.getDirector());
		});
		movieRepository.flush();
		return optMovie.map(m -> mapper.map(m, MovieFull.class));
	}

	@Override
	public Optional<MovieFull> addActor(int idActor, int idMovie) {
//		Optional<Movie> movieOpt = movieRepository.findById(idMovie);
//		Optional<Person> actorOpt = personRepository.findById(idActor);
//		if (movieOpt.isPresent() && actorOpt.isPresent()) {
//			movieOpt.get().getActors().add(actorOpt.get());
//		}
//		return movieOpt;
	return null;
	}

	@Override
	public Optional<MovieFull> setDirector(int idDirector, int idMovie) {
		return movieRepository.findById(idMovie)
				.flatMap(m -> personRepository.findById(idDirector)
						.map(a -> {
							m.getActors().add(a);
							return mapper.map(m, MovieFull.class);
						}));
//		var movieOpt = movieRepository.findById(idMovie);
//		var directorOpt = personRepository.findById(idDirector);
//		if (movieOpt.isPresent() && directorOpt.isPresent()) {
//			movieOpt.get().setDirector(directorOpt.get());
//			movieRepository.flush();
//			return movieOpt;
//		}
//		
//		return Optional.empty();
	}

	@Override
	public Optional<MovieFull> deleteMovie(int idMovie) {
		var movieToDelete = movieRepository.findById(idMovie);
		var movieFullToDelete = movieToDelete.map(m -> mapper.map(m, MovieFull.class));
		movieToDelete.ifPresent(m -> {
			movieRepository.delete(m);
		});
		movieRepository.flush();
		return movieFullToDelete;
	}



//	@Override
//	public Optional<MovieFull> addGenreToMovie(String genre, int idMovie) {
//		var movieOpt = movieRepository.findById(idMovie);
//		if (movieOpt.isPresent() && genreOpt.isPresent()) {
//			movieOpt.get().getGenres().add(genreOpt.get());
//			movieRepository.flush();
//		}
//		return movieOpt;
//	}
//
//	@Override
//	public Genre addGenre(String genre) {
//		var exist = genreRepository.findByGenre(genre);
//		if (exist.isEmpty()) {
//			Genre genreNew = genreRepository.save(new Genre(genre));
//			genreRepository.flush();
//			return genreNew;
//		}
//		return exist.get();
//	}

//	@Override
//	public Act addAct(Movie movie, Person person, String role) {
//		//var idActExist = actRepository.findByMovieAndPerson(movie, person);
//		return null;
//	}
//
//	@Override
//	public Optional<Act> getActByMovieAndPerson(Movie movie, Person person) {
//		return actRepository.findByMovieAndPerson(movie, person);
//	}


}
