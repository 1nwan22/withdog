package com.withdog.order.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.withdog.order.domain.OrderedProduct;
import com.withdog.order.domain.OrderedProductDTO;
import com.withdog.order.mapper.OrderedProductMapper;
import com.withdog.product.bo.ProductBO;
import com.withdog.product.domain.ProductDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderedProductBO {

	private final OrderedProductMapper orderedProductMapper;
	private final ProductBO productBO;
	
	public void addOrderedProduct(int orderId, List<Map<String, Object>> productIdAndCountJson) {
		Map<Integer, Integer> productIdAndCount = new HashMap<>(productIdAndCountJson.size());
		for (Map<String, Object> pac : productIdAndCountJson) {
			int productId = (int) pac.get("productId");
			int count = (int) pac.get("count");
			
			productIdAndCount.put(productId, count);
		}
		
		orderedProductMapper.insertOrderedProductMapper(orderId, productIdAndCount);
	}
	
	public List<OrderedProductDTO> getOrderedProductDTOListByOrderId(int orderId) {
		List<OrderedProduct> opl = orderedProductMapper.selectOrderedProductListByOrderId(orderId);
		log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ OrderedProductList = {}", opl);
		List<OrderedProductDTO> orderedProductDTOList = new ArrayList<>(opl.size());
		for (OrderedProduct op : opl) {
			OrderedProductDTO opd = new OrderedProductDTO();
			opd.setOrderId(orderId);
			ProductDTO pd = productBO.getProductDTOById(op.getProductId());
			log.info("$$$$$$$$$$$$$$$$ ProductDTO = {}", pd);
			opd.setProductId(pd.getId());
			opd.setBrand(pd.getBrand());
			opd.setName(pd.getName());
			opd.setPrice(pd.getPrice());
			opd.setStock(pd.getStock());
			opd.setStatus(pd.getStatus());
			opd.setProductImagePath(pd.getProductImagePath());
			opd.setCount(op.getCount());
			orderedProductDTOList.add(opd);
		}
		
		return orderedProductDTOList;
	}
}
