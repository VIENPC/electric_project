package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
