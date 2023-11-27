package com.withdog.account;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.account.kakao.KakaoBO;
import com.withdog.account.kakao.KakaoToken;
import com.withdog.account.kakao.KakaoUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/account")
@Controller
public class AccountController {

	private final KakaoBO kakaoBO;
	
	// http://localhost/account/sign-in-view
	@GetMapping("/sign-in-view")
	public String signInView(Model model, HttpSession session) {
		// 카카오 
		model.addAttribute("REST_API_KEY", kakaoBO.REST_API_KEY);
		model.addAttribute("REDIRECT_URI", kakaoBO.REDIRECT_URI);
		
		model.addAttribute("viewName", "/account/signIn");
		
//		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/account/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "/account/signUp");
//		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		session.removeAttribute("email");
		session.removeAttribute("adminYn");
		return "redirect:/account/sign-in-view";
	}
	
	@GetMapping("/kakao/oauth")
	public String kakaoOauth(@RequestParam("code") String code, HttpSession session) {
		KakaoToken kakaoToken  = kakaoBO.getAccessTokenResponse(code);
		if (ObjectUtils.isEmpty(kakaoToken)) {
			kakaoToken = kakaoBO.getRefreshTokenResponse(kakaoToken.getRefreshToken());
		}
		KakaoUser kakaoUser = kakaoBO.getUser(kakaoToken.getAccessToken());
		
		kakaoBO.addAccount(kakaoUser);
		
		
		return "redirect:/";
	}

	


}
