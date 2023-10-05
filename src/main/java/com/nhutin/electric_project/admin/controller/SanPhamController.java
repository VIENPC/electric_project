package com.nhutin.electric_project.admin.controller;

import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

import com.nhutin.electric_project.model.Brand;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.repository.productsRepository;

@Controller
@RequestMapping("admin")
public class SanPhamController {
	@Autowired
	ServletContext app;

	@Autowired
	brandsRepository hsxdao;

	@Autowired
	productsRepository spdao;

	// @Autowired
	// HoaDonDAO hddao;

	// @Autowired
	// HoaDonCTDAO hdctdao;

	@RequestMapping("/qlsanpham")
	public String qlsanpham(Model model) {
		Product sp = new Product();
		model.addAttribute("sp", sp);
		Brand hsx = new Brand();
		model.addAttribute("hsx", hsx);
		// load danh mục hãng sảnxuất
		List<Brand> listhsx = hsxdao.findAll();
		model.addAttribute("listhsx", listhsx);
		// model.addAttribute("listsp", spService.findAll());

		List<Product> listsp = spdao.findAll();
		model.addAttribute("listsp", listsp);
		return "admin/view/product";

	}

	@RequestMapping("/addsanpham")
	public String addsanpham(Model model) {
		Product sp = new Product();
		model.addAttribute("sp", sp);
		Brand hsx = new Brand();
		model.addAttribute("hsx", hsx);
		// load danh mục hãng sản xuất
		List<Brand> listhsx = hsxdao.findAll();
		model.addAttribute("listhsx", listhsx);
		// In danh sách sản phẩm ra terminal
		for (Brand product : listhsx) {
			System.out.println("Product ID: " + product.getBrandName());
			System.out.println("Product Name: " + product.getBrandID());
			// Thêm các trường thông tin sản phẩm khác tương tự ở đây
		}
		return "admin/view/add_product";
	}

	@RequestMapping("/addsanpham/{masp}")
	public String editmasp(Model model, @PathVariable("masp") Integer masp) {
		List<Brand> listhsx = hsxdao.findAll();
		model.addAttribute("listhsx", listhsx);
		Product sp = spdao.findById(masp).get();
		// System.out.println(sp.getHinhanh());
		model.addAttribute("sp", sp);
		return "admin/view/updatesp";
	}

	@RequestMapping("/addsanpham/save")
	public String add_sp(Model model, @Validated @ModelAttribute("sp") Product sp, Errors errors,
			@RequestParam("image") MultipartFile img) {
		try {
			if (errors.hasErrors() || img.isEmpty()) {
				if (img.isEmpty()) {
					model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
				}
				List<Brand> listhsx = hsxdao.findAll();
				model.addAttribute("listhsx", listhsx);
				return "admin/view/add_product";
			} else {
				LocalDateTime now = LocalDateTime.now();
				String filename = img.getOriginalFilename();
				File file = new File(app.getRealPath("/images/sanpham/" + filename));
				img.transferTo(file);
				sp.setImage(filename);
				// sp.setNgaynhap(now);
				// sp.setNgaynhap(now);
				// System.out.println(formattedDateTime);
				// System.out.println(sp.getMota());
				spdao.save(sp);
			}

			return "redirect:/admin/addsanpham?success=add";
		} catch (Exception e) {
			model.addAttribute("loitensp", "Tên sản phẩm đã tồn tại");
		}
		return null;

	}

	@RequestMapping("/addsanpham/themhang")
	public String add_hangsx(Model model, @RequestParam("hinh_hang") MultipartFile img,
			@Validated @ModelAttribute("hsx") Brand hsx, Errors errors)
			throws IllegalStateException, IOException {
		// if(errors.hasErrors() || img.isEmpty()){
		// if(img.isEmpty()){
		// model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
		// }
		// return "admin/view/add_product";
		// }

		String filename = img.getOriginalFilename();
		File file = new File(app.getRealPath("/images/logohang/" + filename));
		img.transferTo(file);
		hsx.setImage(filename);
		hsxdao.save(hsx);

		return "redirect:/admin/addsanpham";
	}

