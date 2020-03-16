package cinema.persistence.repository;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cinema.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserNameIgnoreCase(String username);
	
	//test Like
	@Query("SELECT u FROM User u JOIN u.movieLiked m WHERE m.idMovie = ?1")
	Set<User> findByMovieLiked (int idMovie);

}
