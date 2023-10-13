package com.nhutin.electric_project.user.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.Promotion;
import com.nhutin.electric_project.repository.categorysRepository;
import com.nhutin.electric_project.repository.prmotionRepositpry;
import com.nhutin.electric_project.service.CategorysService;

@CrossOrigin("*")
@RestController
public class CategoryRestController {
	@Autowired
	categorysRepository dmdao;

	@Autowired
	CategorysService categoryService;

	@Autowired
	prmotionRepositpry promoDao;

	@GetMapping("/rest/category")
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(dmdao.findAll());
	}

	@GetMapping("/rest/promotion")
	public ResponseEntity<List<Promotion>> findAll1() {
		return ResponseEntity.ok(promoDao.findAll());
	}

	@GetMapping("/rest/{itemId}")
	public ResponseEntity<Optional<Promotion>> getOne(@PathVariable("itemId") Integer itemId) {
		return ResponseEntity.ok(promoDao.findById(itemId));
	}
}
