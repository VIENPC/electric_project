package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.New;

public interface newsRepository extends JpaRepository<New, Integer>  {
 
}
