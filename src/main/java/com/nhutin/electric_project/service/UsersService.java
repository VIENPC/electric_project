package com.nhutin.electric_project.service;

import java.util.List;

import com.nhutin.electric_project.model.User;

public interface UsersService {
List<User> findAll();
	
	List<User> findAllVer2();
	
	List<User> findLikeName(String name);

	User findById(Integer UserID);

	User create(User u);

	User update(User u);

	void delete(Integer UserID);

}
