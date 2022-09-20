package com.example.demo.app;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public AuthenticatedUser identifyUser(String email);
	
	public int registerUser(AuthenticatedUser user);
	
	public List<AuthenticatedUser> userFindAll();
}