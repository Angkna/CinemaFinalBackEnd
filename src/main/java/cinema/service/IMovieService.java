package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.Act;
import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface IMovieService {
	
	//get
	List<Movie> getAllMovies();
	Optional<Movie> getMovieById(int idMovie);
	
	Set<Movie> getMovieByTitle(String title);
	Set<Movie> getMovieByTitleContainingIgnoreCase(String title);
	Set<Movie> getMovieByTitleAndYear(String title,int Year);
	
	Set<Movie> getMovieByYear(int year);
	Set<Movie> getMovieByYearLessThan(int year);
	Set<Movie> getMovieByYearGreaterThan(int year);
	Set<Movie> getMovieByYearBetween(int year1,int year2);
	Set<Movie> getMovieByYearAndTitleAndDuration(int year, String name, int duration);
	
	Set<Movie> getMovieByDurationGreaterThan(int duration);
	Set<Movie> getMovieByDurationBetween(int duration1, int duration2);
	Set<Movie> getMovieByDurationLessThanEqual(int duration);
	
	Set<Movie> getMovieByGenresGenreIgnoreCase(String genre);
	
	Set<Movie> getMovieByRatingGreaterThanEqual(double rating);
	
	Set<Movie> getMovieBySynopsisContaining(String recherche);
	
	Set<Movie> getMovieByAudiance(Audiance audiance);
	
	Set<Movie> getMovieByDirector(Person person);
	Set<Movie> getMovieByDirectorName(String name);
	Set<Movie> getMovieByDirectorNameEndingWith(String name);
	Set<Movie> getMovieByDirectorId(int idDirector);
	
//	Set<Movie> getMovieByActorsName(String name);
//	Set<Movie> getMovieByActorsIdPerson(int idActor);
//	Set<Movie> getMovieByActorsNameEndingWith(String name);
	
	
	//post-put
	Movie addMovie(Movie movie);
	Genre addGenre(String genre);
	Optional<Movie> modifyMovie (Movie movie);
	Optional<Movie> addActor (int idActor, int idMovie);
	Optional<Movie> setDirector (int idDirector, int idMovie);
	Optional<Movie> addGenreToMovie (String genre, int idMovie);
	
	//delete
	Optional<Movie> deleteMovie (int idMovie);
	
	//test act
	Optional<Act> getActByMovieAndPerson (Movie movie, Person person);
	Act addAct (Movie movie, Person person, String role);
}
