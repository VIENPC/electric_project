package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Inventory;

public interface inventoryRepository extends JpaRepository<Inventory, Integer> {

}
