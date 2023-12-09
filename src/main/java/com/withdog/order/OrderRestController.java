package com.withdog.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
			@RequestBody List<Map<String, Object>> productIdAndCountJson) {
		Map<String, Object> result = new HashMap<>();
		Integer accountId = (Integer) session.getAttribute("accountId");
		log.info("$$$$$$$$$$$$ accountId = {}", accountId);
		if (ObjectUtils.isEmpty(accountId)) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 후 이용해주세요.");
			log.warn("############ 비로그인 상태");
			return result;
		}
		int orderId = orderBO.addOrder(accountId, productIdAndCountJson);
		
		result.put("code", 200);
		result.put("result", "success");
		result.put("orderId", orderId);
		
		return result;
	}
	
	
}
