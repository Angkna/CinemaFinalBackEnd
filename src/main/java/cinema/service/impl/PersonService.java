package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.NationalityRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	NationalityRepository nationalityRepository;
	
	@Override
	public List<Person> getAllPersons() {
		return personRepository.findAll();
		
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
	public Optional<Person> getByIdPerson(int idPerson) {
			return personRepository.findById(idPerson);
	}

	@Override
	public Person addPerson(Person person) {
		Person personSave = personRepository.save(person);
		personRepository.flush();
		return personSave;
	}

	@Override
	public Set<Person> getByNationality(String nationality) {
		return personRepository.findByNationalitiesNationality(nationality);
	}

	@Override
	public Nationality addNationality(String nationality) {
		Nationality nationalitySave = nationalityRepository.save(new Nationality(nationality));
		nationalityRepository.flush();
		return nationalitySave;
	}

	@Override
	public Optional<Person> addNationalityToPerson(String nationality, int idPerson) {
		var nationalitySave = nationalityRepository.findByNationality(nationality);
		var personSave = personRepository.findById(idPerson);
		if (personSave.isPresent() && nationalitySave.isPresent() ) {
		personSave.get().getNationalities().add(nationalitySave.get());
		personRepository.flush();
		}
		return personSave;
	}
	
	
	
	
}
