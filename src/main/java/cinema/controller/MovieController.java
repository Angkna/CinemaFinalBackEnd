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

import cinema.persistence.entity.Audiance;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
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
	public Set<Movie> movieByTitle(@RequestParam("t") String title) {
		return movieService.getMovieByTitle(title);
	}
	
	@GetMapping("/byTitleContaining")
	@ResponseBody
	public Set<Movie> movieByTitleContaining(@RequestParam("t") String title) {
		return movieService.getMovieByTitleContainingIgnoreCase(title);
	}
	
	@GetMapping("/byTitleAndYear")
	@ResponseBody
	public Set<Movie> movieByTitleAndYear(@RequestParam("t") String title,
									@RequestParam("y") int year) {
		return movieService.getMovieByTitleAndYear(title, year);
	}

	@GetMapping("/byYear")
	@ResponseBody
	public Set<Movie> movieByYear(@RequestParam("y1") int year){
		 return movieService.getMovieByYear(year);
	}
	
	@GetMapping("/byYearLessThan")
	@ResponseBody
	public Set<Movie> movieByYearLessThan(@RequestParam("y1") int year){
		 return movieService.getMovieByYearLessThan(year);
	}
	
	@GetMapping("/byYearGreaterThan")
	@ResponseBody
	public Set<Movie> movieByYearGreaterThan(@RequestParam("y1") int year){
		 return movieService.getMovieByYearGreaterThan(year);
	}
	
	@GetMapping("/byYearBetween")
	@ResponseBody
	public Set<Movie> movieByYearBetween(@RequestParam("y1") int year,
									@RequestParam("y2") int year2) {
		 return movieService.getMovieByYearBetween(year, year2);
	}
	
	@GetMapping("/byTitleAndYearAndDuration")
	@ResponseBody
	public Set<Movie> movieByTitleAndYearAndDuration(@RequestParam("t") String title,
									@RequestParam("y") int year,
									@RequestParam("d") int duration) {
		return movieService.getMovieByYearAndTitleAndDuration(year, title, duration);
	}
	
	@GetMapping("/byDurationGreaterThan")
	@ResponseBody
	public Set<Movie> movieByDurationGreaterThan(@RequestParam("d1") int duration){
		 return movieService.getMovieByDurationGreaterThan(duration);
	}
	
	@GetMapping("/byDurationBetween")
	@ResponseBody
	public Set<Movie> movieByDurationBetween(@RequestParam("d1") int duration1,
									@RequestParam("d2") int duration2) {
		 return movieService.getMovieByDurationBetween(duration1, duration2);
	}
	
	@GetMapping("/byDurationLessThanEqual")
	@ResponseBody
	public Set<Movie> movieByDurationLessThanEqual(@RequestParam("d1") int duration){
		 return movieService.getMovieByDurationLessThanEqual(duration);
	}
	
	//A tester /////////////////////////////////////////////////////
	@GetMapping("/byGenre")
	@ResponseBody
	public Set<Movie> movieByGenresGenreIgnoreCase(@RequestParam("g") String genre){
		 return movieService.getMovieByGenresGenreIgnoreCase(genre);
	}
	
	@GetMapping("/byRatingGreaterThanEqual")
	@ResponseBody
	public Set<Movie> movieByRatingGreaterThanEqual(@RequestParam("r") double rating){
		 return movieService.getMovieByRatingGreaterThanEqual(rating);
	}
	
	@GetMapping("/bySynopsisContaining")
	@ResponseBody
	public Set<Movie> movieBySynopsisContaining(@RequestParam("r") String recherche) {
		return movieService.getMovieBySynopsisContaining(recherche);
	}
	
	@GetMapping("/byAudiance")
	@ResponseBody
	public Set<Movie> movieByAudiance(@RequestParam("a") Audiance audiance) {
		return movieService.getMovieByAudiance(audiance);
	}
	
	
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<Movie> movieByDirector(@RequestBody Person person) {
		return movieService.getMovieByDirector(person);
	}
	
	@GetMapping("/byDirectorName")
	@ResponseBody
	public Set<Movie> movieByDirectorName(@RequestParam("d") String name) {
		return movieService.getMovieByDirectorName(name);
	}
	
	@GetMapping("/byDirectorNameEndingWith")
	@ResponseBody
	public Set<Movie> movieByDirectorNameEndingWith(@RequestParam("ne") String partialName) {
		return movieService.getMovieByDirectorNameEndingWith(partialName);
	}
	
	@GetMapping("/byDirectorId")
	 @ResponseBody
	public Set<Movie> findByDirectorId(@RequestParam("d") int idDirector) {
		return movieService.getMovieByDirectorId(idDirector);		
	}

	
	@GetMapping("/byActorName")
	@ResponseBody
	public Set<Movie> movieByActorsName(@RequestParam("a") String name) {
		return movieService.getMovieByActorsName(name);
	}
	
	@GetMapping("/byActorId")
	@ResponseBody
	public Set<Movie> moviebyActorsIdPerson(@RequestParam("a") int idActor) {
		return movieService.getMovieByActorsIdPerson(idActor);				
	}
	
	@GetMapping("/byActorNameEndingWith")
	@ResponseBody
	public Set<Movie> movieByActorsNameEndingWith(@RequestParam("a") String partialName) {
		return movieService.getMovieByActorsNameEndingWith(partialName);
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
	
	@PostMapping ("/addNewGenre")
	public Genre addGenre(@RequestParam("n") String genre){
		return movieService.addGenre(genre);	
	}
	
	@PutMapping ("/addGenreToMovie")
	public Optional<Movie> addGenreToMovie(@RequestParam("g") String genre, @RequestParam("m") int idMovie) {
	return movieService.addGenreToMovie(genre, idMovie);
}
	
	
	////////////////////////Delete////////////////////////
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<Movie> deleteMovie (@PathVariable ("id") int idMovie) {
		return movieService.deleteMovie(idMovie);	
	}
	
	
	
}

