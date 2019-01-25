package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Profile;
import com.demo.entity.User;
import com.demo.entity.Worker;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {	
	Profile findByUser(User user); // нужно передавать айди, а не юзера
	
	
}
