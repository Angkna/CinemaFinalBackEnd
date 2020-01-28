package cinema.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;

public interface NationalityRepository extends JpaRepository<Nationality, Integer> { 

	Optional<Nationality> findByNationality(String nationality);
}
