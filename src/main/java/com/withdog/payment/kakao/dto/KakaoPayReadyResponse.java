package com.withdog.payment.kakao.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ToString
@Setter
@Getter
public class KakaoPayReadyResponse {

	private String tid; // 결제 고유 번호
    private String next_redirect_pc_url;
    private Date created_at;
}
