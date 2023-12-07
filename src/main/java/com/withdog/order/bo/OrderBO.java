package com.withdog.order.bo;

import java.util.HashMap;
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

	public Long addOrder(int accountId, Map<Object, Object> productIdAndCountObj) {
		OrderEntity orderEntity = orderRepository.save(OrderEntity.builder().accountId(accountId).build());

		log.info("$$$$$$$$$$$ orderEntity = {}", orderEntity);

		long orderId = orderEntity.getId();
		Map<Integer, Integer> productIdAndCount = new HashMap<>(productIdAndCountObj.size());
		productIdAndCountObj.forEach((productIdObj, countObj) -> {
			Integer productId = (Integer) productIdObj;
			Integer count = (Integer) countObj;
			productIdAndCount.put(productId, count);
		});
		orderedProductBO.addOrderedProduct(orderId, productIdAndCount);
		return orderId;
	}
	


}
