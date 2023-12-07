package com.withdog.order;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.order.bo.OrderBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderRestController {

	private final OrderBO orderBO;
	
	@PostMapping("/create-order")
	public Map<String, Object> createOrder(
			HttpSession session,
			@RequestParam Map<Object, Object> productIdAndCount) {
		Map<String, Object> result = new HashMap<>();
		Integer accountId = (Integer) session.getAttribute("accountId");
		if (ObjectUtils.isEmpty(accountId)) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 후 이용해주세요.");
			return result;
		}
		Long orderId = orderBO.addOrder(accountId, productIdAndCount);
		
		return result;
	}
	
	
}