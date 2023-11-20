package com.withdog.account.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AccountBOTest {

	@Autowired
	AccountBO accountBO;
	
	@Transactional // junit: db에 들어가는 거 롤백
	@Test
	void addusertest() {
		accountBO.addAccount("test@test.com", "test", "test", "test");
	}

}
