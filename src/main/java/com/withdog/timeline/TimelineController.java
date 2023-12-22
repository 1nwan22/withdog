package com.withdog.timeline;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.post.bo.PostBO;
import com.withdog.post.domain.PostDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TimelineController {
	
	private final PostBO postBO;
	// http://localhost/timeline/timeline-view
	// http://localhost
	@GetMapping("")
	public String timelineView(Model model, HttpSession session,
			@PageableDefault(size = 16, sort = "id" ,direction = Sort.Direction.DESC) Pageable pageable
			) {
		session.setAttribute("page", pageable.getPageNumber());
		Slice<PostDTO> postList = postBO.generatePostSlice(pageable);
		log.info("$$$$$$$$$$$$$$$$ postList={}", postList);
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "timeline/home");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}

}
