package ca.moodyjay.audio.security;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.moodyjay.audio.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// find a user based on the username
		ca.moodyjay.audio.beans.User user = userRepo.findByUsername(username);
		
		// if the user cannot be found
		if (user == null) {
			System.out.println("User not found: " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		// change the list of the user's roles into a list of GrantedAuthority
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		grantList.add(new SimpleGrantedAuthority(user.getRole().getRolename()));
		
		// create a Spring user based on the information read
		UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), grantList);
		
		return userDetails;
	}

}
