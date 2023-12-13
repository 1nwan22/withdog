package com.withdog.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PetDTO {
	
	private int id;
	
	private int accountId;
	
	private String name;
	
	private int age;
	
	private String species;
	
	private String gender;
	
	private String neutralization;
	
	private String profileImagePath;

}
