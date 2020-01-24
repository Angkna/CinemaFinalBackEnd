package cinema.controller;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/movie")

public class PersonController {
	
	@Autowired
	IPersonService personService;
	
	
	
	
	@GetMapping ("")
	public List<Person>getAllPersons() {
		return personService.getAllPersons();
		
	}
	
	@GetMapping("/byNamePartial")
	@ResponseBody
		public Set<Person> getByNameContainingIgnoreCase(String name){
		return personService.getByNameContainingIgnoreCase(name);
	}
	
	
	
	
	
	
	///////////////////////////////////////////////////
	
//	@PostMapping 
//	Person addPerson(Person person){
//		//TODO
//				return null;
		
	//}


}
