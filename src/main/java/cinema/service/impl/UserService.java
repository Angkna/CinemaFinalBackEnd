package cinema.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.config.JwtTokenUtil;
import cinema.dto.UserDto;
import cinema.persistence.entity.User;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.UserRepository;
import cinema.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired 
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	ModelMapper mapper;
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public List<UserDto> getAllUser() {
		return userRepository.findAll().stream()
				.map(u -> mapper.map(u, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<UserDto> getByUserName(String username) {
		return userRepository.findByUserNameIgnoreCase(username).map(u -> mapper.map(u, UserDto.class));
	}

	@Override
	public Optional<UserDto> getByToken(String jwtToken) {
		String username = jwtTokenUtil.getUserNameFromToken(jwtToken);
		return getByUserName(username);
	}
	
	@Override
	public Set<UserDto> getByMovieLiked(int idMovie) {
		//movieRepository.findById(idMovie).get().getUsersWhoLike();
		return userRepository.findByMovieLiked(idMovie).stream()
				.map(u -> mapper.map(u, UserDto.class))
				.collect(Collectors.toCollection(HashSet::new));
	}
	
	@Override
	public UserDto addUser(UserDto user) {	
		User u = mapper.map(user, User.class);
		String temp = u.getPassword();
		u.setPassword(passwordEncoder.encode(temp));
		user.setPassword(passwordEncoder.encode(temp));
		userRepository.saveAndFlush(u);
		mapper.map(u, user);
		return user;
	}

	@Override
	public Optional<UserDto> modifyUser(UserDto user) {
		String temp = user.getPassword();
		var userM = userRepository.findById(user.getIdUser());
		userM.ifPresent(u ->  {
				u.setFirstName(user.getFirstName());
				u.setLastName(user.getLastName());
				u.setUserName(user.getUserName());
				u.setPassword(passwordEncoder.encode(temp));
				u.setEmail(user.getEmail());		
		});
		userRepository.flush();
		return userM.map(u -> mapper.map(u, UserDto.class));
	}
	
	@Override
	public Optional<UserDto> addLikedMovieToUser(String userName, int idMovie) {
		var userObs = userRepository.findByUserNameIgnoreCase(userName);
		var movieObs = movieRepository.findById(idMovie);
		userObs.ifPresent(u -> { 
			movieObs.ifPresent( m -> {
				u.addMovieLiked(m);
			});
		});
		userRepository.flush();
		return userObs.map(u -> mapper.map(u, UserDto.class));
	}
	
	@Override
	public Optional<UserDto> deleteLikedMovieToUser(String userName, int idMovie) {
		var userObs = userRepository.findByUserNameIgnoreCase(userName);
		var movieObs = movieRepository.findById(idMovie);
		userObs.ifPresent(u -> { 
			movieObs.ifPresent( m -> {
				u.deleteMovieLiked(m);
			});
		});
		userRepository.flush();
		return userObs.map(u -> mapper.map(u, UserDto.class));
	}



}
