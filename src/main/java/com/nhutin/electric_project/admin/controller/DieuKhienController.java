package com.nhutin.electric_project.admin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhutin.electric_project.repository.UserRepository;
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

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("slkh", khdao.count());

        model.addAttribute("slsp", spdao.count());

        model.addAttribute("slhd", hddao.count());

        model.addAttribute("slspht", spdao.countByActive(false));

        model.addAttribute("listhdtt", hddao.findHdTt(1));

        model.addAttribute("listspbc", spdao.findBestSellingProducts());

        List<Object[]> revenueData = hddao.calculateRevenueByMonth();
        model.addAttribute("revenueData", revenueData);
        // System.out.println("Thống kê" + revenueData);
        return "admin/view/dashboard";
    }
}
