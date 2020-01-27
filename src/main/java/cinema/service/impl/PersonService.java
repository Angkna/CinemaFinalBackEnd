package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Person;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService {

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public List<Person> getAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getByNameContainingIgnoreCase(String name) {
		return personRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Set<Person> getByBirthdateYear(int year) {
			return personRepository.findByBirthdateYear(year);
	}

	@Override
	public Optional<Person> getByPersonId(int idPerson) {
		// TODO Auto-generated method stub
		return personRepository.findById(idPerson);
	}

	@Override
	public Person addPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getByNationalities(String nationalities) {
		return personRepository.findByNationalities(nationalities);
	}
	
	
	
}
