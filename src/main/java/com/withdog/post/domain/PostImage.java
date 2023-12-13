package com.withdog.post.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostImage {

	private int id;
	private int postId;
	private String imagePath;
	private LocalDateTime createdAt;
}
