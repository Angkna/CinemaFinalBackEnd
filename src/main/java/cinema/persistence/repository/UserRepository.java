package cinema.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUserNameIgnoreCase(String username);

}
