package com.example.demo.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userFindAllService {

	@Autowired
	private UserRepository repository;
	
	public List<AuthenticatedUser> userFindAll() {
		
		return repository.userFindAll();
		
	}
	
}
