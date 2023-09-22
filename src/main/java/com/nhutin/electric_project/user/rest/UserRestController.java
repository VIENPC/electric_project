package com.nhutin.electric_project.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.service.UsersService;

public class UserRestController {
	@Autowired
	UsersService userService;
	
	@Autowired
	UserRepository udao;
	
	@GetMapping("/rest/Users/{UserID}")
	public User getOne(@PathVariable("UserID") Integer UserID) {
		return userService.findById(UserID);
	}
	
	@GetMapping("/rest/Users/loadall")
	public List<User> getAll() {
		return userService.findAllVer2();
	}
	
	@GetMapping("/rest/Users/search")
	public List<User> getUserLikeName(@PathVariable("keyword") String name) {
		return userService.findLikeName(name);
	}

	@PostMapping("/rest/Users/create")
	public User create(@RequestBody User User) {
		return userService.create(User);
	}

	@PutMapping("/rest/Users/update/{UserID}")
	public void update(@PathVariable("UserID") Integer UserID, @RequestBody User user) {

		userService.update(user);
	}

	@DeleteMapping("/rest/Users/delete/{UserID}")
	public void delete(@PathVariable("UserID") Integer UserID) {
		userService.delete(UserID);
	}
}
