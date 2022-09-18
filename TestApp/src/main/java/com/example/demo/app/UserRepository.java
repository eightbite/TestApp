package com.example.demo.app;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public AuthenticatedUser identifyUser(String email);
	
	public int registerUser(AuthenticatedUser user);
}