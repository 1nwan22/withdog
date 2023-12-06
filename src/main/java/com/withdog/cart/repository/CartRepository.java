package com.withdog.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.withdog.cart.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

	public CartEntity findByProductId(int productId);
	
	public List<CartEntity> findAllByAccountId(int accountId);
}
