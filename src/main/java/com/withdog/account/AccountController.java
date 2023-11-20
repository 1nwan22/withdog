package com.withdog.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.account.kakao.KakaoBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/account")
@Controller
public class AccountController {

	private final KakaoBO kakaoBO;
	
	// http://localhost/account/sign-in
	@GetMapping("/sign-in")
	public String signIn(Model model) {
		// 카카오 
		model.addAttribute("REST_API_KEY", kakaoBO.REST_API_KEY);
		model.addAttribute("REDIRECT_URI", kakaoBO.REDIRECT_URI);
		
		model.addAttribute("viewName", "/account/signIn");
		return "template/layout";
	}
	
	// http://localhost/account/sign-up
	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("viewName", "/account/signUp");
		return "template/layout";
	}
	
	// http://localhost/account/kakao/oauth
	
	@GetMapping("/kakao/oauth")
	public String kakaoOauth(@RequestParam("code") String code, Model model) {
		
		model.addAttribute("viewName", "/admin/dashBoard");
		kakaoBO.getUser(kakaoBO.getAccessToken(code).getAccess_token());
		return "template/layout";
	}

}
