package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.UserDto;

public interface IUserService {
	List<UserDto> getAllUser();
	UserDto addUser(UserDto user);
	Optional<UserDto> getByUserName(String username);
	Optional<UserDto> getByToken(String jwtToken);
	Optional<UserDto> modifyUser(UserDto user);
	Set<UserDto> getByMovieLiked(int idMovie);
}
