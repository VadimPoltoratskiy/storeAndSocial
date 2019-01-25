package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Profile;
import com.demo.entity.User;
import com.demo.entity.Worker;
import com.demo.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	ProfileRepository profileRepository;	
	
	@Transactional
	public void save(Profile profile) {
		profileRepository.save(profile);
	}
	
	@Transactional
	public Profile getUserProfile(User user) {
		return profileRepository.findByUser(user);
	}
	
	//31.05.2018
	/*@Transactional
	public List<Profile> showAllProfiles() {		
		return (List<Profile>) profileRepository.findAll();
	}*/
	
		
}
