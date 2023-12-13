package com.withdog.post.domain;

import java.time.LocalDateTime;

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
public class PostImage {

	private int id;
	private int postId;
	private String imagePath;
	private LocalDateTime createdAt;
}
