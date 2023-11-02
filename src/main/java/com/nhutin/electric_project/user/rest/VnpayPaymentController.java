package com.nhutin.electric_project.user.rest;

import com.nhutin.electric_project.config.CookieUtils;
import com.nhutin.electric_project.config.VnpayConfig;
import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.PaymentDTO;
import com.nhutin.electric_project.model.PaymentQR;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.ordersRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class VnpayPaymentController {

	@Autowired
	CookieUtils cookie;

	@Autowired
	UserRepository userDAO;

	@Autowired
	ordersRepository orderDAO;

	// @GetMapping
	// public ModelAndView payVNP() {
	// ModelAndView modelAndView = new ModelAndView("index");
	// modelAndView.addObject("pay", new PaymentQR());
	// return modelAndView;
	// }
	// @GetMapping("/pay")
	// public ModelAndView payQR(){
	// ModelAndView modelAndView = new ModelAndView("vnpay_pay");
	// modelAndView.addObject("payment",new PaymentQR());
	// return modelAndView;
	// }

	@PostMapping("/totalAmount")
	public ResponseEntity<String> receiveTotalAmount(@RequestBody PaymentQR request) {
		Integer totalAmount = request.getAmount();
		Integer madh = request.getOrderid();
		System.out.println(totalAmount);
		System.out.println(madh);
		// Xử lý giá trị totalAmount ở đây

		return ResponseEntity.ok("Total Amount received successfully");
	}

	@PostMapping("/pay/vnpayajax")
	public ResponseEntity<Map<String, String>> getPaymentUrl(@RequestBody PaymentQR request) throws IOException {
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";
		long amount = (request.getAmount()) * 100;
		Integer mahd = request.getOrderid();
		System.out.println("tongtieng"+amount);
		System.out.println(mahd);
		// String bankCode = req.getParameter("bankCode");

		String vnp_TxnRef = String.valueOf(mahd);
		String vnp_IpAddr = "127.0.0.1";
		String vnp_TmnCode = VnpayConfig.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");

		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", orderType);
		vnp_Params.put("vnp_Locale", "vn");
		// String locate = req.getParameter("language");
		// if (locate != null && !locate.isEmpty()) {
		// vnp_Params.put("vnp_Locale", locate);
		// } else {

		// }
		vnp_Params.put("vnp_ReturnUrl", VnpayConfig.vnp_Returnurl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder(); 
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = VnpayConfig.vnp_PayUrl + "?" + queryUrl;
		System.out.println(paymentUrl);

		// Tạo một đối tượng JSON để trả về paymentUrl
		Map<String, String> response = new HashMap<>();
		response.put("paymentUrl", paymentUrl);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/vnpay_jsp")
	public ResponseEntity<String> laythongtin(@RequestParam("vnp_TransactionStatus") String trangthaitt,
			@RequestParam("vnp_TxnRef") String order_id) {

		if (trangthaitt.equals("00")) {
			Order order = orderDAO.findById(Integer.parseInt(order_id)).orElse(null);
			if (order != null) {
				order.setStatustt(true);
				orderDAO.save(order);

				// Chuyển hướng đến trang thông báo thành công (ví dụ: /success)
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/success").build();
			}
		}

		// Nếu không thành công hoặc không tìm thấy đơn hàng, có thể chuyển hướng đến
		// trang lỗi hoặc trang khác.
		return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/error").build();
	}

}
