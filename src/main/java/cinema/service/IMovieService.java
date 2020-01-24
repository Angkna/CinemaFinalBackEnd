package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.Movie;

public interface IMovieService {
	
	List<Movie> getAllMovies() ;
	Set<Movie> getMovieByDirector(String name);
	Set<Movie> getMovieByActorsName(String name) ;
	Set<Movie> getMovieByActorsIdPerson(int idActor) ;
	Set<Movie> getMovieByTitle(String Title) ;
	Set<Movie> getMovieByTitleAndYear(String Title,int Year) ;
	Set<Movie> getMovieByYearBetween(int Year,int Year2) ;
	Optional<Movie> getMovieById(int idMovie) ;
	Set<Movie> getMovieByDirectorId(int idDirector) ;
	Set<Movie> getMovieByActorNameEndingWith(String name);
	
	Movie addMovie(Movie movie);
	Optional<Movie> modifyMovie (Movie movie);
	Optional<Movie> addActor (int idActor, int idMovie);
	Optional<Movie> setDirector (int idDirector, int idMovie);
	
	Optional<Movie> deleteMovie (int idMovie);
}
