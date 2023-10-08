package com.nhutin.electric_project.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Report {

  @RequestMapping("/brands")
  public String Brand() {
    return "admin/view/qlbrand";
  }

  @RequestMapping("/categorys")
  public String category() {
    return "admin/view/qlcategorys";
  }

  @RequestMapping("/suppliers")
  public String supplier() {
    return "admin/view/supplier";
  }
}
