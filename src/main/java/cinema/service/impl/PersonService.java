package cinema.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.dto.PersonFull;
import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.NationalityRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	ModelMapper mapper;
	
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
		var exist = nationalityRepository.findByNationality(nationality);
			
		if(exist.isEmpty() ) {
				Nationality nationalitySave = nationalityRepository.save(new Nationality(nationality));
				nationalityRepository.flush();
				return nationalitySave;
		}
		return exist.get();
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

	@Override
	public Optional<PersonFull> getMovieDirector(int idMovie) {
		return movieRepository.findById(idMovie)
				.map(m -> Objects.isNull(m.getDirector()) ? null : mapper.map(m.getDirector(), PersonFull.class));
	}

	@Override
	public List<PersonFull> getMovieActors(int idMovie) {
		return movieRepository.findById(idMovie)
				.map(m -> m.getActors().stream()
						.map(a -> mapper.map(a, PersonFull.class))
						.collect(Collectors.toList())
						)
				.orElse(List.of());
	}

	@Override
	public Optional<PersonFull> modifyPerson(PersonFull person) {
		var optPerson = personRepository.findById(person.getIdPerson());
		optPerson.ifPresent(m ->  {
				m.setName(person.getName());
				m.setBiography(person.getBiography());
				m.setBirthdate(person.getBirthdate());
				//m.setMovies(person.getMovies());
				//m.setNationalities(person.getNationalities());
		});
		personRepository.flush();
		return optPerson.map(m -> mapper.map(m, PersonFull.class));
	}
	
}
