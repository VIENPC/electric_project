package com.nhutin.electric_project.user.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.nhutin.electric_project.config.CookieUtils;
import com.nhutin.electric_project.model.Comment;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.CommentsDao;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.prmotionRepositpry;
import com.nhutin.electric_project.repository.productsRepository;

@Controller
public class HomeController {
    @Autowired
    HttpSession session;

    @Autowired
    CommentsDao cmdao;

    @Autowired
    productsRepository prdao;

    @Autowired
    prmotionRepositpry prmoDao;

    @Autowired
    CookieUtils cook;
    @Autowired
    UserRepository userDAO;

    @RequestMapping("/home")
    public String index() {

        return "banhang/view/home";
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam(name = "lang") String lang, HttpServletRequest request) {
        Locale locale = new Locale(lang);
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        return "redirect:/home";
    }

    @RequestMapping("/")
    public String index1() {

        return "banhang/view/home";
    }

    @RequestMapping("/success")
    public String thongbao() {

        return "banhang/view/sucessful";
    }

    @RequestMapping("/about")
    public String about() {

        return "banhang/view/about";
    }

    @RequestMapping("/shop")
    public String productCategory(@RequestParam(name = "categoryID", required = false) Integer categoryID,
            @RequestParam(name = "brandID", required = false) Integer brandID,
            @RequestParam(name = "_fragment", required = false) Integer fragmentID) {
        if (fragmentID != null) {
            // Sử dụng giá trị của fragmentID để điều hướng đến trang giao diện
            if (categoryID != null) {
                // Xử lý categoryID nếu cần
            } else if (brandID != null) {
                // Xử lý brandID nếu cần
            }
            return "banhang/view/product_list";
        } else {
            return "banhang/view/product_list"; // Trường hợp mặc định
        }
    }

    @RequestMapping("/detail/{productID}")
    public String detailid(@PathVariable("productID") int productID, Model model) {

        List<Comment> com = cmdao.findCommentByProductId(productID);

        for (Comment list : com) {
            System.out.println(list.getContent());
        }
        model.addAttribute("listcomment", com);
        return "banhang/view/product_detail";
    }

    @RequestMapping("comment")
    public String comment(HttpServletRequest req) {
        String email = cook.get("tenDangNhapCookie", req);

        User kh = userDAO.findByEmailLike(email);
        Comment comment = new Comment();

        Product product = prdao.findById(Integer.parseInt(req.getParameter("productid")));
        comment.setProduct(product);
        comment.setUser(kh);
        comment.setContent(req.getParameter("contect"));

        cmdao.save(comment);

        return "banhang/view/product_detail";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "banhang/view/product_detail";
    }

    @RequestMapping("/cart")
    public String cart() {

        return "banhang/view/cart";
    }

    @RequestMapping("/checkout")
    public String checkout(HttpServletRequest req, Model model) {
        String email = (String) session.getAttribute("tenDangNhapLogin");

        if (email != null) {

            User user = userDAO.findByEmailLike(email);
            model.addAttribute("ThongTinTK", user);
            Integer tt = userDAO.findThoiGianDaTao(user.getUserID());
            System.out.println("Số tuổi : " + tt);
            model.addAttribute("soTuoi", tt);
        }

        return "banhang/view/checkout";
    }

    @RequestMapping("/news")
    public String news() {

        return "banhang/view/newss";
    }
}
