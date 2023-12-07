package com.withdog.account.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.withdog.account.entity.AccountEntity;
import com.withdog.account.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountBO {

	@Autowired
	private AccountRepository accountRepository;
	
	public AccountEntity getAccountEntityById(int id) {
		return accountRepository.findById(null).orElse(null);
	}
	public AccountEntity getAccountEntityByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
	public AccountEntity getAccountEntityByUserId(String userId) {
		return accountRepository.findByUserId(userId);
	}
	
	@Transactional
	public Integer addAccount(String email, String userId, String userName, String password) {
		AccountEntity accountEntity = accountRepository.save(
				AccountEntity.builder()
				.email(email)
				.userId(userId)
				.userName(userName)
				.password(password)
				.vetYn("n")
				.build());
		log.info("$$$$$$$$$$$$ accountEntity.getId() = {}", accountEntity.getId());
		// entity는 넣은 것을 바로 꺼내올 수 있다. 그리고 엔티티 전부보다는 id만 넘기는게 좋다
		return accountEntity == null ? null : accountEntity.getId();
	}
	
	@Transactional
	public AccountEntity addAccountKakaoOauth(String email, String userId) {
		AccountEntity accountEntity = accountRepository.save(
				AccountEntity.builder()
				.email(email)
				.userId(userId)
				.vetYn("n")
				.adminYn("n")
				.type("kakao")
				.build());
		log.info("$$$$$$$$$$$$ accountEntity = {}", accountEntity);
		return accountEntity == null ? null : accountEntity;
	}
	
	@Transactional
	public String updateAccountAdminYnByEmail(String email, String yN) {
		
		AccountEntity account = accountRepository.findByEmail(email);
		if (account != null) {
			account = account.toBuilder() // 복사본 생성
			.adminYn(yN)
			.build();
			
			account = accountRepository.save(account); // 수정된 내용 저장
		}
		return account.getAdminYn();
		
	}
}
