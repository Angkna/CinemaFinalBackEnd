package cinema.persistence.entity.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestPerson {

	 @Autowired
	 PersonRepository repoPerson;
	 
	 @Autowired
	EntityManager entityManager; 
	
	@Test
	void testPersonSaven() {
			//given
		//given
		Person person = new Person ("Clint Eastwood", LocalDate.of(1930, 5, 31));
		//when
		repoPerson.save(person);
		//then
		System.out.println("personne créée : " + person);
	}

	
	@Test
	void testPersonName () {
		List<Person> data = List.of(
				new Person ("Bradley Cooper", LocalDate.of(1975,05,01)),
				new Person ("Bob", LocalDate.of(1992,06,29))
				);
	data.forEach(entityManager::persist);
		
		var name = "Bradley Cooper";
		var personNameRead = repoPerson.findByName(name).stream().findFirst().get();
		entityManager.flush();
		//System.out.println("name person : " + nameRead);
		assertTrue(()-> personNameRead.getName().equals(name));
	}
	
	
	@Test
	void testPersonNamePartial () {
		List<Person> data = List.of(
				new Person ("Bradley", LocalDate.of(1975,05,01)),
				new Person ("Bob", LocalDate.of(1992,06,29))
				);
	data.forEach(entityManager::persist);
		
		var name = "Bradley";
		var personNameRead = repoPerson.findByName(name).stream().findFirst().get();
		entityManager.flush();
		//System.out.println("name person : " + name);
		assertTrue( () -> personNameRead.getName().contains(name)    );
	}
	
	
	
	@Test
	void testByNameContainingIgnoreCase () {
		List<Person> data = List.of(
				new Person ("Bradley Cooper", LocalDate.of(1975,05,01)),
				new Person ("Bob", LocalDate.of(1992,06,29))
				);
	data.forEach(entityManager::persist);
		
		var name = "Cooper";
		var nameReadBrad = repoPerson.findByNameContainingIgnoreCase(name).stream().findFirst().get();
		//System.out.println("name person : " + nameReadBrad);
		assertTrue(nameReadBrad.getName().contains(name));
	}
	
	
	@Test
	void testPersonByBirthdateYear () {
		List<Person> data = List.of(
					new Person ("Bradley", LocalDate.of(1975,05,01)),
					new Person ("Bob", LocalDate.of(1992,06,29))
					);
		data.forEach(entityManager::persist);
		
		//when
		var year = 1975;
		var dataRead = repoPerson.findByBirthdateYear(year);
		entityManager.flush();
		//System.out.println(dataRead);
		assertTrue (dataRead.stream()
				.mapToInt(p -> p.getBirthdate().getYear())
				.allMatch(y -> (y == year))
				);
	}

	
	@Test
	void testbyPersonId () {
	//	List<Person> data = List.of(
				var person = new Person ("Bradley", LocalDate.of(1975,05,01));
				//new Person ("Bob", LocalDate.of(1992,06,29))
			//	);
	//data.forEach(entityManager::persist);
	entityManager.persist(person);
	var id = person.getIdPerson();
	//when
	var personReadOpt = repoPerson.findById(id);
	assertAll(
			() -> assertTrue(personReadOpt.isPresent()),
			() -> assertEquals(person.getName(), personReadOpt.get().getName())
			);
	}
	

//	@Test
//	void testbyPersonNationalities () {
//		var person = new Person ("Bradley", LocalDate.of(1975,05,01));
//		var nationalities = List.of("Australien");
//		person.setNationalities(nationalities);
//				
//		entityManager.persist(person);
	
	
//	}
	
	
	
	
	
}
	
	
	

