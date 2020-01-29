package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Act;
import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.GenreRepository;
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
	GenreRepository genreRepository;

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(int idMovie) {
		return movieRepository.findById(idMovie);
	}

	@Override
	public Set<Movie> getMovieByTitle(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	public Set<Movie> getMovieByTitleContainingIgnoreCase(String title) {
		return movieRepository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public Set<Movie> getMovieByTitleAndYear(String title, int year) {
		return movieRepository.findByTitleAndYear(title, year);
	}

	@Override
	public Set<Movie> getMovieByYear(int year) {
		return movieRepository.findByYear(year);
	}

	@Override
	public Set<Movie> getMovieByYearLessThan(int year) {
		return movieRepository.findByYearLessThan(year);
	}

	@Override
	public Set<Movie> getMovieByYearGreaterThan(int year) {
		return movieRepository.findByYearGreaterThan(year);
	}

	@Override
	public Set<Movie> getMovieByYearBetween(int year1, int year2) {
		if (year2 <= year1) {
			return movieRepository.findByYearBetween(year2, year1);
		}
	return movieRepository.findByYearBetween(year1, year2);
	}

	@Override
	public Set<Movie> getMovieByYearAndTitleAndDuration(int year, String title, int duration) {
		return movieRepository.findByYearAndTitleAndDuration(year, title, duration);
	}

	@Override
	public Set<Movie> getMovieByDurationGreaterThan(int duration) {
		return movieRepository.findByDurationGreaterThan(duration);
	}

	@Override
	public Set<Movie> getMovieByDurationBetween(int duration1, int duration2) {
		if (duration2 < duration1) {
			return movieRepository.findByDurationBetween(duration2, duration1);
		}
	return movieRepository.findByDurationBetween(duration1, duration2);
	}

	@Override
	public Set<Movie> getMovieByDurationLessThanEqual(int duration) {
		return movieRepository.findByDurationLessThanEqual(duration);
	}
	
	@Override
	public Set<Movie> getMovieByGenresGenreIgnoreCase(String genre) {
		return movieRepository.findByGenresGenreIgnoreCase(genre);
	}	

	@Override
	public Set<Movie> getMovieByRatingGreaterThanEqual(double rating) {
		return movieRepository.findByRatingGreaterThanEqual(rating);
	}

	@Override
	public Set<Movie> getMovieBySynopsisContaining(String recherche) {
		return movieRepository.findBySynopsisContaining(recherche);
	}

	@Override
	public Set<Movie> getMovieByAudiance(Audiance audiance) {
		return movieRepository.findByAudiance(audiance);
	}

	@Override
	public Set<Movie> getMovieByDirector(Person person) {
		return movieRepository.findByDirector(person);
	}

	@Override
	public Set<Movie> getMovieByDirectorName(String name) {
		return movieRepository.findByDirectorName(name);
	}

	@Override
	public Set<Movie> getMovieByDirectorNameEndingWith(String name) {
		return movieRepository.findByDirectorNameEndingWith(name);
	}

	@Override
	public Set<Movie> getMovieByDirectorId(int idDirector) {
		  var directOpt =personRepository.findById(idDirector); 
			return directOpt.map(d-> movieRepository.findByDirector(d))
					.orElseGet( () -> Collections.emptySet());
	}

	@Override
	public Set<Movie> getMovieByActorsName(String name) {
		return movieRepository.findByActorsName(name);
	}

	@Override
	public Set<Movie> getMovieByActorsIdPerson(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor);
	}

	@Override
	public Set<Movie> getMovieByActorsNameEndingWith(String name) {
		return movieRepository.findByActorsNameEndingWith(name);
	}

	@Override
	public Movie addMovie(Movie movie) {
		Movie movieSaved = movieRepository.save(movie);
		movieRepository.flush();
		return movieSaved;
	}

	@Override
	public Optional<Movie> modifyMovie(Movie movie) {
		var optMovie = movieRepository.findById(movie.getIdMovie());
		optMovie.ifPresent(m ->  {
				m.setTitle(movie.getTitle());
				m.setYear(movie.getYear());
				m.setDuration(movie.getDuration());
				m.setDirector(movie.getDirector());
		});	
		movieRepository.flush();
		return optMovie;
	}

	@Override
	public Optional<Movie> addActor(int idActor, int idMovie) {
		var movieOpt = movieRepository.findById(idMovie);
		var actorOpt = personRepository.findById(idActor);
		if (movieOpt.isPresent() && actorOpt.isPresent()) {
			movieOpt.get().getActors().add(actorOpt.get());
			movieRepository.flush();
		}
		return movieOpt;
	}

	@Override
	public Optional<Movie> setDirector(int idDirector, int idMovie) {
		var movieOpt = movieRepository.findById(idMovie);
		var directorOpt = personRepository.findById(idDirector);
		if (movieOpt.isPresent() && directorOpt.isPresent()) {
			movieOpt.get().setDirector(directorOpt.get());
			movieRepository.flush();
		}
		return movieOpt;
	}

	@Override
	public Optional<Movie> deleteMovie(int idMovie) {
		var movieToDelete = movieRepository.findById(idMovie);
		movieToDelete.ifPresent(m -> {
		movieRepository.delete(m);
		movieRepository.flush();
		});
		return movieToDelete;
	}

	@Override
	public Optional<Movie> addGenreToMovie(String genre, int idMovie) {
		var movieOpt = movieRepository.findById(idMovie);
		var genreOpt = genreRepository.findByGenre(genre);
		if (movieOpt.isPresent() && genreOpt.isPresent()) {
			movieOpt.get().getGenres().add(genreOpt.get());
			movieRepository.flush();
		}
		return movieOpt;
	}

	@Override
	public Genre addGenre(String genre) {
		var exist = genreRepository.findByGenre(genre);
		if (exist.isEmpty()) {
			Genre genreNew = genreRepository.save(new Genre(genre));
			genreRepository.flush();
			return genreNew;
		}
		return exist.get();
	}

//	@Override
//	public Act addAct(Movie movie, Person person, String role) {
//		var exist = actRepository.findBy
//		return null;
//	}


}
