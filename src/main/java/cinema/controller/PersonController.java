package cinema.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/person")

public class PersonController {
	
	@Autowired
	IPersonService personService;	
	
	
	@GetMapping
	@ResponseBody
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
	
		
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Person> personById(@PathVariable("id")int idPerson) {
		return personService.getByIdPerson(idPerson);
	}

		
	@GetMapping ("/byNationalities")
	@ResponseBody
		public Set<Person> findByNationalities(@RequestParam("n") String nationalities) {
			return personService.getByNationality(nationalities);
	}
	
	///////////////////////POST////////////////////////////
	
	
	@PostMapping ("/addPerson")
		Person addPerson(@RequestBody Person person){
			return personService.addPerson(person);	
	}

	@PostMapping ("/addNewNationality")
	Nationality addNationality(@RequestParam("n") String nationality){
		return personService.addNationality(nationality);	
	}

	///////////////////////////PUT////////////////////////
	
	@PutMapping ("/addNationalityToPerson")
		Optional<Person> addNationalityToPerson(@RequestParam("n") String nationality, @RequestParam("p") int idPerson) {
		return personService.addNationalityToPerson(nationality, idPerson);
	}
	
}
