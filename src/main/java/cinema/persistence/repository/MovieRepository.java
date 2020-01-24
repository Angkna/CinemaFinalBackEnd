package cinema.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	Set<Movie> findByTitleContainingIgnoreCase(String title);
	Set<Movie> findByYear(int year);
	Set<Movie> findByYearLessThan(int year);
	Set<Movie> findByYearGreaterThan(int year);
	
	Set<Movie> findByYearBetween(int year1, int year2);
	Set<Movie> findByYearAndTitle(int year, String title);
	Set<Movie> findByYearAndTitleAndDuration(int year, String title, int duration);
	
	//director
	Set<Movie> findByDirector (Person director);
	Set<Movie> findByDirectorNameEndingWith(String name);
	//Set<Movie> findByIdDirector (int idDirector);
	
	//actor
	Set<Movie> findByActorsName (String name);
	Set<Movie> findByActorsIdPerson (int idActor);
	Set<Movie> findByActorsNameEndingWith(String name);
	
	//title
	Set<Movie> findByTitle(String title);
	Set<Movie> findByTitleAndYear(String title, int year);
	

	
}
