package com.withdog.order.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.withdog.order.domain.OrderedProduct;
import com.withdog.order.mapper.OrderedProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderedProductBO {

	private final OrderedProductMapper orderedProductMapper;
	
	public List<OrderedProduct> getOrderedProductListByOrderId(int orderId) {
		return orderedProductMapper.selectOrderedProductListByOrderId(orderId);
	}
	
	public void addOrderedProduct(int orderId, List<Map<String, Object>> productIdAndCountJson) {
		Map<Integer, Integer> productIdAndCount = new HashMap<>(productIdAndCountJson.size());
		for (Map<String, Object> pac : productIdAndCountJson) {
			int productId = (int) pac.get("productId");
			int count = (int) pac.get("count");
			
			productIdAndCount.put(productId, count);
		}
		
		orderedProductMapper.insertOrderedProductMapper(orderId, productIdAndCount);
	}
	
}
