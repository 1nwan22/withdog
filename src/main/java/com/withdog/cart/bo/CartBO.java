package com.withdog.cart.bo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.withdog.cart.entity.CartEntity;
import com.withdog.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartBO {

	private final CartRepository cartRepository;
	
	@Transactional
	public void addCart(int accountId, int productId, int count, int price) {
		log.info("$$$$$$$$ accountId = {}  productId = {}", accountId, productId);
		
		CartEntity cart = cartRepository.save(
				CartEntity.builder()
				.accountId(accountId)
				.productId(productId)
				.count(count)
				.price(price)
				.build());
		
	}
	

}
