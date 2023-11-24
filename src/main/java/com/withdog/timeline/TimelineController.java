package com.withdog.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimelineController {
	
	// http://localhost/timeline/timeline-view
	// http://localhost
	@GetMapping("")
	public String timelineView(Model model) {
		model.addAttribute("viewName", "/timeline/home");
		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}

}
