package com.withdog.account.kakao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Setter
@Getter
public class KakaoUser {

	private Long id;
	private KakaoAccount kakao_account;
	
	@ToString
	@Setter
	@Getter
    public class KakaoAccount {
    	
    	private Boolean profile_nickname_needs_agreement;
    	
    	private Profile profile;
    	
    	private Boolean name_needs_agreement;
    	
    	private String name;
    	
    	private Boolean email_needs_agreement;
    	
    	private Boolean is_email_valid;
    	
    	private Boolean is_email_verified;
    	
    	private String email;
    	
    	@ToString
    	@Setter
    	@Getter
    	public class Profile {
    		private String nickname;
    	}
    }
    

}
