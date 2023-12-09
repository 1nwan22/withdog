package com.withdog.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="product_order")
@Entity
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="accountId")
	private int accountId;
	
	@Column(name="receiverName")
	private String receiverName;
	
	@Column(name="receiverAddress")
	private String receiverAddress;
	
	@Column(name="receiverPhoneNumber")
	private String receiverPhoneNumber;
	
	private String request;
	
	@Column(name="paymentMethodType")
	private String paymentMethodType;
	
	private String card;
	
	@Column(name="shippingPrice")
	private int shippingPrice;
	
	@Column(name="totalPrice")
	private int totalPrice;
	
	private String status;
	
	@Column(name="payAt")
	private Date payAt;
	
	@Column(name="departAt")
	private Date departAt;
	
	@Column(name="arrivalAt")
	private Date arrivalAt;
	
	@UpdateTimestamp
	@Column(name="createdAt", updatable = false)
	private Date createdAt;
}
