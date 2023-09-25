package com.nhutin.electric_project.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.repository.categorysRepository;


@RestController
public class UserRestController {
    @Autowired
    categorysRepository dmdao;

    @GetMapping("/rest/danhmuc")
	public ResponseEntity<List<Category>> findAll() {

		return ResponseEntity.ok(dmdao.findAll());
	}

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
