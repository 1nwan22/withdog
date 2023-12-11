package com.withdog.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.withdog.account.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

	public AccountEntity findByEmail(String email);
	
	public AccountEntity findByUserId(String userId);
	
}
