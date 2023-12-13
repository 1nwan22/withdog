package com.withdog.post.domain;

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
public class PostDTO {
	
	private int id;
	
	private int accountId;
	
	private String content;
	
	private String createdAt;
	
	private String updatedAt;

}
