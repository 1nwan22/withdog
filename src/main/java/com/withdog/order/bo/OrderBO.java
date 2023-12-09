package com.withdog.order.bo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.withdog.order.domain.OrderDTO;
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
	
	public int addOrder(
			int accountId, List<Map<String, Object>> productIdAndCountJson) {
		
		int orderId = createOrder(accountId);
		log.info("$$$$$$$$$$$$$$ orderId = {}", orderId);
		orderedProductBO.addOrderedProduct(orderId, productIdAndCountJson);
		return orderId;
	}

	private int createOrder(int accountId) {
		OrderEntity orderEntity = orderRepository.save(
				OrderEntity.builder()
				.accountId(accountId)
				.status("결제전")
				.build());

		log.info("$$$$$$$$$$$ orderEntity = {}", orderEntity);

		return orderEntity.getId();
	}
	
	
	public OrderDTO getOrderDTO(int orderId) {
		OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(orderId);
		orderDTO.setAccountId(orderEntity.getAccountId());
		orderDTO.setReceiverName(orderEntity.getReceiverName());
		orderDTO.setReceiverAddress(orderEntity.getReceiverAddress());
		orderDTO.setReceiverPhoneNumber(orderEntity.getReceiverPhoneNumber());
		orderDTO.setRequest(orderEntity.getRequest());
		orderDTO.setPaymentMethodType(orderEntity.getPaymentMethodType());
		orderDTO.setCard(orderEntity.getCard());
		orderDTO.setShippingPrice(orderEntity.getShippingPrice());
		orderDTO.setTotalPrice(orderEntity.getTotalPrice());
		orderDTO.setStatus(orderEntity.getStatus());
		orderDTO.setPayAt(orderEntity.getPayAt());
		orderDTO.setDepartAt(orderEntity.getDepartAt());
		orderDTO.setArrivalAt(orderEntity.getArrivalAt());
		
		return orderDTO;
		
	}
	

}
