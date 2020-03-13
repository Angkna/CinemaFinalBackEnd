package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.UserDto;
import cinema.service.IUserService;

@RestController 
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping
	@ResponseBody
	public List<UserDto> getAllUser(){
		return userService.getAllUser();
	}
	
	@CrossOrigin
	@GetMapping("/username")
	@ResponseBody
	public Optional<UserDto> getByUserName(@RequestParam("u") String username){
		return userService.getByUserName(username);
	}
	
	@CrossOrigin
	@GetMapping("/token")
	@ResponseBody
	public Optional<UserDto> getByToken(@RequestParam("t") String jwtToken){
		return userService.getByToken(jwtToken);
	}
	
	@CrossOrigin
	@GetMapping("/movieLiked")
	@ResponseBody
	public Set<UserDto> getByMovieLiked(@RequestParam("id") int idMovie){
		return userService.getByMovieLiked(idMovie);
	}
	
	@CrossOrigin
	@PostMapping
	@ResponseBody
	public UserDto addUser(@RequestBody UserDto user) {
		return userService.addUser(user);
	}
	
	@CrossOrigin
	@PutMapping
	@ResponseBody
	public Optional<UserDto> modifyUser(@RequestBody UserDto user) {
		return userService.modifyUser(user);
	}
	
	@CrossOrigin
	@PutMapping("/addMovieLiked")
	@ResponseBody
	public Optional<UserDto> addLikedMovieToUser(@RequestParam("idUser") String userName, @RequestParam("idMovie") int idMovie) {
		return userService.addLikedMovieToUser(userName, idMovie);
	}
	
	@CrossOrigin
	@PutMapping("/deleteMovieLiked")
	@ResponseBody
	public Optional<UserDto> deleteLikedMovieToUser(@RequestParam("idUser") String userName, @RequestParam("idMovie") int idMovie) {
		return userService.deleteLikedMovieToUser(userName, idMovie);
	}
}
