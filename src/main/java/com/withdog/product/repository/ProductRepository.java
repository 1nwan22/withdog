package com.withdog.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.withdog.product.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	
	public List<ProductEntity> findByName(String name);
	public List<ProductEntity> findByBrand(String brand);
	public List<ProductEntity> findByPrice(int price);
	public List<ProductEntity> findByCostPrice(int costPrice);
	public List<ProductEntity> findByStock(int stock);

}
