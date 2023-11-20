package com.withdog.account.kakao;

import lombok.Data;

@Data
public class KakaoUser {

	private Long id;
	private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;
    private Boolean name_needs_agreement;
    private String name;
    

}
