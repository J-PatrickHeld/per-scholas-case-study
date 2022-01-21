package com.jamesheld.oboestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesheld.oboestore.entity.Order;

//@Repository //annotation already exists in SimpleJpaRepository, don't need to recreate here
public interface OrderRepository extends JpaRepository<Order, Long> {
	// inherits various methods from JpaRepository
	List<Order> findByEmail(String email);
	
}
