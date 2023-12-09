package com.withdog.payment.kakao;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.payment.kakao.bo.KakaoPayBO;
import com.withdog.payment.kakao.dto.KakaoPayApproveResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/kakao")
@Controller
public class KakaoController {

	private final KakaoPayBO kakaoPayBO;

	@RequestMapping("/success")
	public String kakaoPaySuccess(@RequestParam("pg_token") String pgToken, HttpSession session, Model model) {

		int orderId = (int) session.getAttribute("orderId");
		int accountId = (int) session.getAttribute("accountId");
		String tid = (String) session.getAttribute("tid");
		log.info("@@@@@@@@@@@@@@ pgToken = {}", pgToken);
		log.info("@@@@@@@@@@@@@@ tid = {}", tid);
		KakaoPayApproveResponse kakaoPayApproveResponse = kakaoPayBO.kakaoPayApprove(pgToken, tid, orderId, accountId);
		log.info("$$$$$$$$$$$$$$$ kakaoPayApproveResponse = {}", kakaoPayApproveResponse);
		session.removeAttribute("orderId");
		session.removeAttribute("tid");
		model.addAttribute("viewName", "pay/paymentSuccess");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}

	@RequestMapping("/cancel")
	public void kakaoPayCancel(HttpServletResponse response) {

		try {
			response.sendRedirect("http://localhost/checkout-view");
		} catch (IOException e) {
			log.error("@@@@@@@@@@@@@@ 카카오 페이 결제 취소 @@@@@@@@@@@@@@");
		}

	}

	@RequestMapping("/fail")
	public void kakaoPayFail(HttpServletResponse response) {

		try {
			response.sendRedirect("http://localhost/checkout-view");
		} catch (IOException e) {
			log.error("@@@@@@@@@@@@@@ 카카오 페이 결제 실패 @@@@@@@@@@@@@@");
		}

	}
}
