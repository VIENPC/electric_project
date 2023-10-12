package com.nhutin.electric_project.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.model.Promotion;
import com.nhutin.electric_project.repository.prmotionHistoryRepositpry;
import com.nhutin.electric_project.repository.prmotionRepositpry;

@Controller
public class KhuyenMaiController {
	@Autowired
	prmotionRepositpry prmoDao;

	@Autowired 
	prmotionHistoryRepositpry promoHistoryDao;
	@GetMapping("/khuyenmai")
    public String khuyenmai(Model model) {		
		   List<Promotion> kmlist = prmoDao.findAll();			      		      
	        model.addAttribute("items", kmlist);
        return "taikhoan/khuyenmai";
    }
}
