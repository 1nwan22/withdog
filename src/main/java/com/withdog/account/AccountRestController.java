package com.withdog.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.account.bo.AccountBO;
import com.withdog.account.entity.AccountEntity;
import com.withdog.account.kakao.KakaoBO;
import com.withdog.account.kakao.KakaoToken;
import com.withdog.account.kakao.KakaoUser;
import com.withdog.common.EncryptUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountRestController {

	private final AccountBO accountBO;
	private final KakaoBO kakaoBO;
	
	@RequestMapping("/is-duplicated-email")
	public Map<String, Object> isDuplicatedEmail(@RequestParam("email") String email) {
		
		AccountEntity account = accountBO.getAccountEntityByEmail(email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		if (account == null) {
			result.put("isDuplicated", false);
		} else {
			result.put("isDuplicated", true);
		}

		return result;
	}
	
	@RequestMapping("/is-duplicated-user-id")
	public Map<String, Object> isDuplicatedUserId(@RequestParam("userId") String userId) {
		
		AccountEntity account = accountBO.getAccountEntityByUserId(userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		if (account == null) {
			result.put("isDuplicated", false);
		} else {
			result.put("isDuplicated", true);
		}

		return result;
	}
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("email") String email, 
			@RequestParam("userId") String userId,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password) {

		//TODO  여유되면 다른 비밀번호식도 찾아보기
		String hashedPassword = EncryptUtils.md5(password);

		Integer id = accountBO.addAccount(email, userId, userName, hashedPassword);

		Map<String, Object> result = new HashMap<>();
		if (id == null) {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 실패");
		} else {
			result.put("code", 200);
			result.put("result", "success");
		}
		
		return result;
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session) {
		session.setAttribute("email", email);
		AccountEntity account = accountBO.getAccountEntityByEmail(email);
		session.setAttribute("accountId", account.getId());
		session.setAttribute("userId", account.getUserId());
		Map<String, Object> result = new HashMap<>();
		return result;
	}
	
	@GetMapping("/kakao-oauth")
	public void kakaoOauth(@RequestParam("code") String code, HttpSession session, HttpServletResponse response) {
	    log.info("$$$$$$$$$$ code={}", code);
	    
	    KakaoToken token = kakaoBO.getAccessToken(code);
	    log.info("$$$$$$$$$$$$ kakaoToken={}", token);

	    if (token == null) {
	        log.error("@@@@@@@@@@@@@@@@@ code={}", code);
	    }

	    KakaoUser user = kakaoBO.getUserByToken(token.getAccess_token());
	    log.error("$$$$$$$$$$$$ kakaoUser = {}", user);
	    
	    AccountEntity account = kakaoBO.addAccount(user);
	    session.setAttribute("email", account.getEmail());
	    session.setAttribute("adminYn", account.getAdminYn());
	    
	    try {
			response.sendRedirect("http://localhost");
		} catch (IOException e) {
			log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@ error kakaoAccount 없음");
		}
	    
	}
	
	
	@PutMapping("/admin-permission")
	public Map<String, Object> adminPermission(@RequestParam("email") String email) {
		String adminYn = accountBO.updateAccountAdminYnByEmail(email, "y");
		Map<String, Object> result = new HashMap<>();
		if (adminYn.equals("n")) {
			result.put("code", 500);
			result.put("errorMessage", "관리자 승인 실패");
			return result;
		}
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
}
