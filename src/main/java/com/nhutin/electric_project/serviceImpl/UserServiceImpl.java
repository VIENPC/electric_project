package com.nhutin.electric_project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {
	@Autowired
	UserRepository uDAO;

	@Override
	public List<User> findAll() {
		return uDAO.findAll();
	}

	@Override
	public User findById(Integer UserID) {
		return uDAO.findById(UserID).get();
	}

	@Override
	public User create(User User) {
		return uDAO.save(User);
	}

	@Override
	public User update(User User) {
		return uDAO.save(User);
	}

	@Override
	public void delete(Integer UserID) {
		uDAO.deleteById(UserID);

	}

	@Override
	public List<User> findAllVer2() {
		return uDAO.finAllVer2();
	}

	@Override
	public List<User> findLikeName(String name) {
		return uDAO.finAllLikeName(name);
	}
}
