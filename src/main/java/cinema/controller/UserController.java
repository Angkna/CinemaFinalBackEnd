package cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.User;
import cinema.service.IUserService;

@RestController 
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping
	@ResponseBody
	List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@CrossOrigin
	@GetMapping("/username")
	@ResponseBody
	User getByUserName(@RequestParam("u") String username){
		return userService.getByUserName(username);
	}
	
	@CrossOrigin
	@GetMapping("/token")
	@ResponseBody
	User getByToken(@RequestParam("t") String jwtToken){
		return userService.getByToken(jwtToken);
	}
	
	@CrossOrigin
	@PostMapping
	@ResponseBody
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

}
