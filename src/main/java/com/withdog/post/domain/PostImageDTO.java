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
public class PostImageDTO {
	
	private int id;
	private int postId;
	private String imagePath;
	private String createdAt;
}
