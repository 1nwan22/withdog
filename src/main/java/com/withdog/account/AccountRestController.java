package com.withdog.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.account.bo.AccountBO;
import com.withdog.account.entity.AccountEntity;
import com.withdog.common.EncryptUtils;

@RequestMapping("/account")
@RestController
public class AccountRestController {

	@Autowired
	private AccountBO accountBO;
	
	@RequestMapping("/isDuplicatedEmail")
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
	
	@RequestMapping("/isDuplicatedUserId")
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
	
	@PostMapping("/signUp")
	public Map<String, Object> signUp(
			@RequestParam("email") String email, 
			@RequestParam("userId") String userId,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password) {

		//TODO  여유되면 다른 비밀번호식도 찾아보기
		String hashedPassword = EncryptUtils.md5(password);

		Integer id = accountBO.addUser(email, userId, userName, hashedPassword);

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
}
