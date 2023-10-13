package com.nhutin.electric_project.admin.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhutin.electric_project.model.Promotion;
import com.nhutin.electric_project.model.Promotion_Historie;
import com.nhutin.electric_project.repository.prmotionHistoryRepositpry;
import com.nhutin.electric_project.repository.prmotionRepositpry;

@Controller
@RequestMapping("admin")
public class KhuyenMai {

	@Autowired
	prmotionRepositpry prmoDao;

	@Autowired
	prmotionHistoryRepositpry promoHistoryDao;

	Promotion pro = new Promotion();

	@RequestMapping("/qlkhuyenmai")
	public String qlkhuyenmai(Model model) {

		List<Promotion> kmlist = prmoDao.findAll();

		model.addAttribute("items", kmlist);
		return "admin/view/qlkhuyenmai";
	}

	@RequestMapping("/qlkhuyenmai/{id}")
	public String qlLSkhuyenmai(@PathVariable("id") Integer id, Model model) {

		List<Promotion_Historie> listHitory = promoHistoryDao.findPromoHistory(id);
		int userP = 0;
		for (Promotion_Historie km : listHitory) {
			userP++;
		}
		model.addAttribute("items", listHitory);
		model.addAttribute("userPromotionCount", userP);
		return "admin/view/qlHistoryPromotion";
	}

	@RequestMapping("/qlkhuyenmai/id/{userID}")
	public String qlLSkhuyenmaiID(@PathVariable("userID") Integer userID, Model model) {

		List<Promotion_Historie> listHitory = promoHistoryDao.findIDHistory(userID);
		int userP = 0;
		for (Promotion_Historie km : listHitory) {
			userP++;
		}
		model.addAttribute("items", listHitory);
		model.addAttribute("userPromotionCount", userP);
		return "admin/view/qlHistoryPromotion";
	}

	@RequestMapping("/qlkhuyenmai/nameOrder/{name}")
	public String qlLSkhuyenmaiOrder(@PathVariable("name") String name, Model model) {

		List<Promotion_Historie> listHitory = promoHistoryDao.findPromotionHistoriesByOrderName(name);

		int userP = 0;
		for (Promotion_Historie km : listHitory) {
			userP++;
		}
		model.addAttribute("items", listHitory);
		model.addAttribute("userPromotionCount", userP);
		return "admin/view/qlHistoryPromotion";
	}

	@RequestMapping(value = "/qlkhuyenmai/delete/{id}")
	public String deletesp(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {

			prmoDao.deleteById(id);

			/*
			 * redirectAttributes.addFlashAttribute("successMessage",
			 * "Đã xóa sản phẩm thành công!");
			 */
			return "redirect:/admin/qlkhuyenmai";
		} catch (Exception e) {

			/*
			 * redirectAttributes.addFlashAttribute("errorMessage",
			 * "Xóa sản phẩm không thành công: " + e.getMessage());
			 */
			return "redirect:/admin/qlkhuyenmai";
		}

	}

	@RequestMapping("/qlkhuyenmai/nameUser/{fullName}")
	public String qlLSkhuyenmaiUser(@PathVariable("fullName") String fullName, Model model) {
		List<Promotion_Historie> listHitory = promoHistoryDao.findPromotionHistoriesByUserFullName(fullName);

		int userP = 0;
		for (Promotion_Historie km : listHitory) {
			userP++;
		}
		model.addAttribute("items", listHitory);
		model.addAttribute("userPromotionCount", userP);
		return "admin/view/qlHistoryPromotion";
	}

	@RequestMapping("/qlkhuyenmai/edit/{id}")
	public String edittkh(@PathVariable("id") Integer id, Model model) {
		Promotion kh = prmoDao.findById(id).get();
		model.addAttribute("items", kh); // Thêm đối tượng items vào model
		return "admin/view/editPromotion";
	}

	@PostMapping("/qlkhuyenmai/updata/{id}")
	public String update(@PathVariable("id") Integer id, @Valid Promotion items, BindingResult result, Model model) {

		Promotion kh = prmoDao.findById(id).get();

		System.out.println("tên:" + kh.getUsedDates());
		items.setUsedDates(kh.getUsedDates());
		prmoDao.save(items);

		return "redirect:/admin/qlkhuyenmai";
	}

	@GetMapping("/qlkhuyenmai/addPromotion")
	public String AddPromotion(Model model) {
		model.addAttribute("promotion", new Promotion()); // Promotion là đối tượng khuyến mãi
		return "/admin/view/addPromotion";
	}

	@PostMapping("/qlkhuyenmai/addPromotion/add")
	public String addPromotion(@ModelAttribute("promotion") Promotion promotion) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		promotion.setUsedDates(currentDateTime);
		promotion.setDateEnd(currentDateTime);
		prmoDao.save(promotion);

		return "redirect:/admin/qlkhuyenmai";
	}

}