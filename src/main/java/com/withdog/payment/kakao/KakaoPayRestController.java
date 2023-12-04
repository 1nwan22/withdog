package com.withdog.payment.kakao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.payment.kakao.bo.KakaoPayBO;
import com.withdog.payment.kakao.dto.KakaoPayApproveResponse;
import com.withdog.payment.kakao.dto.KakaoPayReadyResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/kakao")
@RestController
public class KakaoPayRestController {

	private final KakaoPayBO kakaoPayBO;
	
    @PostMapping("/ready")
    public KakaoPayReadyResponse kakaoPayReady() {

        return kakaoPayBO.kakaoPayReady();
    }
    
    @GetMapping("/success")
    public Map<String, Object> kakaoPaySuccess(@RequestParam("pg_token") String pgToken) {

    	log.error("@@@@@@@@@@@@@@ pgToken = {}", pgToken);
        KakaoPayApproveResponse kakaoPayApproveResponse = kakaoPayBO.kakaoPayApprove(pgToken);
        log.info("$$$$$$$$$$$$$$$ kakaoPayApproveResponse = {}", kakaoPayApproveResponse);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("result", "success");
        
        return result;
    }
    
    @GetMapping("/cancel")
    public void kakaoPayCancel(HttpServletResponse response) {

    	try {
			response.sendRedirect("http://localhost/checkout-view");
		} catch (IOException e) {
			log.error("@@@@@@@@@@@@@@ 카카오 페이 결제 취소 @@@@@@@@@@@@@@");
		}


    }
    
    @GetMapping("/fail")
    public void kakaoPayFail(HttpServletResponse response) {

    	try {
			response.sendRedirect("http://localhost/checkout-view");
		} catch (IOException e) {
			log.error("@@@@@@@@@@@@@@ 카카오 페이 결제 실패 @@@@@@@@@@@@@@");
		}

    }



}
