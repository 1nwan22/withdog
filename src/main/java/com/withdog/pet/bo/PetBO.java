package com.withdog.pet.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.common.FileManagerService;
import com.withdog.pet.dto.PetDTO;
import com.withdog.pet.entity.PetEntity;
import com.withdog.pet.repository.PetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PetBO {

	private final PetRepository petRepository;
	private final FileManagerService fms;
	
	public void addPet(
			int accountId, String name, 
			int age, String species, String gender, 
			String neutralization, MultipartFile profileImage) {
		String profileImagePath = null;
		if(!ObjectUtils.isEmpty(profileImage)) {
			profileImagePath = fms.savePetProfileFile(name, profileImage);
		}
		log.info("$$$$$$$$$$$$$$$$ profileImagePath = {}", profileImagePath);
		
		PetEntity pet = petRepository.save(
				PetEntity.builder()
				.accountId(accountId)
				.name(name)
				.age(age)
				.gender(gender)
				.neutralization(neutralization)
				.profileImagePath(profileImagePath)
				.build());
		
		log.info("$$$$$$$$$$$$$$$$$$$$$$ pet = {}", pet);
	}
	
	public List<PetDTO> getPetListByAccountId(int accountId) {
		List<PetEntity> petEntityList = petRepository.findByAccountId(accountId);
		List<PetDTO> petList = new ArrayList<>(petEntityList.size());
		
		for (PetEntity petEntity : petEntityList) {
			PetDTO pet = new PetDTO();
			
			pet.setId(petEntity.getId());
			pet.setAccountId(petEntity.getAccountId());
			pet.setName(petEntity.getName());
			pet.setAge(petEntity.getAge());
			pet.setSpecies(petEntity.getSpecies());
			pet.setGender(petEntity.getGender());
			pet.setNeutralization(petEntity.getNeutralization());
			pet.setProfileImagePath(petEntity.getProfileImagePath());
			
			petList.add(pet);
		}
		
		return petList;
	}
	
	public void editPetById(PetDTO pet) {
		PetEntity petEntity = petRepository.findById(pet.getId()).orElse(null);
		
		if(!ObjectUtils.isEmpty(petEntity)) {
			petEntity = petEntity.toBuilder()
					.name(pet.getName())
					.age(pet.getAge())
					.species(pet.getSpecies())
					.gender(pet.getGender())
					.neutralization(pet.getNeutralization())
					.profileImagePath(pet.getProfileImagePath())
					.build();
			petEntity = petRepository.save(petEntity);
					
		}
	}
}
