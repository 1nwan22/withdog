package com.withdog.account.bo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.post.bo.PostBO;
import com.withdog.post.bo.PostImageBO;
import com.withdog.post.domain.PostDTO;
import com.withdog.post.entity.PostEntity;
import com.withdog.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
class BOTest {

	private final PostRepository postRepository;
	private final PostBO postBO;
	private final PostImageBO postImageBO;
	
	//@Test
	Slice<PostEntity> generatePostEntitySlice(Pageable pageable) {
		log.info("$$$$$$$$$$$$$$$$$ {}", postRepository.findAllByOrderByIdDesc(pageable));
		return postRepository.findAllByOrderByIdDesc(pageable);
	}
	
	@Test
	public Slice<PostDTO> generatePostSlice(Pageable pageable) {
	    List<PostEntity> postEntityList = postRepository.findAll();

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

	    boolean hasNext = false;
	    if (content.size() > pageable.getPageSize()) {
	        content.remove(pageable.getPageSize());
	        hasNext = true;
	    }
	    log.info("$$$$$$ {}", content);
	    return new SliceImpl<>(content, pageable, hasNext);
	}
	

}
