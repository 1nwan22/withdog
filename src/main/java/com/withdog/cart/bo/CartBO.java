package com.withdog.cart.bo;

import org.springframework.stereotype.Service;

import com.withdog.cart.dto.CartDTO;
import com.withdog.cart.entity.CartEntity;
import com.withdog.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartBO {

	private CartRepository cartRepository;
	
	public CartDTO addCart(CartDTO cartDTO) {
		// dto -> entity
		CartEntity entity = CartEntity.toEntity(cartDTO);
		log.info("$$$$$$$$$$$$$$ CartEntity = {}", entity);
		
		// save
		CartEntity saveEntity = cartRepository.save(entity);
		log.info("$$$$$$$$$$$$$$ saveCartEntity = {}", saveEntity);
		
		// entity -> dto
		CartDTO dto = CartDTO.toDTO(saveEntity);
		log.info("$$$$$$$$$$$$$$ CartDTO = {}", dto);
		
		return dto;
		
		
		
	}
	
	public Integer addCart(int accountId) {
		return 1;
	}
}
