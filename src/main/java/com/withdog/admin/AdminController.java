package com.withdog.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	// http://localhost/admin/dash-board
	@GetMapping("dash-board")
	public String dashBoard(Model model) {

		model.addAttribute("viewName", "/admin/dashBoard");
		return "template/layout";
	}

}
