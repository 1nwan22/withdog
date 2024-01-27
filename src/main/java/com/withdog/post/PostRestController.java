package com.withdog.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.post.bo.PostBO;
import com.withdog.post.domain.PostDTO;

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
	
	@GetMapping("/load")
	public Map<String, Object> loadMorePosts(HttpSession session,
			@PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
			) {
		int page = (int) session.getAttribute("page");
		page += 1;
		session.setAttribute("page", page);
		log.info("$$$$$$$$$ page = {}", page);
		pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
		Slice<PostDTO> postSlice = postBO.generatePostSlice(pageable);
		Map<String, Object> result = new HashMap<>();
		result.put("postSlice", postSlice);
		return result;
	}
	
}
