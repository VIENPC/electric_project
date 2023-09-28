package com.nhutin.electric_project.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.ordersRepository;
import com.nhutin.electric_project.repository.productsRepository;

@Controller
@RequestMapping("admin")
public class QuantriController {

	@Autowired
	UserRepository khdao;
	@Autowired
	productsRepository spdao;
	@Autowired
	ordersRepository hddao;

	@RequestMapping("/qldanhmuc")
	public String qldanhmuc() {

		return "admin/view/qldanhmuc";
	}

	@RequestMapping("/baocaochung")
	public String baocaochung(Model model) {
		model.addAttribute("slsp", spdao.count());
		model.addAttribute("slhd", hddao.count());
		model.addAttribute("slspht", spdao.countByActive(false));
		model.addAttribute("tkhk", khdao.countLockedUsers());
		// model.addAttribute("sldhh", hddao.countDontt(5));
		// double tongtien = hddao.sumTotalAmountOfApprovedOrders();
		// if (tongtien == null) {
		// model.addAttribute("tthoadon", 0);
		// } else {
		// model.addAttribute("tthoadon", tongtien);
		// }

		// model.addAttribute("listspbc", spdao.findSanPhamBanChay());
		model.addAttribute("listspht", spdao.findOutOfStockProducts());

		return "admin/view/baocaochung";
	}

}
