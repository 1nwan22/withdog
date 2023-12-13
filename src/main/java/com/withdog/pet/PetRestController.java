package com.withdog.pet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.pet.bo.PetBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pet")
@RestController
public class PetRestController {
	
	private final PetBO petBO;

	@PostMapping("/add")
	public Map<String, Object> petAdd(HttpSession session,
			@RequestParam(name="name") String name,
			@RequestParam(name="age", required=false) int age,
			@RequestParam(name="species", required=false) String species,
			@RequestParam(name="gender", required=false) String gender,
			@RequestParam(name="neutralization", required=false) String neutralization,
			@RequestParam(name="profileImagePath", required=false) String profileImagePath,
			@RequestPart(name="profileImage", required=false) MultipartFile profileImage) {
		Map<String, Object> result = new HashMap<>();
		Integer accountId = (Integer) session.getAttribute("accountId");
		if (ObjectUtils.isEmpty(accountId)) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 후 이용하세요");
			return result;
		}
		petBO.addPet(accountId, name, age, species, gender, neutralization, profileImage);
		
		result.put("code", 200);
		result.put("result", "success");
		
		
		return result;
		
	}
	
	
}
