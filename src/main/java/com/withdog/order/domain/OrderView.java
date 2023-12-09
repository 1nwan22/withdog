package com.withdog.order.domain;

import java.util.List;

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
public class OrderView {

	private OrderDTO order;
	private List<OrderedProductDTO> orderedProductList;
}
