package com.minxc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minxc.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUserName(String userName);
}
