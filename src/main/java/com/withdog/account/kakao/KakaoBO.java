package com.withdog.account.kakao;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.withdog.account.bo.AccountBO;
import com.withdog.account.entity.AccountEntity;

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
	public static final String REST_API_KEY = "1bae0f0383b840c78944ff92c9750271"; // client id
	private static final String GRANT_TYPE = "authorization_code";
	private static final String CLIENT_SECRET = "FrRaoXpOOa668egtAvKnFxVq9enfnnW0"; // secret client id
	private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 사용자 정보
	
	public KakaoToken getAccessToken(String code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("grant_type", GRANT_TYPE);
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
	
	public KakaoUser getUser(String token) {
		KakaoUser kakaoUser = webClient.post()
				.uri(USER_INFO_URL)
				.header("Authorization", "Bearer " + token)
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.retrieve()
				.bodyToMono(KakaoUser.class)
				.onErrorResume(e -> {
			        logger.error("Error:###### [카카오 유저 정보 불러오기 실패] ", e);
			        return null;
			    })
				.block();
		
		return kakaoUser;
				
	}
	
	@Transactional
	public void addAccount(String token) {
		String email = getUser(token).getEmail();
		String userName = getUser(token).getName();
		accountBO.getAccountEntityByEmail(email);
		
	}
	
}
