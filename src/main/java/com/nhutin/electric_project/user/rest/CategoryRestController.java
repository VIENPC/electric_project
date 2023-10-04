package com.nhutin.electric_project.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.repository.categorysRepository;
import com.nhutin.electric_project.service.CategorysService;

@CrossOrigin("*")
@RestController
public class CategoryRestController {
	@Autowired
	categorysRepository dmdao;

	@Autowired
	CategorysService categoryService;

	@GetMapping("/rest/category")
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(dmdao.findAll());
	}

}
