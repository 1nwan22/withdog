package com.withdog.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	// http://localhost/account/admin/dash-board
	@GetMapping("/dash-board")
	public String dashBoard(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email.equals("pepper@pepper.com")) {
			model.addAttribute("viewName", "/admin/dashBoard");
			return "template/layout";
		}
		
		return "template/layout";
	}
}
