package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;
import com.demo.entity.UserRole;
import com.demo.repository.UserRepository;
import com.demo.repository.UserRoleRepository;
import com.demo.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }
	
	@Transactional
	public void register(User user) {		
		user.addRole(new UserRole("ROLE_USER", user));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user.setPassword(user.getPassword());
		userRepository.save(user);
	}
	
        
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		
		User user = userRepository.findByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		} else {
			List<String> userRoles = userRoleRepository.findRoleByUserName(username);
			return new CustomUserDetails(user,userRoles);
		}
	}
	
	@Transactional
	public User get(String name) {
		return userRepository.findByUserName(name);
	}

	public User get(Long id) {
		
		return userRepository.findById(id).orElseThrow(null);
	} 
	
	@Transactional
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}
		
}
