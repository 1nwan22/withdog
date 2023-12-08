package com.withdog.order.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.withdog.order.entity.OrderEntity;
import com.withdog.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderBO {

	private final OrderRepository orderRepository;
	private final OrderedProductBO orderedProductBO;

	public Long addOrder(int accountId, List<Map<String, Object>> productIdAndCountJson) {
		OrderEntity orderEntity = orderRepository.save(
				OrderEntity.builder()
				.accountId(accountId)
				.build());

		log.info("$$$$$$$$$$$ orderEntity = {}", orderEntity);

		long orderId = orderEntity.getId();
		Map<Integer, Integer> productIdAndCount = new HashMap<>();
		for (Map<String, Object> e : productIdAndCountJson) {
			Integer productId = (Integer) e.get("productId");
			Integer count = (Integer) e.get("count");
			productIdAndCount.put(productId, count);
			
		}
		orderedProductBO.addOrderedProduct(orderId, productIdAndCount);
		return orderId;
	}
	


}
