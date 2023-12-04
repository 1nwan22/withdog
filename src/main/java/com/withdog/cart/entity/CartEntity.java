package com.withdog.cart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.UpdateTimestamp;

import com.withdog.cart.dto.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="cart")
@Entity
public class CartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="accountId")
	private int accountId;
	
	@Column(name="productId")
	private int productId;
	
	private int count;
	
	private int price;
	
	@UpdateTimestamp
	@Column(name="createdAt", updatable=false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Date updatedAt;
	
	public static CartEntity toEntity(CartDTO dto) {
		log.info("$$$$$$$$$$$$$$$$ CartDTO = {}", dto);
        return CartEntity.builder()
        		.id(dto.getId())
        		.accountId(dto.getAccountId())
        		.productId(dto.getProductId())
        		.count(dto.getCount())
        		.price(dto.getPrice())
        		.build();
        		
        		
    }

}
