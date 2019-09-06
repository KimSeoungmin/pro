package com.test.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.web.service.TestService;
import com.test.web.vo.MemberVO;
import com.test.web.vo.TestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService service;
	
	//							구분 짓는 용도
	@RequestMapping(value = "testInsert", method = RequestMethod.GET)	
	public String testInsert(TestVO vo) {
		service.TestInsert(vo);
	
		return "main";		
	}
	
	@RequestMapping(value = "testSession1", method = RequestMethod.GET)	
	public String testSession1(HttpSession session) {
		session.setAttribute("test", "세션저장");	//세션에 값 저장
	
		return "main";		
	}
	
	@RequestMapping(value = "testSession2", method = RequestMethod.GET)	
	public String testSession2(HttpSession session) {
		session.removeAttribute("test");	//세션값 삭제
		//session.invalidate();
	
		return "main";		
	}
	
	@RequestMapping(value = "testMap", method = RequestMethod.GET)	
	public String testMap(HttpSession session) {
		//session.invalidate();
	
		return "testMap";		
	}
	
//	@RequestMapping(value = "testSession1", method = RequestMethod.GET)	
//	public String testSession3(HttpSession session) {
//		session.setAttribute("test", "세션저장");	//세션에 값 저장
//	
//		return "main";		
//	}
//	
//	@RequestMapping(value = "testSession2", method = RequestMethod.GET)	
//	public String testSession4(HttpSession session) {
//		session.removeAttribute("test");	//세션값 삭제
//		//session.invalidate();
//	
//		return "main";		
//	}
	
}
