package com.withdog.pet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.withdog.pet.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Integer> {
	
	public List<PetEntity> findByAccountId(int accountId);
	
	public Page<PetEntity> findAllByAccountId(Pageable pageable, int accountId);

}
