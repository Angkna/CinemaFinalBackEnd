package cinema.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	
	//Name
	Set<Person> findByName(String name);
	Set<Person> findByNameContainingIgnoreCase(String name);
	
//	//id
//	Set<Person> findByPersonId(int idPerson);
	
	//Birthdate
		@Query("select p from Person p where extract (year from p.birthdate) = ?1")
	Set <Person> findByBirthdateYear(int year);

	//TODO for strong develp
//	@Query"select p from Person p where extract (year from p.birthdate) = ?
//	Set<Person> findByYearBetween(int year1, int year2)

}
