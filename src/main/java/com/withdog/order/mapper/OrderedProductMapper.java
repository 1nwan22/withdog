package com.withdog.order.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductMapper {

	public void insertOrderedProductMapper(
			@Param("orderId") long orderId,
			@Param("productIdAndCount") Map<Integer, Integer> productIdAndCount);
}
