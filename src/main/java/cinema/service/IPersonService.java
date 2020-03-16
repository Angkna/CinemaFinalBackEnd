package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.MovieFull;
import cinema.dto.PersonFull;
import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;

public interface IPersonService {

	List<Person> getAllPersons();
	Set<Person> getByNameContainingIgnoreCase(String name);
	Set<Person> getByBirthdateYear(int year);
	Optional<Person> getByIdPerson(int idPerson);
	Person addPerson(Person person);
	Set<Person> getByNationality(String nationality);
	Nationality addNationality(String nationality);
	Optional<Person> addNationalityToPerson(String nationality, int idPerson);
	Optional<PersonFull> getMovieDirector(int idMovie);
	List<PersonFull> getMovieActors(int idMovie);
	
	Optional<Person> modifyPerson(Person person);
	Optional<Person> deletePerson(int idPerson);
}
