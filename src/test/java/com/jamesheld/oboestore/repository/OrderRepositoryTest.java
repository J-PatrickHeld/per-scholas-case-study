package com.jamesheld.oboestore.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;

import com.jamesheld.oboestore.entity.Order;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestPropertySource(locations = "classpath:test.properties") // put file in src/test/java, so as not to conflict with application.properties
public class OrderRepositoryTest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	// @Rollback(false) //don't rollback the transaction if we want to check that it was persisted to database
	public void testCreateOrder() {
		Order order = new Order("James", "Held", "james@james.com", "1234 good st", "milwaukee", "WI", 53180, 5);
		orderRepository.save(order);
		
		assertNotNull(order);
		assertTrue(order.getId() > 0);
	}
}


