package com.withdog.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.withdog.post.domain.PostImage;

@Repository
public interface PostImageMapper {

	public void insertPostImage(
			@Param("postId") int postId,
			@Param("imagePathList") List<String> imagePathList);
	
	public PostImage selectImageByPostId(int postId);
	
	public List<PostImage> selectImageListByPostId(int postId);
}
