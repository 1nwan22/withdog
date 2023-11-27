package com.withdog.account.kakao;

import javax.persistence.Column;

import lombok.Data;

@Data
public class KakaoToken {

	@Column(name="tokenType")
	private String tokenType;
	
	@Column(name="accessToken")
	private String accessToken;
	
	@Column(name="idToken")
	private String idToken;
	
	@Column(name="expiresIn")
	private Integer expiresIn;
	
	@Column(name="refreshToken")
	private String refreshToken;
	
	@Column(name="refreshTokenRxpiresIn")
	private Integer refreshTokenRxpiresIn;
	
	private String scope;

}
