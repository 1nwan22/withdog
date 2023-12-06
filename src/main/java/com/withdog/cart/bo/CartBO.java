package com.withdog.cart.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.withdog.cart.dto.CartDTO;
import com.withdog.cart.entity.CartEntity;
import com.withdog.cart.repository.CartRepository;
import com.withdog.product.bo.ProductBO;
import com.withdog.product.entity.ProductEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartBO {

	private final CartRepository cartRepository;
	private final ProductBO productBO;
	
	public CartEntity getCartByProductId(int productId) {
		return cartRepository.findByProductId(productId);
	}
	
	public List<CartDTO> getCartListByAccountId(int accountId) {
		List<CartEntity> cartList = cartRepository.findAllByAccountId(accountId);
		log.info("$$$$$$$$$$$ cartList = {}", cartList);
		List<CartDTO> cartDTOList = new ArrayList<>(cartList.size());
		for (CartEntity cart : cartList) {
			ProductEntity product = productBO.getProductById(cart.getProductId());
			CartDTO cartDTO = new CartDTO(product.getId(), product.getBrand(), product.getName(), product.getPrice(), cart.getCount());
			cartDTOList.add(cartDTO);
		}
		return cartDTOList;
	}
	
	@Transactional
	public void addCart(int accountId, Map<Integer, Integer> productIdAndCount) {
		productIdAndCount.forEach((productId, count) -> {
	        CartEntity cart = cartRepository.findByProductId(productId);
	        if (!ObjectUtils.isEmpty(cart)) { // 기존 상품이 카트에 있으면 업데이트
	            cart = cart.toBuilder()
	            		.count(count)
	            		.build();
	            cart = cartRepository.save(cart);
	            		
	        } else {
	        	try {
	                cartRepository.save(
	                        CartEntity.builder()
	                                .accountId(accountId)
	                                .productId(productId)
	                                .count(count)
	                                .build());
	            } catch (Exception e) {
	            	log.error("@@@@@@@@@@@@@@@ CartEntity 저장 중 오류 발생 {}", e.getMessage());
	            }
	        
	        }
	    });
		
	}
	

}
