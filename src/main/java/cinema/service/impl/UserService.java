package cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.User;
import cinema.persistence.repository.UserRepository;
import cinema.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService{

	@Autowired
	UserRepository userRepository;
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public User addUser(User user) {
		String temp = user.getPassword();
		user.setPassword(passwordEncoder.encode(temp));
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getByUserName(String username) {
		return userRepository.findByUserNameIgnoreCase(username);
	}

}
