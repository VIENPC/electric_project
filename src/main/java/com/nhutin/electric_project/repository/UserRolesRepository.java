package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.UserRole;

public interface UserRolesRepository extends JpaRepository<UserRole, Integer> {

}
