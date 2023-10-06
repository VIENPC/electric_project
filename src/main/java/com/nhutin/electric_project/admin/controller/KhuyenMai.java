package com.nhutin.electric_project.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.model.Promotion;
import com.nhutin.electric_project.model.Promotion_Historie;
import com.nhutin.electric_project.repository.prmotionHistoryRepositpry;
import com.nhutin.electric_project.repository.prmotionRepositpry;
import com.nhutin.electric_project.repository.productsRepository;

@Controller
@RequestMapping("admin")
public class KhuyenMai {
	
@Autowired
prmotionRepositpry prmoDao;

@Autowired 
prmotionHistoryRepositpry promoHistoryDao;
	 @RequestMapping("/qlkhuyenmai")
	    public String qlkhuyenmai(Model model) {
	        List<Promotion> kmlist = prmoDao.findAll();	       
	        model.addAttribute("items", kmlist);
	        return "admin/view/qlkhuyenmai";
	    }
	 @RequestMapping("/qlkhuyenmai/{id}")
	    public String qlLSkhuyenmai(@PathVariable("id") Integer id, Model model) {

	        List<Promotion_Historie> listHitory= promoHistoryDao.findPromoHistory(id);	       
	        int userP =0;
	        for (Promotion_Historie km : listHitory) {
	        	userP++;
	        }
	        model.addAttribute("items", listHitory);
	        model.addAttribute("userPromotionCount", userP);
	        return "admin/view/qlHistoryPromotion";
	    }
}
