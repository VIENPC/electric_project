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

   @RequestMapping("baocaosanpham")
   public String baocaosanpham() {
      return "admin/view/baocaosp";
   }

}
