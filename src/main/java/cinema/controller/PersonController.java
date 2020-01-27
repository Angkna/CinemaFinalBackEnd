package cinema.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		public Set<Person> getByNameContainingIgnoreCase(@RequestParam("n") String name){
			return personService.getByNameContainingIgnoreCase(name);
	}

	
	
	@GetMapping ("/byBirthDate")
	@ResponseBody
		public Set <Person> findByBirthdateYear(@RequestParam("b") int year) {
		return personService.getByBirthdateYear(year);
	}
	
	//NEMARCHEPAS!!!!!
	@GetMapping ("/byPersonId")
	@ResponseBody
		public Optional<Person> findByPersonId(@PathVariable("a") int idPerson) {
		return personService.getByPersonId(idPerson);
	}
	
//	@GetMapping ("/byNationalities")
//	@ResponseBody
//		public Set<Person> findByNationalities(@PathVariable("n") String nationalities) {
//			return personService.getByNationalities(nationalities);
//	}
	
	///////////////////////////////////////////////////
	
	//NEMARCHEPAS!!!!
	@PostMapping ("/addPerson")
		Person addPerson(@RequestBody Person person){
			return personService.addPerson(person);	
	}


}
