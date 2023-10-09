package com.nhutin.electric_project.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.Promotion;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.ordersRepository;

import jakarta.jws.soap.SOAPBinding.Use;

@Controller
@RequestMapping("admin")
public class KhachHangController {
    @Autowired
    UserRepository khdao;

    @Autowired
    ordersRepository orderDao;

    @RequestMapping("/qlkhachhang")
    public String qlkhachhang(Model model) {

        List<User> khlist = khdao.findAll();

        for (User comment : khlist) {
            System.out.println("Comment ID: " + comment.getUserID());
            System.out.println("-----");
        }
        model.addAttribute("items", khlist);
        return "admin/view/qlcustomer";
    }

    @RequestMapping("/qlkhachhang/{user}")
    public String edittkh(@PathVariable("user") Integer user, Model model) {
        User kh = khdao.findById(user).get();
        model.addAttribute("items", kh);
        return "admin/view/editUser";
    }

    @RequestMapping("/historyOrder/{user}")
    public String history(@PathVariable("user") Integer user, Model model) {
        List<Order> order = orderDao.findHistory(user);
        model.addAttribute("listhd", order);
        return "admin/view/qloder";
    }

    @RequestMapping("/qlkhachhang/unclock/{user}")
    public String unclockh(@PathVariable("user") Integer user) {
        User kh = khdao.findById(user).get();
        // kh.setTrangthaikh(1);
        khdao.save(kh);
        return "redirect:/admin/qlkhachhang?success=updatesp";
    }

    @RequestMapping(value = "/qlkhachhang/delete/{user}")
    public String deletesp(@PathVariable("user") Integer user, RedirectAttributes redirectAttributes) {
        try {
            // Gọi service hoặc repository để xóa sản phẩm dựa vào id
            khdao.deleteById(user);
            // Chuyển hướng tới trang hiển thị thông báo xóa thành công
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa sản phẩm thành công!");
            return "redirect:/admin/qlkhachhang"; // Chuyển hướng đến /qlkhachhang
        } catch (Exception e) {
            // Chuyển hướng tới trang hiển thị thông báo xóa không thành công
            redirectAttributes.addFlashAttribute("errorMessage", "Xóa sản phẩm không thành công: " + e.getMessage());
            return "redirect:/admin/qlkhachhang";
        }

    }

    @PostMapping("/qlkhachhang/updata/{user}")
    public String update(@PathVariable("user") Integer user, @Valid User items, BindingResult result, Model model) {
        User kh = khdao.findById(user).get();

        items.setDateOfBirth(kh.getDateOfBirth());
        khdao.save(items);
        return "redirect:/admin/qlkhachhang";
    }

}