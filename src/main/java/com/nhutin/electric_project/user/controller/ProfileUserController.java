package com.nhutin.electric_project.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.OrderDetail;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.orderDetailsRepository;
import com.nhutin.electric_project.repository.ordersRepository;

@Controller
public class ProfileUserController {

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRepository userDAO;
    
    @Autowired 
    ordersRepository orderDAO;
    
    @Autowired
    orderDetailsRepository orderdetailDAO;

    

    @RequestMapping("/user/profile")
    public String getProfileUser(Model model) {

        String email = (String) session.getAttribute("tenDangNhapLogin");
        System.out.println("da lay duoc session : " + email);
        if (email != null) {

            User user = userDAO.findByEmailLike(email);
            model.addAttribute("ThongTinTK", user);
            // System.out.println(user);
        }
        return "taikhoan/profile";
    }
    @RequestMapping("/user/history")
    public String getHistoryOrder(Model model) {
    	 String email = (String) session.getAttribute("tenDangNhapLogin");
         System.out.println("Khôi đã : " + email);
         if (email != null) {

             User user = userDAO.findByEmailLike(email);
             model.addAttribute("ThongTinTK", user);
             System.out.println("Khôi đã : " +  user.getUserID());
             List<Order> orders = orderDAO.findHistory(user.getUserID());
             model.addAttribute("orders", orders);
             for (Order order : orders) {
                 System.out.println("Order ID: " + order.getOrderId() + ", Total Amount: " + order.getTotalAmount());
             }
         }
        return "taikhoan/historyOrder";
    }
    @RequestMapping("/user/xemhoadonct/{mahd}")
    public String xemhdct(@PathVariable("mahd") Integer mahd, Model model) {
        List<OrderDetail> orderdetail = orderdetailDAO.findOrderDetailsByOrderId(mahd);
        model.addAttribute("orderdetail", orderdetail);
        
        for (OrderDetail order : orderdetail) {
            System.out.println("Order ID: " + order.getProduct().getProductName());
        }
        return "taikhoan/historyOrderdetail";
    }

    @PostMapping("/user/profile/update")
    public String updateInfor(@ModelAttribute("ThongTinTK") User user) {
    if (user != null) {
    String email = (String) session.getAttribute("tenDangNhapLogin");
    System.out.println("update: "+email);

    User user2 = userDAO.findByEmailLike(email);
    System.out.println("sữa: "+user2.getFullName());
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