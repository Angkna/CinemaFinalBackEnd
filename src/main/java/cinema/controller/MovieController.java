package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import cinema.persistence.entity.Movie;
import cinema.service.IMovieService;

@RestController
@RequestMapping("/api/movie")

public class MovieController {
	
	@Autowired
	IMovieService movieService;
	
	
	@GetMapping
	@ResponseBody
	public List<Movie> allmovies() {
		return movieService.getAllMovies();
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("id")int idMovie) {
			return movieService.getMovieById(idMovie);
	}
	
	
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByTitle(@RequestParam("t") String Title) {
		return movieService.getMovieByTitle(Title);
	}
	
	@GetMapping("/byTitleAndYear")
	@ResponseBody
	public Set<Movie> movieByTitleAndYear(@RequestParam("t") String Title,
									@RequestParam("y") int Year) {
		return movieService.getMovieByTitleAndYear(Title, Year);
	}

	@GetMapping("/byYearBetween")
	@ResponseBody
	public Set<Movie> movieByYearBetween(@RequestParam("y1") int Year,
									@RequestParam("y2") int Year2) {
		 return movieService.getMovieByYearBetween(Year, Year2);
	}
	
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<Movie> movieByDirector(@RequestParam("d") String name) {
		return movieService.getMovieByDirector(name);
	}
	
	@GetMapping("/byDirectorId")
	 @ResponseBody
	public Set<Movie> findByDirectorId(@RequestParam("d") int idDirector) {
		return movieService.getMovieByDirectorId(idDirector);		
	}

	
	@GetMapping("/byActor")
	@ResponseBody
	public Set<Movie> movieByActorsName(@RequestParam("a") String Person) {
		return movieService.getMovieByActorsName(Person);
	}
	
	@GetMapping("/byActorId")
	@ResponseBody
	public Set<Movie> moviebyActorsIdPerson(@RequestParam("a") int idActor) {
		return movieService.getMovieByActorsIdPerson(idActor);				
	}
	
	/**
	 * Method: Post
	 */
	
	////////////////////////Post//////////////////////////

	
	@PostMapping
	@ResponseBody
	public Movie addMovie (@RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}
	
	////////////////////////Put/////////////////////////
	
	
	@PutMapping("/modify")
	@ResponseBody
	public Optional<Movie> modifyMovie (@RequestBody Movie movie) {
		return movieService.modifyMovie(movie);
	}
	
	
	
	
	@PutMapping("/addActor") 			//post aurait marché
	public Optional<Movie> addActor (@RequestParam("a") int idActor, @RequestParam ("m") int idMovie) {
		return movieService.addActor(idActor, idMovie);
	}
	
	@PutMapping("/setDirector")
	public Optional<Movie> setDirector (@RequestParam("d") int idDirector, 
						@RequestParam("m") int idMovie) {
		return movieService.setDirector(idDirector, idMovie);
	}
	
	
	////////////////////////Delete////////////////////////
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<Movie> deleteMovie (@PathVariable ("id") int idMovie) {
		return movieService.deleteMovie(idMovie);	
	}
	
	
	
}

