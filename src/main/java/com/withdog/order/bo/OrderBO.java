package com.withdog.order.bo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.withdog.order.domain.OrderDTO;
import com.withdog.order.domain.OrderedProductDTO;
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
		List<OrderedProductDTO> orderedProductDTOList = orderedProductBO.getOrderedProductDTOListByOrderId(orderId);
		int shippingPrice = 0;
		int totalPrice = 0;
		for (OrderedProductDTO opdto : orderedProductDTOList) {
			int price = opdto.getPrice();
			int count = opdto.getCount();
			totalPrice += price * count;
		}
		if (totalPrice < 40000) {
			shippingPrice = 3000; 
		}
		
		editOrder(orderId, shippingPrice, totalPrice);
		
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
	
	public void editOrder(int id, int shippingPrice, int totalPrice) {
		OrderEntity order = orderRepository.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(order)) {
			order = order.toBuilder()
					.shippingPrice(shippingPrice)
					.totalPrice(totalPrice)
					.build();
			
			order = orderRepository.save(order);
		}
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
