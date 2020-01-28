package cinema.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
	
	Optional<Genre> findByGenre(String name);

}
