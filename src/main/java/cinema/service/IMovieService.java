package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.MovieFull;
import cinema.dto.MovieLight;
import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Person;

public interface IMovieService {
	
	//get
	List<MovieLight> getAllMovies();
	Optional<MovieFull> getMovieById(int idMovie);
	
	Set<MovieLight> getMovieByTitle(String title);
	Set<MovieLight> getMovieByTitleContainingIgnoreCase(String title);
	Set<MovieLight> getMovieByTitleAndYear(String title,int Year);
	Set<MovieLight> getMovieByTitleContainingIgnoreCaseAndYear(String title,int Year);
	
	Set<MovieLight> getMovieByYear(int year);
	Set<MovieLight> getMovieByYearLessThan(int year);
	Set<MovieLight> getMovieByYearGreaterThan(int year);
	Set<MovieLight> getMovieByYearBetween(int year1,int year2);
	Set<MovieLight> getMovieByYearAndTitleAndDuration(int year, String name, int duration);
	
	Set<MovieLight> getMovieByDurationGreaterThan(int duration);
	Set<MovieLight> getMovieByDurationBetween(int duration1, int duration2);
	Set<MovieLight> getMovieByDurationLessThanEqual(int duration);
	
	Set<MovieLight> getMovieByGenresIgnoreCase(String genre);
	
	Set<MovieLight> getMovieByRatingGreaterThanEqual(double rating);
	
	Set<MovieLight> getMovieBySynopsisContaining(String recherche);
	
	Set<MovieLight> getMovieByAudiance(Audiance audiance);
	
	Set<MovieLight> getMovieByDirector(Person person);
	Set<MovieLight> getMovieByDirectorName(String name);
	Set<MovieLight> getMovieByDirectorNameEndingWith(String name);
	Set<MovieLight> getMovieByDirectorId(int idDirector);
	
	Set<MovieLight>  getLikedMovies(int idUser);

//	Set<MovieLight> getMovieByActorsName(String name);
//	Set<MovieLight> getMovieByActorsIdPerson(int idActor);
//	Set<MovieLight> getMovieByActorsNameEndingWith(String name);
	
	
	//post-put
//	MovieFull addMovie(MovieFull movie);
	MovieLight addMovie(MovieLight movie);
	//String addGenre(String genre); //?
	Optional<MovieFull> modifyMovie (MovieFull movie);
	Optional<MovieFull> addActor (int idActor, int idMovie);
	Optional<MovieFull> setDirector (int idDirector, int idMovie);
	//Optional<MovieFull> addGenreToMovie (String genre, int idMovie);//?
	
	//test act
	//Optional<Act> getActByMovieAndPerson (Movie movie, Person person);
	//Act addAct (Movie movie, Person person, String role);
	
	//delete
	Optional<MovieFull> deleteMovie (int idMovie);

}
