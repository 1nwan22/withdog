package com.withdog.review.dto;

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
public class ReviewDTO {

	private int id;
	
	private String userId;
	
	private int productId;
	
	private int point;
	
	private String content;
	
	private String createdAt;
	
	private String updatedAt;
}
