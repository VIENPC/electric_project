package com.nhutin.electric_project.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class HomeController2 {

   @RequestMapping("baocaochung")
   public String baocaochung() {
      return "admin/view/baocaochung";
   }

   @RequestMapping("supplier")
   public String baocaosanpham() {
   return "admin/view/supplier";
   }

   @RequestMapping("insertUser")
   public String insertUser() {
      return "admin/view/qlcustomer_insert";
   }

   // @RequestMapping("UserDetail")
   // public String UserDetail() {
   // return "admin/view/qlcustomer_detail";
   // }

   @RequestMapping("brand")
   public String brand() {
      return "admin/view/qlbrand";
   }

   @RequestMapping("category")
   public String category() {
      return "admin/view/qlcategorys";
   }

   @RequestMapping("addBrand")
   public String add() {
      return "admin/view/add_brand";
   }

   @RequestMapping("addCategory")
   public String addCate() {
      return "admin/view/add_category";
   }
}
