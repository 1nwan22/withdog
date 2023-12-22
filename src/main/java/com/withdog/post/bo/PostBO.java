package com.withdog.post.bo;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
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
	
	public List<PostDTO> getPostList() {
		List<PostEntity> postEntityList = postRepository.findAll();
		List<PostDTO> postList = new ArrayList<>(postEntityList.size());
		
		for (PostEntity postEntity : postEntityList) {
			PostDTO post = new PostDTO();
			post.setId(postEntity.getId());
			post.setAccountId(postEntity.getAccountId());
			post.setImagePath(postImageBO.getImageByPostId(postEntity.getId()).getImagePath());
			post.setContent(postEntity.getContent());
			post.setCreatedAt(postEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			post.setUpdatedAt(postEntity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			postList.add(post);
		}
		return postList;
	}
	
	public Slice<PostDTO> generatePostSlice(Pageable pageable) {
	    Slice<PostEntity> postEntitySlice = postRepository.findAllByOrderByIdDesc(pageable);
	    List<PostEntity> postEntityList = postEntitySlice.getContent();
	    List<PostDTO> content = new ArrayList<>();
	    for (PostEntity postEntity : postEntityList) {
	        content.add(new PostDTO(
	            postEntity.getId(),
	            postEntity.getAccountId(),
	            postImageBO.getImageByPostId(postEntity.getId()).getImagePath(),
	            postEntity.getContent(),
	            postEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
	            postEntity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	        ));
	    }

	    
	    return new SliceImpl<>(content, pageable, postEntitySlice.hasNext());
	}
	
	public Slice<PostEntity> generatePostEntitySlice(Pageable pageable) {
		return postRepository.findAllByOrderByIdDesc(pageable);
	}

}
