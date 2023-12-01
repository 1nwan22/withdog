package com.withdog.pet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pet")
@Controller
public class PetController {

	// http://localhost/pet/profile-view
	@GetMapping("/profile-view")
	public String profileView(Model model) {
		model.addAttribute("viewName", "pet/petProfile");
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
}
