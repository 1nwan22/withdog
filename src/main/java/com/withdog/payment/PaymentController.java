package com.withdog.payment;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.withdog.order.bo.OrderViewBO;
import com.withdog.order.domain.OrderView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PaymentController {

	private final OrderViewBO orderViewBO;
	
	// http://localhost/checkout
	@GetMapping("/checkout/{orderId}")
	public String checkoutView(Model model, HttpSession session,
			@PathVariable int orderId) {
		OrderView orderView = orderViewBO.getOrderView(orderId);
		log.info("$$$$$$$$$$$$$$$$$$$$ orderView = {}", orderView);
		model.addAttribute("order", orderView);
		model.addAttribute("viewName", "pay/checkout");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}