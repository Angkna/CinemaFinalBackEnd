package cinema.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.dto.ActDto;
import cinema.persistence.repository.ActRepository;
import cinema.service.IActService;

@Service
@Transactional
public class ActService implements IActService {
	
	@Autowired
	ModelMapper mapper;
	@Autowired
	ActRepository actRepository;

	@Override
	public Set<ActDto> getByMovieIdMovie(int idMovie) {
		return actRepository.findByMovieIdMovie(idMovie).stream()
				.map(m -> mapper.map(m, ActDto.class))
				.collect(Collectors.toCollection(HashSet::new));
	}

}
