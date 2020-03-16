package cinema.persistence.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Act;
import cinema.persistence.entity.ActId;

public interface ActRepository extends JpaRepository<Act, ActId>{
	Optional<Act> findByMovieIdMovieAndPersonIdPerson (int idMovie, int idPerson);
	Set<Act> findByMovieIdMovie (int idMovie);
}
