package com.withdog.pet;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.pet.bo.PetBO;
import com.withdog.pet.dto.PetDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/pet")
@Controller
public class PetController {

	private final PetBO petBO;
	
	// http://localhost/pet/profile-view
	@GetMapping("/profile-view")
	public String profileView(Model model, HttpSession session) {
		Integer accountId = (Integer) session.getAttribute("accountId");
		if (ObjectUtils.isEmpty(accountId)) { // 로그인 확인
			return "redirect:/account/sign-in-view";
		}
		List<PetDTO> petList = petBO.getPetListByAccountId(accountId);
		if (!ObjectUtils.isEmpty(petList)) { 
			model.addAttribute("pet", petList);
			model.addAttribute("viewName", "pet/petProfile");
		} else { // 펫 등록
			return "redirect:/pet/add-view";
		}
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/pet/profile-edit-view
	@GetMapping("/profile-edit-view")
	public String profileEditView(Model model) {
		model.addAttribute("viewName", "pet/editPetProfile");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
	
	@GetMapping("/add-view")
	public String addView(Model model) {
		
		model.addAttribute("viewName", "pet/addPet");
		model.addAttribute("viewNameR", "include/rightSide");
		
		return "template/layout";
	}
}
