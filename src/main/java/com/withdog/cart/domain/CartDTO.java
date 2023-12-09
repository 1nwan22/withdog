
package com.withdog.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartDTO {

	private int productId;
	
	private String productBrand;
	
	private String productName;
	
	private int price;
	
	private int count;
	
	
}
