package com.withdog.order.bo;

import org.springframework.stereotype.Service;

import com.withdog.order.domain.OrderView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderViewBO {

	private final OrderedProductBO orderedProductBO;
	private final OrderBO orderBO;
	
	public OrderView getOrderView(int orderId) {
		OrderView orderView = new OrderView();
		orderView.setOrder(orderBO.getOrderDTO(orderId));
		orderView.setOrderedProductList(orderedProductBO.getOrderedProductDTOListByOrderId(orderId));
		
		return orderView;
	}
	
}