	@RequestMapping("/qlsanpham/deletesp/{masp}")
	public String deletesp(@PathVariable("masp") Integer masp, RedirectAttributes redirectAttributes) {
		try {
			// Gọi service hoặc repository để xóa sản phẩm dựa vào id
			spdao.deleteById(masp);

			// Chuyển hướng tới trang hiển thị thông báo xóa thành công
			redirectAttributes.addFlashAttribute("successMessage", "Đã xóa sản phẩm thành công!");
			return "redirect:/admin/qlsanpham?success=delete";
		} catch (Exception e) {
			// Chuyển hướng tới trang hiển thị thông báo xóa không thành công
			redirectAttributes.addFlashAttribute("errorMessage", "Xóa sản phẩm không thành công: " + e.getMessage());
			return "redirect:/admin/qlsanpham?fail=delete";
		}

	}

	@RequestMapping("/qlsanpham/updatesp")
	public String updatesp(HttpServletRequest request) {
		try {
			Integer masp = Integer.parseInt(request.getParameter("masp"));
			Integer soluong = Integer.parseInt(request.getParameter("soluong"));
			String giaspstr = request.getParameter("giasp");
			giaspstr = giaspstr.replace("đ", "").replace(".", "");
			Double giasp = (double) Integer.parseInt(giaspstr);
			String trangthai = request.getParameter("tinhtrang");
			Boolean tt = false;
			if (trangthai.equalsIgnoreCase("còn hàng")) {
				tt = true;
			}

			Product sp = spdao.findById(masp).get();
			sp.setProductID(sp.getProductID());
			sp.setProductName(request.getParameter("tensp"));
			sp.setQuantity(soluong);
			sp.setActive(tt);
			sp.setPrice(giasp);
			spdao.save(sp);
			// in kiểm tra thông tin trước khi update
			// System.out.println(masp);
			// System.out.println(request.getParameter("tensp"));
			// System.out.println(soluong);
			// System.out.println(tt);
			// System.out.println(giasp);
			return "redirect:/admin/qlsanpham?success=updatesp";
		} catch (Exception e) {

			return "redirect:/admin/qlsanpham?fail=updatesp";
		}

	}

	@RequestMapping("/updatesp/save")
	public String update(Model model, @Validated @ModelAttribute("sp") Product sp, Errors errors,
			@RequestParam("image	") MultipartFile img)
			throws IllegalStateException, IOException {
		if (errors.hasErrors() || img.isEmpty()) {
			if (img.isEmpty()) {
				model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
			}
			return "admin/view/updatesp";
		} else {
			LocalDateTime now = LocalDateTime.now();
			String filename = img.getOriginalFilename();
			File file = new File(app.getRealPath("/images/sanpham/" + filename));
			Product sp2 = spdao.findById(sp.getProductID()).get();
			img.transferTo(file);

			sp2.setImage(filename);
			sp2.setProductName(sp.getProductName());
			sp2.setQuantity(sp.getQuantity());
			sp2.setPrice(sp.getPrice());
			sp2.setActive(sp.isActive());
			sp2.setBrand(sp.getBrand());
			// sp2.set(sp.getXuatsu());
			sp2.setDescription(sp.getDescription());
			// sp2.setNgaynhap(now);
			spdao.save(sp2);
			return "redirect:/admin/qlsanpham?success=updatesp";
		}

	}

	@ModelAttribute
	public void globalModelAttributes(Model model) {

		List<Product> sp = spdao.findAll();
		for (Product sanPham : sp) {
			if (sanPham.getQuantity() == 0) {
				sanPham.setActive(false);
				spdao.save(sanPham);
			}
		}
	}
}
