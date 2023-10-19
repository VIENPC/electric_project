package com.nhutin.electric_project.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhutin.electric_project.ServiceImpl.BrandServiceImpl;
import com.nhutin.electric_project.ServiceImpl.CategoryServiceImpl;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.repository.categorysRepository;
import com.nhutin.electric_project.repository.ordersRepository;
import com.nhutin.electric_project.repository.productsRepository;

@Controller
@RequestMapping("admin")
public class DieuKhienController {
    @Autowired
    UserRepository khdao;
    @Autowired
    productsRepository spdao;
    @Autowired
    ordersRepository hddao;

    @Autowired
    brandsRepository hsxdao;

    @Autowired
    brandsRepository bDAO;
    @Autowired
    categorysRepository cdao;

    @Autowired
    CategoryServiceImpl categoryservice;

    @Autowired
    BrandServiceImpl brandsservice;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("slkh", khdao.count());

        model.addAttribute("slsp", spdao.count());

        model.addAttribute("slhd", hddao.count());

        model.addAttribute("listlsp", cdao.findAll());

        model.addAttribute("slspht", spdao.countByActive(false));

        model.addAttribute("listhdtt", hddao.findHdTt(1));

        model.addAttribute("listspbc", spdao.findBestSellingProducts());

        model.addAttribute("souser", khdao.calculateUserRegistrationByMonth());

        model.addAttribute("soOrder", khdao.calculateOrdersByMonth());

        List<Object[]> revenueData = hddao.calculateRevenueByMonth();
        model.addAttribute("revenueData", revenueData);
        // System.out.println("Thống kê" + revenueData);
        model.addAttribute("listhxs", hsxdao.findAll());
        return "admin/view/dashboard";
    }

    @GetMapping("/revenue-by-month")
    public String getRevenueByMonth(@RequestParam("brandId") int brandId, Model model) {
        model.addAttribute("listhxs", hsxdao.findAll());
        List<Object[]> od = brandsservice.getRevenueByMonth(brandId);
        model.addAttribute("revenueDatamonth", od);
        model.addAttribute("slkh", khdao.count());

        model.addAttribute("slsp", spdao.count());

        model.addAttribute("slhd", hddao.count());

        model.addAttribute("slspht", spdao.countByActive(false));

        model.addAttribute("listhdtt", hddao.findHdTt(1));

        model.addAttribute("listspbc", spdao.findBestSellingProducts());

        model.addAttribute("souser", khdao.calculateUserRegistrationByMonth());

        model.addAttribute("soOrder", khdao.calculateOrdersByMonth());
        List<Object[]> revenueData = hddao.calculateRevenueByMonth();
        model.addAttribute("revenueData", revenueData);
        // System.out.println("Thống kê" + revenueData);
        return "admin/view/dashboard";
    }

    @GetMapping("/category-by-month")
    public String getcategoryRevenueByMonth(@RequestParam("categoryid") int categoryid,
            Model model) {
        model.addAttribute("listhxs", bDAO.findAll());
        model.addAttribute("listlsp", cdao.findAll());
        List<Object[]> od = categoryservice.getRevenueByMonth(categoryid);
        model.addAttribute("revenueDatamonth", od);
        model.addAttribute("slkh", khdao.count());
        model.addAttribute("slsp", spdao.count());
        model.addAttribute("slhd", hddao.count());
        model.addAttribute("slspht", spdao.countByActive(false));
        model.addAttribute("listhdtt", hddao.findHdTt(1));
        model.addAttribute("listspbc", spdao.findBestSellingProducts());
        model.addAttribute("souser", khdao.calculateUserRegistrationByMonth());
        model.addAttribute("soOrder", khdao.calculateOrdersByMonth());
        List<Object[]> revenueData = hddao.calculateRevenueByMonth();
        model.addAttribute("revenueData", revenueData);
        return "admin/view/dashboard";
    }
}