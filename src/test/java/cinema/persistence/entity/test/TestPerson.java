package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
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
	
	@Test
	void testPersonSaven() {
			//given
		//given
		Person person = new Person ("Clint Eastwood", LocalDate.of(1930, 5, 31));
		//when
		repoPerson.save(person);
		//then
		System.out.println("personne créée : " + person);
		
//		List<Person> data = List.of(
//				new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),
//				new Person("Gerard Darmond", LocalDate.of(1948, 02, 29)),
//				new Person("Todd Phillips")
//				);
//		data.forEach(EntityManager::persist);
////		var name = 1994;
//		String title = "Le Roi Lion";
//		var duration = 166;
//		var dataRead = repoMovie.findByYearAndTitleAndDuration(year, title, duration);
//						
//		System.out.println(dataRead);		
	}

	@Test
	void testPersonNamePartial () {
		Person person = new Person ("Bradley" );
		repoPerson.save(person);
		var name = person.getName();
		System.out.println("name person : " + name);
		
	}
	
//	@Test
//	void testPersonByBirthdateYear () {
//		List<Person> data = List.of(
//					new Person ("Bradley", LocalDate.of(1975,05,01)),
//					new Person ("Bob", LocalDate.of(1992,06,29))
//					);
//		data.forEach(entityManager::persist);
//		
//		//when
//		var year = 1975;
//		var dateRead = repoPerson.findByBirthdateYear(year);
//		System.out.println(dataRead);
//		
////		var name = person.getBirthdate();
////		System.out.println("person by birthdate  : "+ name);
//	}

//	}
	
	
	
}
