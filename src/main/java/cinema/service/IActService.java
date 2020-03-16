package cinema.service;

import java.util.Set;

import cinema.dto.ActDto;

public interface IActService {
	Set<ActDto> getByMovieIdMovie (int idMovie);

}
