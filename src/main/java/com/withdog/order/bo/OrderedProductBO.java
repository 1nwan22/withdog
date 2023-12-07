package com.withdog.order.bo;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.withdog.order.mapper.OrderedProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderedProductBO {

	private final OrderedProductMapper orderedProductMapper;
	
	public void addOrderedProduct(long orderId, Map<Integer, Integer> productIdAndCount) {
	
		orderedProductMapper.insertOrderedProductMapper(orderId, productIdAndCount);
	}
	
}
