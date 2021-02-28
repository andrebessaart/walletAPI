package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository repository;
	
	@BeforeEach
	public void setUp() {
		
		User user = new User();
		user.setName("Set up User");
		user.setPassword("senha123");
		user.setEmail("setup@email.com");
		

		repository.save(user);
		
		System.out.print("passou aquii ---------------------------");
		
	}
	
	@AfterEach
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void testSave() {
		
		User user = new User();
		user.setName("teste");
		user.setEmail("teste@teste");
		user.setPassword("123456");
		
		User response = repository.save(user);
		
		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals("setup@email.com");
		
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), "setup@email.com");
		
		System.out.print("passou aquii ------------------------32222222222222---");
	}
	
	

}
