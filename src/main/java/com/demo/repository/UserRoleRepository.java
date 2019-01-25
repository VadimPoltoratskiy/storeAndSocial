package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	
	@Query("select a.role from UserRole a, User b where b.userName=?1 and a.user.userId=b.userId")
    public List<String> findRoleByUserName(String username);
	
}


