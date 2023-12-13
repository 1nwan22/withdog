package com.withdog.pet.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder=true)
@Table(name="pet")
@Entity
public class PetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="accountId")
	private int accountId;
	
	private String name;
	
	private int age;
	
	private String species;
	
	private String gender;
	
	private String neutralization;
	
	@Column(name="profileImagePath")
	private String profileImagePath;
	
	private String introduce;
	
	@UpdateTimestamp
	@Column(name="createdAt", updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
}
