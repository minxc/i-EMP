package com.minxc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minxc.core.entity.User;


public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUserName(String userName);
}
