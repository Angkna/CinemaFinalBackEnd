package cinema.controller;

import java.util.Collections;
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
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IMovieService;
import cinema.service.impl.MovieService;

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
	
//	@GetMapping("/{id}")
//	@ResponseBody
//	public Optional<Movie> movieById(@PathVariable("id")int idMovie) {
//		return movieRepository.findById(idMovie);
//	}
//	
//	
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByTitle(@RequestParam("t") String Title) {
		return movieService.getMovieByTitle(Title);
	}
//	
//	@GetMapping("/byTitleAndYear")
//	@ResponseBody
//	public Set<Movie> movieByTitleAndYear(@RequestParam("t") String Title,
//									@RequestParam("y") int Year) {
//		return movieRepository.findByTitleAndYear(Title,Year);
//	}
//
//	@GetMapping("/byYearBetween")
//	@ResponseBody
//	public Set<Movie> movieByYearBetween(@RequestParam("y1") int Year,
//									@RequestParam("y2") int Year2) {
//		return movieRepository.findByYearBetween(Year, Year2);
//	}
	
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<Movie> movieByDirector(@RequestParam("d") String name) {
		return movieService.getMovieByDirector(name);
	}
//	
//	@GetMapping("/byDirectorId")
//	 @ResponseBody
//	public Set<Movie> findByDirector(@RequestParam("d") int idDirector) {
//	    var directOpt =personRepository.findById(idDirector); 
//		return directOpt.map(d-> movieRepository.findByDirector(d))
//				.orElseGet( () -> Collections.emptySet());    		 		
//	}

	
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
	
	
	////////////////////////Post//////////////////////////

	
//	@PostMapping
//	@ResponseBody
//	public Movie addMovie (@RequestBody Movie movie) {
//		Movie movieSaved = movieRepository.save(movie);
//		movieRepository.flush();
//		return movieSaved;
//	}
//	
//	////////////////////////Put/////////////////////////
//	
//	
//	@PutMapping("/modify")
//	@ResponseBody
//	public Optional<Movie> modifyMovie (@RequestBody Movie movie) {
//		//TODO : anywhere else	
//		var optMovie = movieRepository.findById(movie.getIdMovie());
//	
//		optMovie.ifPresent(m ->  {
//				m.setTitle(movie.getTitle());
//				m.setYear(movie.getYear());
//				m.setDuration(movie.getDuration());
//				m.setDirector(movie.getDirector());
//		});	
//		movieRepository.flush();
//		//
//		return optMovie;
//	}
//	
//	
//	
//	
//	@PutMapping("/addActor") 			//post aurait marché
//	public Optional<Movie> addActor (@RequestParam("a") int idActor,
//						@RequestParam ("m") int idMovie) {
//		//TODO : anywhere else 
//		var movieOpt = movieRepository.findById(idMovie);
//		var actorOpt = personRepository.findById(idActor);
//		if (movieOpt.isPresent() && actorOpt.isPresent()) {
//			movieOpt.get().getActors().add(actorOpt.get());
//			movieRepository.flush();
//		}
//		return movieOpt;
//	}
//	
//	@PutMapping("/setDirector")
//	public Optional<Movie> setDirector (@RequestParam("d") int idDirector, 
//						@RequestParam("m") int idMovie) {
//		var movieOpt = movieRepository.findById(idMovie);
//		var directorOpt = personRepository.findById(idDirector);
//		if (movieOpt.isPresent() && directorOpt.isPresent()) {
//			// movieOpt.get().getDirector().add(directorOpt.get());
//			movieOpt.get().setDirector(directorOpt.get());
//			movieRepository.flush();
//		}
//		return movieOpt;
//	}
//	
//	
//	////////////////////////Delete////////////////////////
//	
//	@DeleteMapping("/{id}")
//	@ResponseBody
//	public Optional<Movie> deleteMovie (@PathVariable ("id") int idMovie) {
//		var movieToDelete = movieRepository.findById(idMovie);
//		movieToDelete.ifPresent(m -> {
//		movieRepository.delete(m);
//		movieRepository.flush();
//		});
//		return movieToDelete;	
//	}
//	
//	
	
}

