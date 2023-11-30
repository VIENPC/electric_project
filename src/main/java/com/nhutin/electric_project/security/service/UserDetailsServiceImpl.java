package com.nhutin.electric_project.security.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhutin.electric_project.model.ConfirmationCode;
import com.nhutin.electric_project.model.Role;
import com.nhutin.electric_project.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userDAO;

	@Autowired
	ConfirmationTokenService confirmationTokenService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			com.nhutin.electric_project.model.User user = userDAO.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

			String role = user.getRole().toString();
			session.setAttribute("tenDangNhapLogin", user.getEmail());
			
			// Sử dụng User.builder() để tạo một đối tượng UserDetails
			return User.builder().username(username).password(user.getPassword()).roles(role)
					.disabled(!user.isEnabled()).accountLocked(!user.isAccountNonLocked()).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Database error", e);
		}
	}

	public String signUpUser(com.nhutin.electric_project.model.User user) {
		boolean userExist = userDAO.findByEmail(user.getEmail()).isPresent();
		userExist = userDAO.findByUsername(user.getUsername()).isPresent();
		userExist = userDAO.findByPhoneNumber(user.getPhoneNumber()).isPresent();

		if (userExist) {
			throw new IllegalStateException("User already exists.");
		}

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);
		user.setLoginPermission(false);
		user.setLockStatus(false);
		userDAO.save(user);

		String token = UUID.randomUUID().toString();
		// Lấy ngày giờ hiện tại
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		// Thêm 15 phút
		long millisecondsIn15Minutes = 15 * 60 * 1000; // 15 phút * 60 giây/phút * 1000 mili giây/giây
		Timestamp newTimestamp = new Timestamp(currentTimestamp.getTime() + millisecondsIn15Minutes);
		// Send confirmation token
		ConfirmationCode confirmationCode = new ConfirmationCode(user.getUserID(), false, token, currentTimestamp,
				newTimestamp);

		confirmationTokenService.saveConfirmationToken(confirmationCode);

		return token;
	}

	public int enableUser(String email) {
		return userDAO.enableUser(email, new Timestamp(System.currentTimeMillis()));
	}

	public UserDetails createUserAfterOAuthLogin(String email, String name, String phone) {
		com.nhutin.electric_project.model.User newUser = new com.nhutin.electric_project.model.User();

		// Lấy ngày giờ hiện tại
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		newUser.setUsername(email);
		String dummyPassword = "oauth2_dummy_password";
		newUser.setPassword(dummyPassword);
		newUser.setEmail(email);
		newUser.setFullName(name);
		newUser.setPhoneNumber(phone);
		newUser.setLoginPermission(true);
		newUser.setRegistrationDate(currentTimestamp);
		newUser.setLockStatus(false);
		newUser.setRole(Role.USER);

		userDAO.save(newUser);

		// Tạo một đối tượng UserDetails thích hợp
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(newUser.getUsername(),
				newUser.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(newUser.getRole().toString())));

		return userDetails;
	}

	public UserDetails updateUserAfterOAuthLogin(com.nhutin.electric_project.model.User user, String name) {
		// Lấy ngày giờ hiện tại
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		if (user.getRegistrationDate() == null) {
			user.setRegistrationDate(currentTimestamp);
		}
		// Tạo mật khẩu giả tạo, bạn có thể dùng một giá trị bất kỳ.
		String dummyPassword = "oauth2_dummy_password";
		user.setPassword(dummyPassword);
		user.setRole(Role.USER);
		user.setFullName(name);
		user.setLoginPermission(true);
		userDAO.save(user);

		// Tạo một đối tượng UserDetails thích hợp
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
		return userDetails;
	}

	public boolean userExists(String email) {
        return userDAO.findByEmail(email).isPresent();
    }
}
