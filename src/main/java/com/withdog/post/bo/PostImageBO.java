package com.withdog.post.bo;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.common.FileManagerService;
import com.withdog.post.domain.PostImage;
import com.withdog.post.domain.PostImageDTO;
import com.withdog.post.mapper.PostImageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostImageBO {
	
	private final PostImageMapper postImageMapper;
	private final FileManagerService fms;

	@Transactional
	public void addPostImage(int postId, List<MultipartFile> imageList) {
		String imagePath = null;
		List<String> imagePathList = new ArrayList<>(imageList.size());
		
		if (!ObjectUtils.isEmpty(imageList)) {
			for (MultipartFile image : imageList) {
				imagePath = fms.saveImageFile(postId, image);
				log.info("$$$$$$$$$$$$$$$$ imagePath={}", imagePath);
				imagePathList.add(imagePath);
			}
		}
		
		postImageMapper.insertPostImage(postId, imagePathList);
	}
	
	public PostImageDTO getImageByPostId(int postId) {
		PostImage postImage = postImageMapper.selectImageByPostId(postId);
		PostImageDTO postImageDTO = new PostImageDTO();
		
		postImageDTO.setId(postImage.getId());
		postImageDTO.setPostId(postImage.getPostId());
		postImageDTO.setImagePath(postImage.getImagePath());
		String createdAt = postImage.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		postImageDTO.setCreatedAt(createdAt);
		
		return postImageDTO;
	}
	
	public List<PostImageDTO> getImageListByPostId(int postId) {
		List<PostImage> postImageList = postImageMapper.selectImageListByPostId(postId);
		List<PostImageDTO> postImageDTOList = new ArrayList<>(postImageList.size());
		
		for (PostImage postImage : postImageList) {
			PostImageDTO postImageDTO = new PostImageDTO();
			
			postImageDTO.setId(postImage.getId());
			postImageDTO.setPostId(postImage.getPostId());
			postImageDTO.setImagePath(postImage.getImagePath());
			String createdAt = postImage.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			postImageDTO.setCreatedAt(createdAt);
			
			postImageDTOList.add(postImageDTO);
		}
		
		return postImageDTOList;
	}
}
