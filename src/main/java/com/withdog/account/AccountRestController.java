package com.withdog.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.account.bo.AccountBO;
import com.withdog.account.entity.AccountEntity;
import com.withdog.common.EncryptUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountRestController {

	private final AccountBO accountBO;
	
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
		session.setAttribute("adminYn", account.getAdminYn());
		Map<String, Object> result = new HashMap<>();
		return result;
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
