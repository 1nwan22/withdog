package com.withdog.account.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withdog.account.entity.AccountEntity;
import com.withdog.account.repository.AccountRepository;

@Service
public class AccountBO {

	@Autowired
	private AccountRepository accountRepository;
	
	public AccountEntity getAccountEntityByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
	public AccountEntity getAccountEntityByUserId(String userId) {
		return accountRepository.findByUserId(userId);
	}
	
	public Integer addAccount(String email, String userId, String userName, String password) {
		AccountEntity accountEntity = accountRepository.save(
				AccountEntity.builder()
				.email(email)
				.userId(userId)
				.userName(userName)
				.password(password)
				.build());
		
		// entity는 넣은 것을 바로 꺼내올 수 있다. 그리고 엔티티 전부보다는 id만 넘기는게 좋다
		return accountEntity == null ? null : accountEntity.getId();
	}
	
	public void addAccountOauth(String email) {
		
	}
	
	public String updateAccountAdminYnByEmail(String email, String adminYn) {
		
		AccountEntity account = accountRepository.findByEmail(email);
		if (account != null) {
			account.toBuilder() // 기존 값 유지
			.adminYn(adminYn)
			.build();
			
			account = accountRepository.save(account);
		}
		return account.getAdminYn();
		
	}
}
