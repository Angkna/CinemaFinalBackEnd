package cinema.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	//title
	Set<Movie> findByTitle(String title);
	Set<Movie> findByTitleContainingIgnoreCase(String title);
	Set<Movie> findByTitleAndYear(String title, int year);
	Set<Movie> findByTitleContainingIgnoreCaseAndYear(String title, int year);
		
	//year
	Set<Movie> findByYear(int year);
	Set<Movie> findByYearLessThan(int year);
	Set<Movie> findByYearGreaterThan(int year);
	Set<Movie> findByYearBetween(int year1, int year2);
	Set<Movie> findByYearAndTitle(int year, String title);
	Set<Movie> findByYearAndTitleAndDuration(int year, String title, int duration);
	
	//duration
	Set<Movie> findByDurationGreaterThan(int duration);
	Set<Movie> findByDurationBetween(int duration1, int duration2);
	Set<Movie> findByDurationLessThanEqual(int duration);
	
	//genres
	Set<Movie> findByGenresIgnoreCase(String genre);
	
	//rating
	Set<Movie> findByRatingGreaterThanEqual(double rating);
	
	//synopsis
	Set<Movie> findBySynopsisContaining(String recherche);
	
	//audiance
	Set<Movie> findByAudiance(Audiance audiance);
	//Set<Movie> findByAudianceLessThenEqual(Audiance audiance);
	
	//director
	Set<Movie> findByDirector (Person director);
	Set<Movie> findByDirectorName(String name);
	Set<Movie> findByDirectorNameEndingWith(String name);
	Set<Movie> findByDirectorIdPerson(int idDirector);
	
	//actor
//	Set<Movie> findByActorsName (String name);
//	Set<Movie> findByActorsIdPerson (int idActor);
//	Set<Movie> findByActorsNameEndingWith(String name);
	
}
