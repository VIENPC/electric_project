package com.nhutin.electric_project.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.repository.categorysRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    categorysRepository categorysRepository;

    @GetMapping("/category")
    public List<Category> getCategories() {
        return categorysRepository.findAll();
    }
}
