package com.nhutin.electric_project.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;

@Controller
@RequestMapping("admin")
public class KhachHangController {
    @Autowired
    UserRepository khdao;

    @RequestMapping("/qlkhachhang")
    public String qlkhachhang(Model model) {

        List<User> khlist = khdao.findAll();
        model.addAttribute("items", khlist);
        return "admin/view/qlcustomer";
    }

    @RequestMapping("/qlkhachhang/editt/{user}")
    public String edittkh(@PathVariable("user") String user) {
        User kh = khdao.findByUser(user);
        kh.setLockStatus(true);
        khdao.save(kh);
        return "redirect:/admin/qlkhachhang?success=updatesp";
    }

    @RequestMapping("/qlkhachhang/unclock/{user}")
    public String unclockh(@PathVariable("user") String user) {
        User kh = khdao.findByUser(user);
        kh.setLockStatus(false);
        khdao.save(kh);
        return "redirect:/admin/qlkhachhang?success=updatesp";
    }
}
