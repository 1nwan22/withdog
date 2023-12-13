package com.withdog.post.bo;


import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.post.domain.PostDTO;
import com.withdog.post.entity.PostEntity;
import com.withdog.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostBO {
	
	private final PostRepository postRepository;
	private final PostImageBO postImageBO;
	
	public Integer addPost(int accountId, String content, List<MultipartFile> imageList) {
		PostEntity post = postRepository.save(
				PostEntity.builder()
				.accountId(accountId)
				.content(content)
				.build());
		log.info("$$$$$$$$$$ post = {}", post);
		postImageBO.addPostImage(post.getId(), imageList);
		
		return post == null ? null : post.getId();
	}
	
	public PostDTO getPostDTOById(int id) {
		PostEntity postEntity = postRepository.findById(id).orElse(null);
		if (ObjectUtils.isEmpty(postEntity)) {
			return null;
		}
		PostDTO postDTO = new PostDTO();
		postDTO.setId(postEntity.getId());
		postDTO.setAccountId(postEntity.getAccountId());
		postDTO.setContent(postEntity.getContent());
		String createdAt = postEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String updatedAt = postEntity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		postDTO.setCreatedAt(createdAt);
		postDTO.setUpdatedAt(updatedAt);
		
		return postDTO;
	}

}
