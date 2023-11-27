package com.withdog.account.kakao;


import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.withdog.account.bo.AccountBO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class KakaoBO {
	
	private Logger logger = LoggerFactory.getLogger(KakaoBO.class);
	private final WebClient webClient;
	private final AccountBO accountBO;
	
	private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	public static final String REDIRECT_URI = "http://localhost/account/kakao/oauth";
	public static final String REST_API_KEY = "6773c070982eedbe407df9374aeef8fd"; // client id
	private static final String CLIENT_SECRET = "rrctSuFWwhE537a2DA9c6Y6IVtJ1B9fR"; // secret client id
	private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 사용자 정보
	private static final String SERVICE_APP_ADMIN_KEY = "7901aef37758282846605303baf8758c"; // admin 키
	
	public KakaoToken getAccessTokenResponse(String code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("grant_type", "authorization_code");
		params.add("redirect_uri", REDIRECT_URI);
		params.add("client_id", REST_API_KEY);
		params.add("client_secret", CLIENT_SECRET);
		
		KakaoToken kakaoToken = webClient.post()
				.uri(TOKEN_URL)
				.body(BodyInserters.fromFormData(params))
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.retrieve()
				.bodyToMono(KakaoToken.class)
				.onErrorResume(e -> {
					logger.error("Error:###### [카카오 토큰 획득 실패] ", e);
					return null;
				})
				.block();
		
		
		
		return kakaoToken;
	}
	
	public KakaoToken getRefreshTokenResponse(String refreshToken) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("client_id", refreshToken);
		params.add("refresh_token", REDIRECT_URI);
		params.add("client_secret", CLIENT_SECRET);
		
		KakaoToken kakaoToken = webClient.post()
				.uri(TOKEN_URL)
				.body(BodyInserters.fromFormData(params))
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.retrieve()
				.bodyToMono(KakaoToken.class)
				.onErrorResume(e -> {
					logger.error("Error:###### [카카오 토큰 획득 실패] ", e);
					return null;
				})
				.block();
		
		return kakaoToken;
	}
	
	public KakaoUser getUser(String token) {
		KakaoUser kakaoUser = webClient.post()
				.uri(USER_INFO_URL)
				.header("Authorization", "KakaoAK " + SERVICE_APP_ADMIN_KEY)
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.retrieve()
				.bodyToMono(KakaoUser.class)
				.onErrorResume(e -> {
					logger.error("Error:###### [카카오 유저 정보 획득 실패] ", e);
					return null;
				})
				.block();
		
	     
		
			return kakaoUser;
	}
	

	
	@Transactional
	public Integer addAccount(KakaoUser kakaoUser) {
		String email = kakaoUser.getKakaoAccount().getEmail();
		String userId = email.split("@")[0] + System.currentTimeMillis();
		logger.info("$$$$$$$$$$$$$$ email = {}", email);
		logger.info("$$$$$$$$$$$$$$ userId = {}", userId);
		if (ObjectUtils.isEmpty(accountBO.getAccountEntityByEmail(email))) {
			return accountBO.addAccountOauth(email, userId);
		}
		
		return null;
	}
	
}
