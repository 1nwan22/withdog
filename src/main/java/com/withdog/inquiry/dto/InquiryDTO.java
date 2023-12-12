package com.withdog.inquiry.dto;

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
public class InquiryDTO {

	private int id;
	
	private int accountId;
	
	private int productId;
	
	private String category;
	
	private String subject;
	
	private String content;
	
	private int secret;
	
	private String status;
	
	private String reply;
	
	private String createdAt;
}
