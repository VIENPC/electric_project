package com.nhutin.electric_project;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
//	@Autowired
//	ProductService productservice;
//
//	@Autowired
//	ProductCategoryService ProductCategoryService;
//
//	@Autowired
//	ProductGroupService ProductGroupService;
//
//	@Autowired
//	ColorService ColorService;
//
//	@Autowired
//	SubCategoryService subcateService;
//
//	@Autowired
//	CartDetailDAO cartDetailDAO;
//
//	@Autowired
//	Service service;
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			@Nullable ModelAndView modelAndView) throws Exception {
//		request.setAttribute("prods", productservice.findAll());
//		request.setAttribute("prodcates", ProductCategoryService.findAll());
//		request.setAttribute("prodgroups", ProductGroupService.findAll());
//
//		List<Color> colors = ColorService.findAll();
//		List<String> color = new ArrayList<>();
//		for (Color c : colors) {
//			if (!color.contains(c.getColorName())) {
//				color.add(c.getColorName());
//			}
//		}
//		request.setAttribute("stringcolor", color);
//		request.setAttribute("cols", ColorService.findAll());
//		request.setAttribute("subcates", subcateService.findAll());
//		request.setAttribute("amountCart", 0);
//
//		try {
//			User user = service.getUser();
//			if (user != null) {
//				request.setAttribute("amountCart", cartDetailDAO.countCartByIDUser(user.getUserID()));
//			}
//		} catch (Exception e) {
//		}
//
//	}
}
