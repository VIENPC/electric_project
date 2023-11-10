package com.nhutin.electric_project.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.OrderDetail;
import com.nhutin.electric_project.repository.orderDetailsRepository;
import com.nhutin.electric_project.repository.ordersRepository;

@Controller
@RequestMapping("admin")
public class HoaDonController {
    @Autowired
    ordersRepository hddao;
    @Autowired
    orderDetailsRepository hdctdao;

    @RequestMapping("/qldonhang")
    public String qlhoadon(Model model) {

        model.addAttribute("listhd", hddao.findAll());
        System.out.println(hddao.findAll());
        return "admin/view/qloder";
    }

    @PostMapping("/updatetthd")
    public String settthd(HttpServletRequest request) {
        String tt = request.getParameter("tinhtrang");
        System.out.println(request.getParameter("tinhtrang"));

        Order ord = hddao.findById(Integer.parseInt(request.getParameter("mahd"))).get();
        if (tt.equalsIgnoreCase("Chờ xác nhận")) {
            ord.setStatushd(1);
        } else if (tt.equalsIgnoreCase("Đã xác nhận")) {
            ord.setStatushd(2);
        } else if (tt.equalsIgnoreCase("Đang giao")) {
            ord.setStatushd(3);
        } else if (tt.equalsIgnoreCase("Hoàn thành")) {
            ord.setStatushd(4);
        } else if (tt.equalsIgnoreCase("Đã hủy")) {
            ord.setStatushd(5);
        }
        hddao.save(ord);
        return "redirect:/admin/qldonhang?success=updatesp";
    }

    @RequestMapping("/xemhoadonct/{mahd}")
    public String xemhdct(@PathVariable("mahd") Integer mahd, Model model) {
        List<OrderDetail> itemhdct = hdctdao.findOrderDetailsByOrderId(mahd);
        model.addAttribute("listhdct", itemhdct);
        System.out.println(itemhdct);
        return "admin/view/qlhoadonct";
    }

    @RequestMapping("/qldonhang/suatthd/{mahd}")
    public String edittthd(@PathVariable("mahd") Integer mahd) {
        System.out.println(mahd);
        Order hd = hddao.findById(mahd).get();
        hd.setStatushd(2);
        hddao.save(hd);
        return "redirect:/admin/qldonhang?success=updatesp";
    }

    @RequestMapping("/baocaohoadon")
    public String baocaohoadon() {
        return "admin/view/baocaohd";
    }

    @RequestMapping("/reportTable")
    public String report() {
        return "admin/view/report";
    }

    @RequestMapping("/thongkehdtg")
    public String thongketg(Model model,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        List<Object[]> listhd = hddao.thongKeDonHang(startDate, endDate);
        int tongcong = 0;
        for (Object[] itemsp : listhd) {
            tongcong += Double.parseDouble(itemsp[3].toString()) *
                    Double.parseDouble(itemsp[4].toString());
        }
        System.out.println(tongcong);
        model.addAttribute("listhd", listhd);
        model.addAttribute("tongcong", tongcong);
        // // model.addAttribute("listhd", hddao.thongkehdtg(startDate, endDate));
        return "admin/view/baocaohd";
    }

    // @RequestMapping("xemhoadonct")
    // public String xemhdct2(@RequestParam("mahd") Integer mahd, Model model) {
    // System.out.println(mahd);
    // List<> itemhdct = hdctdao.findChiTietHoaDonByMaHoaDon(mahd);
    // model.addAttribute("listhdct", itemhdct);
    // for (HoaDonCT it : itemhdct) {
    // System.out.println(it.getSanpham().getTensp());
    // }
    // return "admin/view/qloder";
    // }
}
