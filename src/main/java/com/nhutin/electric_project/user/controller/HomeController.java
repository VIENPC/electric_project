package com.nhutin.electric_project.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
 @Autowired
    HttpSession session;

    @RequestMapping("/home")
    public String index() {

        return "banhang/view/home";
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
    public String checkout() {
         
         return "banhang/view/checkout";
    }

    @RequestMapping("/news")
    public String news() {

        return "banhang/view/newss";
    }
}
