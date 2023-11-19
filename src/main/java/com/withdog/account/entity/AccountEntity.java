package com.withdog.account.entity;

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
@Getter
@Builder
@Table(name="account")
@Entity
public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="userName")
	private String userName;
	
	private String password;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	private String address;
	
	@Column(name="zipCode")
	private int zipCode;
	
	private String introduce;
	
	@Column(name="vetYn")
	private String vetYn;
	
	@UpdateTimestamp
	@Column(name="createdAt", updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Date updatedAt;
	
}
