package cinema.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.ActDto;
import cinema.service.IActService;

@RestController
@RequestMapping("/api/act")
public class ActController {

	@Autowired
	IActService actService;
	
	@CrossOrigin
	@GetMapping("/idMovie{idmovie}")
	@ResponseBody
	public Set<ActDto> getByMovieIdMovie(@PathVariable("idmovie")int idMovie) {
		return actService.getByMovieIdMovie(idMovie);
	}
	
}
