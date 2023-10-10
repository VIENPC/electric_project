package com.nhutin.electric_project.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.prmotionRepositpry;

@Controller
public class PromotionController {
	

@Autowired
prmotionRepositpry promo;

  @RequestMapping("/user/promotion") 
  public String getPromotion(Model model) {

     
      return "taikhoan/promotion";
  }
}