package com.nhutin.electric_project.admin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.repository.categorysRepository;
import com.nhutin.electric_project.repository.productsRepository;

@Controller
@RequestMapping("admin")
public class ThongKeSPController {

    @Autowired
    productsRepository spdao;

    @Autowired
    brandsRepository hsxdao;

    @Autowired
    categorysRepository dmdao;

    @RequestMapping("/baocaosanpham")
    public String baocaosanpham(Model model) {
        int totalTongGiaHang = 0;
        model.addAttribute("tongtien", totalTongGiaHang);
        model.addAttribute("listhxs", hsxdao.findAll());
        model.addAttribute("listdm", dmdao.findAll());
        return "admin/view/baocaosp";
    }

    @PostMapping("/thongke")
    public String thongkehang(@RequestParam("mahang") Integer mahang, Model model) {
        model.addAttribute("listhxs", hsxdao.findAll());
        model.addAttribute("listdm", dmdao.findAll());
        List<Object[]> listsp = spdao.thongkeSanPhamTheoBrand(mahang);
        System.out.println(listsp);
        int totalTongGiaHang = 0;
        for (Object[] itemsp : listsp) {
            totalTongGiaHang += Double.parseDouble(itemsp[3].toString()) *
                    Double.parseDouble(itemsp[2].toString());
        }
        model.addAttribute("itemtksp", listsp);
        model.addAttribute("tongcong", totalTongGiaHang);

        return "admin/view/baocaosp";
    }

    @RequestMapping("/thongkesptg")
    public String thongketg(Model model,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("listhxs", hsxdao.findAll());

        List<Object[]> listsp = spdao.thongkesptg(startDate, endDate);
        int totalTongGiaHang = 0;
        for (Object[] itemsp : listsp) {
            totalTongGiaHang += Double.parseDouble(itemsp[3].toString()) *
                    Double.parseDouble(itemsp[2].toString());
        }
        model.addAttribute("itemtksp", listsp);
        model.addAttribute("tongcong", totalTongGiaHang);
        return "admin/view/baocaosp";
    }

    @PostMapping("/thongkedm")
    public String thongkedm(@RequestParam("madm") Integer madm, Model model) {

        model.addAttribute("listhxs", hsxdao.findAll());
        model.addAttribute("listdm", dmdao.findAll());
        System.out.println(madm);
        int totalTongGiaHang = 0;
        List<Object[]> listtk = spdao.thongkeSanPhamTheoMuc(madm);
        for (Object[] objects : listtk) {
            totalTongGiaHang += Double.parseDouble(objects[4].toString());

        }
        model.addAttribute("itemtksp", listtk);
        model.addAttribute("tongcong", totalTongGiaHang);
        return "admin/view/baocaosp";
    }
}
