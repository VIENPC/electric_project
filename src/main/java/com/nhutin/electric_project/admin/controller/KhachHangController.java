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
import com.nhutin.electric_project.model.OrderDetail;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.orderDetailsRepository;
import com.nhutin.electric_project.repository.ordersRepository;
import com.nhutin.electric_project.repository.productsRepository;

import jakarta.jws.soap.SOAPBinding.Use;

@Controller
@RequestMapping("admin")
public class KhachHangController {
    @Autowired
    UserRepository khdao;

    @Autowired
    ordersRepository orderDao;

    @Autowired
    orderDetailsRepository orderdetaiDao;

    @Autowired
    productsRepository productDao;

    @RequestMapping("/qlkhachhang")
    public String qlkhachhang(Model model) {

        List<User> khlist = khdao.findAll();
        model.addAttribute("items", khlist);
        return "admin/view/qlcustomer";
    }

    @RequestMapping("/qlkhachhang/{user}")
    public String edittkh(@PathVariable("user") Integer user, Model model) {
        User kh = khdao.findById(user).get();
        List<Object[]> result = orderdetaiDao.findMostOrderedProductByUserId(user);
        if (result != null && !result.isEmpty()) {
            Object[] firstRow = result.get(0); // Lấy dòng đầu tiên từ danh sách kết quả
            Integer productID = (Integer) firstRow[0];
            Long totalQuantity = (Long) firstRow[1];
            System.out.println("Product ID: " + productID);
            System.out.println("Total Quantity Ordered: " + totalQuantity);
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // System.out.println("Không tìm thấy sản phẩm nào cho người dùng có id " +
            // user);
        }
        model.addAttribute("items", kh);
        return "admin/view/editUser";
    }

    @RequestMapping("/historyOrder/{user}")
    public String history(@PathVariable("user") Integer user, Model model) {
        List<Order> order = orderDao.findHistory(user);
        model.addAttribute("listhd", order);
        return "admin/view/qloderHistory";
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

    @RequestMapping("/qlsanpham/{user}")
    public String qlSanPham(@PathVariable("user") Integer user, Model model) {
        List<Object[]> result = orderdetaiDao.findMostOrderedProductByUserId(user);
        if (result != null && !result.isEmpty()) {
            Object[] firstRow = result.get(0); // Lấy dòng đầu tiên từ danh sách kết quả
            Integer productID = (Integer) firstRow[0];
            Long totalQuantity = (Long) firstRow[1];

            Product sp = productDao.findById(productID).get();
            model.addAttribute("items", sp);
            System.out.println("Product ID: " + productID);
            System.out.println("Total Quantity Ordered: " + totalQuantity);
        } else {
            // System.out.println("Không tìm thấy sản phẩm nào cho người dùng có id " +
            // user);
        }

        return "admin/view/producUser";
    }

    @PostMapping("/qlkhachhang/updata/{user}")
    public String update(@PathVariable("user") Integer user, @Valid User items, BindingResult result, Model model) {
        User kh = khdao.findById(user).get();

        items.setDateOfBirth(kh.getDateOfBirth());
        khdao.save(items);
        return "redirect:/admin/qlkhachhang";
    }

}