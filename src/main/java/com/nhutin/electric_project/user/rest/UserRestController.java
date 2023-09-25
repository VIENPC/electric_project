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

	
}
