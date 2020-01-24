package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.Person;

public interface IPersonService {

	List<Person> getAllPersons();
	Set<Person> getByNameContainingIgnoreCase(String name);
	Set<Person> getByBirthdateYear(int year);
	Optional<Person> getByPersonId(int idPerson);


}
