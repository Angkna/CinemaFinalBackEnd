package cinema.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Movie;
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

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Set<Movie> getMovieByDirector(String name) {
		return movieRepository.findByDirectorNameEndingWith(name);
	}

//	@Override
//	public Set<Movie> movieByActorNameEndingWith(String name) {
//		return movieRepository.findByActorNameEndingWith(name);
//	}

	@Override
	public Set<Movie> getMovieByActorsName(String name) {
		return movieRepository.findByActorsName(name);
	}

	@Override
	public Set<Movie> getMovieByActorsIdPerson(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor);
	}

	@Override
	public Set<Movie> getMovieByTitle(String Title) {
		return movieRepository.findByTitle(Title);
	}
	
}
