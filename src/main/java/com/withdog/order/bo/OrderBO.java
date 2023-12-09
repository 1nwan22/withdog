package com.withdog.order.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import com.withdog.order.domain.OrderDTO;
import com.withdog.order.domain.OrderedProduct;
import com.withdog.order.entity.OrderEntity;
import com.withdog.order.repository.OrderRepository;
import com.withdog.product.bo.ProductBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderBO {

	private final OrderRepository orderRepository;
	private final OrderedProductBO orderedProductBO;
	private final ProductBO productBO;
	
	public void addOrder(
			int accountId, List<Map<String, Object>> productIdAndCountJson) {
		
		int orderId = createOrder(accountId);
		log.info("$$$$$$$$$$$$$$ orderId = {}", orderId);
		orderedProductBO.addOrderedProduct(orderId, productIdAndCountJson);
	}

	private int createOrder(int accountId) {
		OrderEntity orderEntity = orderRepository.save(
				OrderEntity.builder()
				.accountId(accountId)
				.build());

		log.info("$$$$$$$$$$$ orderEntity = {}", orderEntity);

		return orderEntity.getId();
	}
	
	
	
	public List<OrderDTO> getOrderDTOList(int orderId) {
		List<OrderDTO> orderDTOList = new ArrayList<>();
		List<OrderedProduct> orderedProductList = orderedProductBO.getOrderedProductListByOrderId(orderId);
		for (OrderedProduct op : orderedProductList) {
			int productId = (int) op.getProductId();
			int count = (int) op.getCount();
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrder(orderRepository.findById(orderId).orElse(null));
			orderDTO.setProduct(productBO.getProductDTOById(productId));
			orderDTO.setCount(count);
			orderDTOList.add(orderDTO);
		}
		
		return orderDTOList;
	}
	


}
