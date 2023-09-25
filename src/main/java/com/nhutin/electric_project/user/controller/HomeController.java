package com.nhutin.electric_project.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index() {

        return "banhang/view/home";
    }

    @RequestMapping("/about")
    public String about() {

        return "banhang/view/about";
    }

    @RequestMapping("/shop")
    public String product() {

        return "banhang/view/product_list";
    }

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
}
