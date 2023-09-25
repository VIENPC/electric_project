/*
 * package com.nhutin.electric_project.serviceImpl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.stereotype.Service;
 * 
 * import com.nhutin.electric_project.model.User; import
 * com.nhutin.electric_project.repository.UserRepository;
 * 
 * @Service public class ServiceImpl implements Service {
 * 
 * @Autowired UserRepository userDAO;
 * 
 * @Override public User getUser() { Authentication auth =
 * SecurityContextHolder.getContext().getAuthentication(); return
 * userDAO.findByEmail((auth.getName())).get(); }
 * 
 * }
 */