package com.nhutin.electric_project.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nhutin.electric_project.repository.categorysRepository;

@Controller
public class CategoryController {
    @Autowired
    categorysRepository categorysRepository;

}
