package cinema.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cinema.service.IUserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	@Autowired
	IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var exist = userService.getAllUser().stream().map(user -> user.getUserName()).filter(name -> name.equals(username)).findFirst().isPresent();
		if ( exist ) { 
			return new User(
					username,
					userService.getByUserName(username).get().getPassword(), 
					new ArrayList<>()
					);
		}
		else {
			throw new UsernameNotFoundException("User not found with this username");
		}
	}

}
