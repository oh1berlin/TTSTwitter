package com.tts.TechTalentTwitter.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tts.TechTalentTwitter.model.Role;
import com.tts.TechTalentTwitter.model.User;
import com.tts.TechTalentTwitter.repository.RoleRepository;
import com.tts.TechTalentTwitter.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
@Autowired
public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
this.userRepository=userRepository;
this.roleRepository=roleRepository;
this.bCryptPasswordEncoder=bCryptPasswordEncoder;


	
}
public User findByUsername (String username) {
	return userRepository.findByUserName(username);
}

public List<User> findAll(){
	
	return (List<User>) userRepository.findAll();
}

public void save(User user) {
	userRepository.save(user);
}

public User saveNewUser(User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	user.setActive(1);
	Role userRole=roleRepository.findByRole("USER");
	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	return userRepository.save(user);
	
}
}
