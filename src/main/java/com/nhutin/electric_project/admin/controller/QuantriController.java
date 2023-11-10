package com.nhutin.electric_project.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhutin.electric_project.model.Brand;
import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.repository.categorysRepository;
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
	@Autowired
	brandsRepository hsxdao;

	@Autowired
	categorysRepository dmdao;

	@RequestMapping("/qldanhmuc")
	public String qldanhmuc() {

		return "admin/view/qldanhmuc";
	}

	@RequestMapping("/qlsosanh")
	public String sosanh(Model model) {
		model.addAttribute("listhxs", hsxdao.findAll());
		model.addAttribute("listdm", dmdao.findAll());

		return "admin/view/sosanh";
	}

	@RequestMapping("sosanhloai")
	public String sosanhloai(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(request.getParameter("loai1"));
		Integer maloai = Integer.parseInt(request.getParameter("loai1"));
		Integer maloai2 = Integer.parseInt(request.getParameter("loai2"));
		Integer thang = Integer.parseInt(request.getParameter("thang"));
		System.out.println(thang);
		// model.addAttribute("loai1", );
		// Category loai = dmdao.findById(maloai).get();
		// System.out.println(loai.getCategoryName());
		Category l = dmdao.findById(maloai).get();
		Category l2 = dmdao.findById(maloai2).get();
		String tenl1 = l.getCategoryName();
		String tenl2 = l2.getCategoryName();

		redirectAttributes.addFlashAttribute("loai1", tenl1);
		redirectAttributes.addFlashAttribute("loai2", tenl2);
redirectAttributes.addFlashAttribute("thangchon", thang);
		Object[] listloai1 = dmdao.loadtkthdang(maloai, thang);
		Object[] listloai2 = dmdao.loadtkthdang(maloai2,thang);
		Object[] ht1 = spdao.getcoutproducTonkho(maloai, thang);
		Object[] ht2 = spdao.getcoutproducTonkho(maloai2, thang);
		redirectAttributes.addFlashAttribute("objectloai1", listloai1);
		redirectAttributes.addFlashAttribute("objectloai2", listloai2);
		redirectAttributes.addFlashAttribute("ht1", ht1);
		redirectAttributes.addFlashAttribute("ht2", ht2);
		// System.out.println(request.getParameter("loai2"));
		

		return "redirect:/admin/qlsosanh";
	}

	@RequestMapping("sosanhhang")
	public String sosanhhang(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// System.out.println(request.getParameter("loai1"));
		Integer mahang = Integer.parseInt(request.getParameter("hang1"));
		Integer mahang2 = Integer.parseInt(request.getParameter("hang2"));
		Integer thang = Integer.parseInt(request.getParameter("thang"));
		// model.addAttribute("loai1", );
		// Category loai = dmdao.findById(maloai).get();
		// System.out.println(loai.getCategoryName());
		System.out.println(thang);
		Brand h1 = hsxdao.findById(mahang).get();
		Brand h2 = hsxdao.findById(mahang2).get();
		String tenh1 = h1.getBrandName();
		String tenh2 = h2.getBrandName();
		
		redirectAttributes.addFlashAttribute("thangchon", thang);
		redirectAttributes.addFlashAttribute("loai1", tenh1);
		redirectAttributes.addFlashAttribute("loai2", tenh2);

		Object[] listhang1 = hsxdao.sosanhaang(mahang, thang);
		Object[] listhang2 = hsxdao.sosanhaang(mahang2, thang);
		Object[] ht1 = spdao.getcoutproducTonkho2(mahang, thang);
		Object[] ht2 = spdao.getcoutproducTonkho2(mahang2, thang);
		
		redirectAttributes.addFlashAttribute("objectloai1", listhang1);
		redirectAttributes.addFlashAttribute("objectloai2", listhang2);
		redirectAttributes.addFlashAttribute("ht1", ht1);
		redirectAttributes.addFlashAttribute("ht2", ht2);
		// System.out.println(request.getParameter("loai2"));
		System.out.println(ht1[0]);
 
		return "redirect:/admin/qlsosanh";
	}

	@RequestMapping("/baocaochung")
	public String baocaochung(Model model) {
		model.addAttribute("slsp", spdao.count());
		model.addAttribute("slhd", hddao.count());
		model.addAttribute("slspht", spdao.countByActive(false));
		model.addAttribute("tkhk", khdao.countLockedUsers());
		model.addAttribute("sldhh", hddao.countDontt(5));
		double tongtien = hddao.sumTotalAmountOfApprovedOrders();
		System.out.println(tongtien);
		if (tongtien == 0.0) {
			model.addAttribute("tthoadon", 0);
		} else {
			model.addAttribute("tthoadon", tongtien);
		}
		System.out.println(tongtien);

		model.addAttribute("listspbc", spdao.findBestSellingProducts());

		model.addAttribute("listspht", spdao.findByActive(false));
		return "admin/view/baocaochung";
	}

}
