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
import cinema.dto.MovieLight;
import cinema.exception.MovieNotFoundException;
import cinema.persistence.entity.Audiance;

import cinema.persistence.entity.Person;
import cinema.service.IMovieService;

@RestController //REpresentational State Transfert (architecheture de mapping)
@RequestMapping("/api/movie")
public class MovieController {
	
	@Autowired
	IMovieService movieService;
	
	@CrossOrigin
	@GetMapping
	@ResponseBody
	public List<MovieLight> allmovies() {
		return movieService.getAllMovies();
		
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<MovieFull> movieById(@PathVariable("id")int idMovie) {
		Optional<MovieFull> movieFull = movieService.getMovieById(idMovie);
		if (movieFull.isPresent()) {
			return movieFull;
		} throw new MovieNotFoundException();
	}
	
	@CrossOrigin
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<MovieLight> movieByTitle(@RequestParam("t") String title) {
		return movieService.getMovieByTitle(title);
	}
	
	@CrossOrigin
	@GetMapping("/byTitleContaining")
	@ResponseBody
	public Set<MovieLight> movieByTitleContaining(@RequestParam("t") String title) {
		return movieService.getMovieByTitleContainingIgnoreCase(title);
	}
	
	@CrossOrigin
	@GetMapping("/byTitleAndYear")
	@ResponseBody
	public Set<MovieLight> movieByTitleAndYear(@RequestParam("t") String title,
									@RequestParam("y") int year) {
		return movieService.getMovieByTitleAndYear(title, year);
	}
	
	@CrossOrigin
	@GetMapping("/byTitleContainingAndYear")
	@ResponseBody
	public Set<MovieLight> movieByTitleContainingIgnoreCaseAndYear(@RequestParam("t") String title,
									@RequestParam("y") int year) {
		return movieService.getMovieByTitleContainingIgnoreCaseAndYear(title, year);
	}
	
	@CrossOrigin
	@GetMapping("/byYear")
	@ResponseBody
	public Set<MovieLight> movieByYear(@RequestParam("y1") int year){
		 return movieService.getMovieByYear(year);
	}
	
	@GetMapping("/byYearLessThan")
	@ResponseBody
	public Set<MovieLight> movieByYearLessThan(@RequestParam("y1") int year){
		 return movieService.getMovieByYearLessThan(year);
	}
	
	@GetMapping("/byYearGreaterThan")
	@ResponseBody
	public Set<MovieLight> movieByYearGreaterThan(@RequestParam("y1") int year){
		 return movieService.getMovieByYearGreaterThan(year);
	}
	
	@GetMapping("/byYearBetween")
	@ResponseBody
	public Set<MovieLight> movieByYearBetween(@RequestParam("y1") int year,
									@RequestParam("y2") int year2) {
		 return movieService.getMovieByYearBetween(year, year2);
	}
	
	@GetMapping("/byTitleAndYearAndDuration")
	@ResponseBody
	public Set<MovieLight> movieByTitleAndYearAndDuration(@RequestParam("t") String title,
									@RequestParam("y") int year,
									@RequestParam("d") int duration) {
		return movieService.getMovieByYearAndTitleAndDuration(year, title, duration);
	}
	
	@GetMapping("/byDurationGreaterThan")
	@ResponseBody
	public Set<MovieLight> movieByDurationGreaterThan(@RequestParam("d1") int duration){
		 return movieService.getMovieByDurationGreaterThan(duration);
	}
	
	@GetMapping("/byDurationBetween")
	@ResponseBody
	public Set<MovieLight> movieByDurationBetween(@RequestParam("d1") int duration1,
									@RequestParam("d2") int duration2) {
		 return movieService.getMovieByDurationBetween(duration1, duration2);
	}
	
	@GetMapping("/byDurationLessThanEqual")
	@ResponseBody
	public Set<MovieLight> movieByDurationLessThanEqual(@RequestParam("d1") int duration){
		 return movieService.getMovieByDurationLessThanEqual(duration);
	}
	
	
	@CrossOrigin
	@GetMapping("/likedByIdUser")
	@ResponseBody
	public Set<MovieLight> getLikedMovies(@RequestParam("id") int idUser){
		 return movieService.getLikedMovies(idUser);
	}
	
	
	//A tester /////////////////////////////////////////////////////
	@GetMapping("/byGenre")
	@ResponseBody
	public Set<MovieLight> movieByGenresGenreIgnoreCase(@RequestParam("g") String genre){
		 return movieService.getMovieByGenresIgnoreCase(genre);
	}
	
	@GetMapping("/byRatingGreaterThanEqual")
	@ResponseBody
	public Set<MovieLight> movieByRatingGreaterThanEqual(@RequestParam("r") double rating){
		 return movieService.getMovieByRatingGreaterThanEqual(rating);
	}
	
	@GetMapping("/bySynopsisContaining")
	@ResponseBody
	public Set<MovieLight> movieBySynopsisContaining(@RequestParam("r") String recherche) {
		return movieService.getMovieBySynopsisContaining(recherche);
	}
	
	@GetMapping("/byAudiance")
	@ResponseBody
	public Set<MovieLight> movieByAudiance(@RequestParam("a") Audiance audiance) {
		return movieService.getMovieByAudiance(audiance);
	}
	
	
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<MovieLight> movieByDirector(@RequestBody Person person) {
		return movieService.getMovieByDirector(person);
	}
	
	@GetMapping("/byDirectorName")
	@ResponseBody
	public Set<MovieLight> movieByDirectorName(@RequestParam("d") String name) {
		return movieService.getMovieByDirectorName(name);
	}
	
	@GetMapping("/byDirectorNameEndingWith")
	@ResponseBody
	public Set<MovieLight> movieByDirectorNameEndingWith(@RequestParam("ne") String partialName) {
		return movieService.getMovieByDirectorNameEndingWith(partialName);
	}
	
	@GetMapping("/byDirectorId")
	 @ResponseBody
	public Set<MovieLight> findByDirectorId(@RequestParam("d") int idDirector) {
		return movieService.getMovieByDirectorId(idDirector);		
	}

	

//	@GetMapping("/byActorName")
//	@ResponseBody
//	public Set<MovieLight> movieByActorsName(@RequestParam("a") String name) {
//		return movieService.getMovieByActorsName(name);
//	}
//	
//	@GetMapping("/byActorId")
//	@ResponseBody
//	public Set<MovieLight> moviebyActorsIdPerson(@RequestParam("a") int idActor) {
//		return movieService.getMovieByActorsIdPerson(idActor);				
//	}
//	
//	@GetMapping("/byActorNameEndingWith")
//	@ResponseBody
//	public Set<MovieLight> movieByActorsNameEndingWith(@RequestParam("a") String partialName) {
//		return movieService.getMovieByActorsNameEndingWith(partialName);
//	}

	
	/**
	 * Method: Post
	 */
	
	////////////////////////Post//////////////////////////

	@CrossOrigin
	@PostMapping
	@ResponseBody
	public MovieFull addMovie (@RequestBody MovieFull movie) {
		return movieService.addMovie(movie);
	}
	
	////////////////////////Put/////////////////////////
	
	@CrossOrigin
	@PutMapping("/modify")
	@ResponseBody
	public Optional<MovieFull> modifyMovie (@RequestBody MovieFull movie) {
		return movieService.modifyMovie(movie);
	}
	
	@PutMapping("/addActor") 			//post aurait marché
	public Optional<MovieFull> addActor (@RequestParam("a") int idActor, @RequestParam ("m") int idMovie) {
		return movieService.addActor(idActor, idMovie);
	}
	
	@PutMapping("/setDirector")
	public Optional<MovieFull> setDirector (@RequestParam("d") int idDirector, 
						@RequestParam("m") int idMovie) {
		return movieService.setDirector(idDirector, idMovie);
	}
	
//	@PostMapping ("/addNewGenre")
//	public Genre addGenre(@RequestParam("n") String genre){
//		return movieService.addGenre(genre);	
//	}
//	
//	@PutMapping ("/addGenreToMovie")
//	public Optional<MovieFull> addGenreToMovie(@RequestParam("g") String genre, @RequestParam("m") int idMovie) {
//	return movieService.addGenreToMovie(genre, idMovie);
//	}
	
	
	////////////////////////Delete////////////////////////
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<MovieFull> deleteMovie (@PathVariable ("id") int idMovie) {
		return movieService.deleteMovie(idMovie);	
	}
	
	
	
}

