package com.withdog.order.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderedProduct {

	private int id;
	private int orderId;
	private int productId;
	private int count;
	private Date createdAt;
}
