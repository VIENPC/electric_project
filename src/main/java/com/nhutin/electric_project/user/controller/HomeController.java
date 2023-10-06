package com.nhutin.electric_project.user.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.repository.ordersRepository;


@Controller
public class HomeController {
 @Autowired
    HttpSession session;
@Autowired
    ordersRepository orderDao;
    @RequestMapping("/home")
    public String index() {

        return "banhang/view/home";
    }
     @RequestMapping("/success")
    public String thongbao() {

        return "banhang/view/sucessful";
    }

    @RequestMapping("/about")
    public String about() {

        return "banhang/view/about";
    }

    // @RequestMapping("/shop")
    // public String product() {
    //     return "banhang/view/product_list";
    // }

    // @RequestMapping("/shop/{categoryID}")
    // public String productCategory() {
    //     return "banhang/view/product_list";
    // }
@RequestMapping("/shop")
public String productCategory(@RequestParam(name = "categoryID", required = false) Integer categoryID,
                              @RequestParam(name = "brandID", required = false) Integer brandID,
                              @RequestParam(name = "_fragment", required = false) Integer fragmentID) {
    if (fragmentID != null) {
        // Sử dụng giá trị của fragmentID để điều hướng đến trang giao diện
        if (categoryID != null) {
            // Xử lý categoryID nếu cần
        } else if (brandID != null) {
            // Xử lý brandID nếu cần
        }
        return "banhang/view/product_list";
    } else {
        return "banhang/view/product_list"; // Trường hợp mặc định
    }
}

    // @RequestMapping("/shop/{brandID}")
    // public String productBrand() {
    // return "banhang/view/product_list";
    // }

    @RequestMapping("/detail/{productID}")
    public String detailid() {
        return "banhang/view/product_detail";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "banhang/view/product_detail";
    }

    @RequestMapping("/cart")
    public String cart() {

        return "banhang/view/cart";
    }

    @RequestMapping("/checkout")
    public String checkout(Model model) {      
         return "banhang/view/checkout";
    }

    @RequestMapping("/news")
    public String news() {

        return "banhang/view/newss";
    }
}
