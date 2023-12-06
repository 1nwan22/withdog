package com.withdog.cart.bo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.withdog.cart.dto.CartDTO;
import com.withdog.cart.entity.CartEntity;
import com.withdog.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartBO {

	private final CartRepository cartRepository;
	
	public CartEntity getCartByProductId(int productId) {
		return cartRepository.findByProductId(productId);
	}
	
	@Transactional
	public void addCart(int accountId, CartDTO cartDTO) {
		int productId =cartDTO.getProductId();
		int count = cartDTO.getCount();
		int price = cartDTO.getPrice();
		log.info("$$$$$$$$ accountId = {}  productId = {}", accountId, productId);
		
		CartEntity cart = cartRepository.findByProductId(productId);
		
		if (ObjectUtils.isEmpty(cart)) {
			 cart = cartRepository.save(
					CartEntity.builder()
					.accountId(accountId)
					.productId(productId)
					.count(count)
					.price(price)
					.build());
		} else { // 업데이트
			cart = cart.toBuilder() // 복사본 생성
					.count(count)
					.build();
					
			cart = cartRepository.save(cart); // 수정된 내용 저장
		}
		
		
	}
	

}
