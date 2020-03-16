package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.UserDto;

public interface IUserService {
	List<UserDto> getAllUser();
	Optional<UserDto> getByUserName(String username);
	Optional<UserDto> getByToken(String jwtToken);
	Set<UserDto> getByMovieLiked(int idMovie);
	
	UserDto addUser(UserDto user);
	
	Optional<UserDto> modifyUser(UserDto user);
	Optional<UserDto> addLikedMovieToUser(String userName, int idMovie);
	Optional<UserDto> deleteLikedMovieToUser(String userName, int idMovie);
}
