package com.withdog.account.kakao;

import javax.persistence.Column;

import lombok.Data;

@Data
public class KakaoUser {

	private Long id;
    private KakaoAccount kakaoAccount;
    
    @Data
    public class KakaoAccount {
    	
    	@Column(name="profileNicknameNeedsAgreement")
    	private Boolean profileNicknameNeedsAgreement;
    	
    	private Profile profile;
    	
    	@Column(name="nameNeedsAgreement")
    	private Boolean nameNeedsAgreement;
    	
    	private String name;
    	
    	@Column(name="emailNeedsAgreement")
    	private Boolean emailNeedsAgreement;
    	
    	@Column(name="isEmailValid")
    	private Boolean isEmailValid;
    	
    	@Column(name="isEmailVerified")
    	private Boolean isEmailVerified;
    	
    	private String email;
    	
    	@Data
    	public class Profile {
    		private String nickname;
    	}
    }
    

}
