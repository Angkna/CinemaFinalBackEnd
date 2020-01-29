package cinema.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Act;
import cinema.persistence.entity.ActId;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface ActRepository extends JpaRepository<Act, ActId>{
	Optional<Act> findByMovieAndPerson (Movie movie, Person person);
}
