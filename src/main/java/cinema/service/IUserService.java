package cinema.service;

import java.util.List;

import cinema.persistence.entity.User;

public interface IUserService {
	List<User> getAllUser();
	User addUser(User user);
	User getByUserName(String username);
	User getByToken(String jwtToken);
}
