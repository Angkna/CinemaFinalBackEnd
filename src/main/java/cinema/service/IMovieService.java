package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface IMovieService {
	List<Movie> getAllMovies() ;
	Set<Movie> getMovieByDirector(String name);
	Set<Movie> getMovieByActorsName(String name) ;
	Set<Movie> getMovieByActorsIdPerson(int idActor) ;
	
	Set<Movie> getMovieByTitle(String Title) ;
	
	Set<Movie> getMovieByTitleAndYear(String Title,int Year) ;
	Set<Movie> getMovieByYearBetween(int Year,int Year2) ;
	Optional<Movie> getMovieById(int idMovie) ;
	
	Set<Movie> getByDirectorId(int idDirector) ;
}
