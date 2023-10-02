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
        Date currentDate = new Date();
        System.out.println(khdao.count());
        // // Lấy ngày cách đây 5 ngày
        // long fiveDaysInMillis = 5 * 24 * 60 * 60 * 1000;
        // Date fiveDaysAgo = new Date(currentDate.getTime() - fiveDaysInMillis);
        // model.addAttribute("khm", khdao.findNewCustomers(fiveDaysAgo));

        return "admin/view/dashboard";
    }

    // @ModelAttribute
    // public void setthoigianhd() {
    // // Date currentDate = new Date();
    // // Date dateToCompare = new Date(this.mua.getTime() + 3 * 24 * 60 * 60 *
    // 1000); // Ngày đặt + 3 ngày
    // // List<HoaDon> listhd = hddao.findAll();
    // // for (HoaDon hoaDon : listhd) {
    // // if()
    // // }
    // }
}
