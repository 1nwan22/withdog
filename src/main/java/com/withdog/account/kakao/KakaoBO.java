package com.withdog.account.kakao;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.withdog.account.bo.AccountBO;
import com.withdog.account.entity.AccountEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class KakaoBO {
	
	private final WebClient webClient;
	private final AccountBO accountBO;
	private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 사용자 정보
//	public static final String REDIRECT_URI = "http://localhost/account/kakao-oauth";
	public static final String REDIRECT_URI = "http://3.39.6.159:8080/account/kakao-oauth";
	public static final String REST_API_KEY = "6773c070982eedbe407df9374aeef8fd"; // client id
	private static final String CLIENT_SECRET = "rrctSuFWwhE537a2DA9c6Y6IVtJ1B9fR"; // secret client id
	
	public KakaoToken getAccessToken(String code) {
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
				.block();
		log.warn("$$$$$$$$$$$$$$$ kakaoToken = {}", kakaoToken);
		
		return kakaoToken;
	}
	
	public KakaoToken getRefreshToken(String refreshToken) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("client_id", REDIRECT_URI);
		params.add("refresh_token", refreshToken);
		params.add("client_secret", CLIENT_SECRET);
		
		KakaoToken kakaoToken = webClient.post()
				.uri(TOKEN_URL)
				.body(BodyInserters.fromFormData(params))
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.retrieve()
				.bodyToMono(KakaoToken.class)
				.block();
		log.warn("$$$$$$$$$$$$$$$ kakaoToken = {}", kakaoToken);
		
		return kakaoToken;
	}
	
	 public KakaoUser getUserByToken(String accessToken) {
			KakaoUser kakaoUser = webClient.post()
					.uri(USER_INFO_URL)
					.header("Authorization", "Bearer " + accessToken)
					.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
					.retrieve()
					.bodyToMono(KakaoUser.class)
					.block();
			
			log.warn("$$$$$$$$$$$$$$$ kakaoUser = {}", kakaoUser);
			
			return kakaoUser;
	 }
	 
//	 public KakaoUser getUserByAdmin(Long id) {
//			KakaoUser kakaoUser = webClient.post()
//					.uri(USER_INFO_URL)
//					.header("Authorization", "KakaoAK " + SERVICE_APP_ADMIN_KEY)
//					.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
//					.retrieve()
//					.bodyToMono(KakaoUser.class)
//					.block();
//			
//			log.warn("$$$$$$$$$$$$$$$ kakaoUser = {}", kakaoUser);
//			
//			return kakaoUser;
//	 }
	 
	 
	 public AccountEntity getUserByEmail(String email) {
		 return accountBO.getAccountEntityByEmail(email);
	 }
	
	@Transactional
	public AccountEntity addAccount(KakaoUser kakaoUser) {
		String email = kakaoUser.getKakao_account().getEmail();
		String userId = email.split("@")[0] + System.currentTimeMillis();
		log.info("$$$$$$$$$$$$$$ email = {}", email);
		log.info("$$$$$$$$$$$$$$ userId = {}", userId);
		
		return accountBO.getAccountEntityByEmail(email) != null ? accountBO.getAccountEntityByEmail(email) : accountBO.addAccountKakaoOauth(email, userId);
	}
	
}
