package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Category;

public interface categorysRepository extends JpaRepository<Category, Integer>{

}
