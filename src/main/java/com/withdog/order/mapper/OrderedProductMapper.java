package com.withdog.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.withdog.order.domain.OrderedProduct;

@Repository
public interface OrderedProductMapper {
	
	public List<OrderedProduct> selectOrderedProductListByOrderId(int orderId);

	public void insertOrderedProductMapper(
			@Param("orderId") int orderId,
			@Param("productIdAndCount") Map<Integer, Integer> productIdAndCount);
}
