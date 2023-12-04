package com.withdog.cart.dto;

import java.util.Date;

import com.withdog.cart.entity.CartEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CartDTO {

	private int id;
	private int accountId;
	private int productId;
	private int count;
	private int price;
	private Date createdAt;
	private Date updatedAt;
	
	public static CartDTO toDTO(CartEntity entity) {
        return CartDTO.builder()
        		.id(entity.getId())
        		.accountId(entity.getAccountId())
        		.productId(entity.getProductId())
        		.count(entity.getCount())
        		.price(entity.getPrice())
        		.build();
	}
}
