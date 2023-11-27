package com.withdog.account.kakao;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Component
public class KakaoUser {

	private Long id;
	private KakaoAccount kakao_account;
	
    @Data
    public class KakaoAccount {
    	
    	private Boolean profile_nickname_needs_agreement;
    	
    	private Profile profile;
    	
    	private Boolean name_needs_agreement;
    	
    	private String name;
    	
    	private Boolean email_needs_agreement;
    	
    	private Boolean is_email_valid;
    	
    	private Boolean is_email_verified;
    	
    	private String email;
    	
    	@Data
    	public class Profile {
    		private String nickname;
    	}
    }
    

}
