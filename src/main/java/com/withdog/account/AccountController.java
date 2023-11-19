package com.withdog.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountController {

	// http://localhost/account/sign-in
	@GetMapping("/sign-in")
	public String signIn(Model model) {
		model.addAttribute("viewName", "/account/signIn");
		return "template/layout";
	}
	
	// http://localhost/account/sign-up
	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("viewName", "/account/signUp");
		return "template/layout";
	}
	
}
