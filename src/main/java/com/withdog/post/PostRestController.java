package com.withdog.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.post.bo.PostBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostRestController {

	private final PostBO postBO;
	
	@PostMapping("/add") 
	public Map<String, Object> addPost(
			@RequestParam String content,
			@RequestPart("imageList") List<MultipartFile> imageList) {
		Map<String, Object> result = new HashMap<>();
		
		
		return result;
	}
	
}
