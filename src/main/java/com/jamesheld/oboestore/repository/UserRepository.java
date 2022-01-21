package com.jamesheld.oboestore.repository;

import org.springframework.stereotype.Repository;

import com.jamesheld.oboestore.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// inherits various methods from JpaRepository
	User findByEmail(String email);
}
