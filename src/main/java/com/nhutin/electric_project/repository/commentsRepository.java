package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Comment;

public interface commentsRepository extends JpaRepository<Comment, Integer>{

}
