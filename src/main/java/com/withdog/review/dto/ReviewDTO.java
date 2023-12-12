package com.withdog.review.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.annotations.UpdateTimestamp;

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
	
	private int accountId;
	
	private int productId;
	
	private double point;
	
	private String content;
	
	private String createdAt;
	
	private String updatedAt;
}
