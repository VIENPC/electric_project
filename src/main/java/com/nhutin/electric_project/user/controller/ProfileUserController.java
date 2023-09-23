package com.nhutin.electric_project.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;

@Controller
public class ProfileUserController {

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRepository userDAO;

    

    @RequestMapping("/user/profile")
    public String getProfileUser(Model model) {

        String email = (String) session.getAttribute("tenDangNhapLogin");
        System.out.println("da lay duoc session : " + email);
        if (email != null) {

            User user = userDAO.findByEmailLike(email);
            model.addAttribute("ThongTinTK", user);
            // System.out.println(user);
        }
        return "user/profile";
    }

    @PostMapping("/user/profile/update")
    public String updateInfor(@ModelAttribute("ThongTinTK") User user) {
    if (user != null) {
    String email = (String) session.getAttribute("tenDangNhapLogin");
    System.out.println("update: "+email);
    User user2 = userDAO.findByEmailLike(email);
    user.setUserID(user2.getUserID());
    user.setUsername(user2.getUsername());
    user.setPassword(user2.getPassword());
    user.setEmail(user2.getEmail());
    user.setRole(user2.getRole());
    user.setLoginPermission(user2.getLoginPermission());
    user.setRegistrationDate(user2.getRegistrationDate());
    user.setLockStatus(user2.getLockStatus());
    userDAO.save(user);
    }
    return "redirect:/user/profile";
    }
}