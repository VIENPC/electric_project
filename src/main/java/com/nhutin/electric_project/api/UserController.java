package com.nhutin.electric_project.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.config.CookieUtils;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;

@CrossOrigin("*")
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	CookieUtils cookieUtils;
	@Autowired
	HttpServletResponse resp;

	@GetMapping("/rest/users")
	public ResponseEntity<List<User>> getAll(Model model) {
		return ResponseEntity.ok(userRepo.findAll());
	}

	@GetMapping("/rest/users/{id}")
	public ResponseEntity<Optional<User>> getOne(@PathVariable("id") int id) {
		if (!userRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userRepo.findById(id));
	}

	// Insert
	@PostMapping("/rest/users")
	public ResponseEntity<User> post(@RequestBody User user) {
		if (userRepo.existsById(user.getUserID())) {
			return ResponseEntity.badRequest().build();
		}
		userRepo.save(user);
		return ResponseEntity.ok(user);
	}

	// Update
	@PutMapping("/rest/users/{id}")
	public ResponseEntity<User> put(@PathVariable("id") int id, @RequestBody User user) {
		if (!userRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		userRepo.save(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/rest/users/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		if (!userRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		userRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public User getNguoiDung() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return userRepo.findByEmail(auth.getName()).get();
	}

	@GetMapping("/api/account/login")
	public ResponseEntity<User> findUserLogin() {
		return ResponseEntity.ok(getNguoiDung());
	}
}
