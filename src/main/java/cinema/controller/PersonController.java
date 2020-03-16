package cinema.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.MovieFull;
import cinema.dto.PersonFull;
import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	IPersonService personService;	
	
	@CrossOrigin
	@GetMapping
	@ResponseBody
	public List<Person>getAllPersons() {
		return personService.getAllPersons();
		
	}
	@CrossOrigin
	@GetMapping("/byNamePartial")
	@ResponseBody
		public Set<Person> getByNameContainingIgnoreCase(@RequestParam("n") String name){
			return personService.getByNameContainingIgnoreCase(name);
	}

	
	@CrossOrigin
	@GetMapping ("/byBirthDate")
	@ResponseBody
		public Set<Person> findByBirthdateYear(@RequestParam("b") int year) {
		return personService.getByBirthdateYear(year);
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Person> personById(@PathVariable("id")int idPerson) {
		return personService.getByIdPerson(idPerson);
	}

	@CrossOrigin
	@GetMapping ("/byNationalities")
	@ResponseBody
		public Set<Person> findByNationalities(@RequestParam("n") String nationalities) {
			return personService.getByNationality(nationalities);
	}
	@CrossOrigin
	@GetMapping("/directorByMovieId/{id}")
	@ResponseBody
	Optional<PersonFull> getDirectorFromMovie(@PathVariable("id") int idMovie){
		return personService.getMovieDirector(idMovie);
	}
	@CrossOrigin
	@GetMapping("/actorsByMovieId/{id}")
	@ResponseBody
	List<PersonFull> getActorsFromMovie(@PathVariable("id") int idMovie){
		return personService.getMovieActors(idMovie);
	}
	
	///////////////////////POST////////////////////////////
	
	@CrossOrigin
	@PostMapping ("/addPerson")
		Person addPerson(@RequestBody Person person){
			return personService.addPerson(person);	
	}
	@CrossOrigin
	@PostMapping ("/addNewNationality")
	Nationality addNationality(@RequestParam("n") String nationality){
		return personService.addNationality(nationality);	
	}

	///////////////////////////PUT////////////////////////
	@CrossOrigin
	@PutMapping ("/modify")
	Optional<PersonFull> modifyPerson(@RequestBody PersonFull person){
		return personService.modifyPerson(person);
	}
	
	@CrossOrigin
	@PutMapping ("/addNationalityToPerson")
		Optional<Person> addNationalityToPerson(@RequestParam("n") String nationality, @RequestParam("p") int idPerson) {
		return personService.addNationalityToPerson(nationality, idPerson);
	}
	
//	@CrossOrigin
//	@PutMapping("/modify")
//	@ResponseBody
//	public Optional<Person> modifyPerson (@RequestBody Person person) {
//		return personService.modifyPerson(person);
//	}
	
	//////////////////DELETE //////////£@CrossOrigin
	@CrossOrigin
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<Person> deletePerson (@PathVariable ("id") int idPerson) {
		return personService.deletePerson(idPerson);	
	}
	
	
}
