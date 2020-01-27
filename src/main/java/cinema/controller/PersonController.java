package cinema.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/movie")

public class PersonController {
	
	@Autowired
	IPersonService personService;	
	
	
	@GetMapping ("/getAllPersons")
	public List<Person>getAllPersons() {
		return personService.getAllPersons();
		
	}
	
	@GetMapping("/byNamePartial")
	@ResponseBody
		public Set<Person> getByNameContainingIgnoreCase(String name){
		return personService.getByNameContainingIgnoreCase(name);
	}
	
	
	@GetMapping ("/byBirthDate")
	@ResponseBody
		public Set <Person> findByBirthdateYear(int year) {
		return personService.getByBirthdateYear(year);
	}
	
	@GetMapping ("/byPersonId")
	@ResponseBody
		public Optional<Person> findByPersonId(int idPerson) {
		return personService.getByPersonId(idPerson);
	}
	
	///////////////////////////////////////////////////
	
	@PostMapping ("/addPerson")
	Person addPerson(Person person){
		return personService.addPerson(person);	
	}


}
