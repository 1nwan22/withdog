package com.withdog.account.dto;

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
public class AccountDTO {

	private int id;
	
	private String email;
	
	private String userId;
	
	private String userName;
	
	private String phoneNumber;
	
	private String address;
	
	private int zipCode;
	
	private String introduce;
	
	private String vetYn;
	
	private String type;
}
