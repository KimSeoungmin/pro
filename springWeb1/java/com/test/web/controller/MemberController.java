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

import com.test.web.dao.MemberDAO;
import com.test.web.service.MemberService;
import com.test.web.service.SelectMemberService;
import com.test.web.vo.MemberVO;
import com.test.web.vo.TestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	//							구분 짓는 용도
	@RequestMapping(value = "singupForm", method = {RequestMethod.GET,RequestMethod.POST})	
	public String singupForm() {
		return "/member/singupForm";		
	}

	@Autowired
	private MemberService service;

	@RequestMapping(value = "sinup", method = {RequestMethod.GET,RequestMethod.POST})	
	public String sinup(MemberVO vo, Model model) {
		//MemberVO vo=new MemberVO();

		boolean temp=false;
		System.out.println(vo);
		model.addAttribute("vo",vo);
		temp=service.memberInsert(vo);

		model.addAttribute("temp",temp);
		return "main";		
	}

	//	구분 짓는 용도
//	@RequestMapping(value = "loginForm", method = RequestMethod.GET)	
//	public String loginForm() {
//		return "/member/login";		
//	}
//
//	@Autowired
//	private SelectMemberService selectservice;
//
//	@RequestMapping(value = "login", method = RequestMethod.POST)	
//	public String login(MemberVO vo, Model model, HttpSession session) {
//
//		MemberVO bname=null;
//		bname=selectservice.selectMember(vo);
//		System.out.println(vo);
//		String fal="ID와 PW가 일치하지 않습니다.";
//		if(bname==null)
//		{
//			model.addAttribute("fal", fal);
//
//			return "/member/login";	
//		}
//		String id=vo.getUserid();
//		System.out.println("vo : "+id);
//		session.setAttribute("bname", bname);
//		model.addAttribute("name", id);
//
//		return "main";		
//	}
	@RequestMapping(value = "logout", method = RequestMethod.GET)	
	public String logout(HttpSession session) {
		//		session.setAttribute("test", "세션저장");	//세션에 값 저장
		session.removeAttribute("userid");
		return "main";		
	}
	//선생님
	@Autowired
	private SelectMemberService sservice;

	@RequestMapping(value = "login", method = RequestMethod.POST)	
	public String login(MemberVO vo, HttpSession session, Model model) {
		boolean result=sservice.selectMembers(vo, session);
		session.setAttribute("userid", vo.getUserid());
		if(result)
		{
			return "main";
		}
		model.addAttribute("result",result);
		return "/member/login";		
		
	}
	
	//로그인 화면 이동
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)	
	public String loginFORM(MemberVO vo, HttpSession session, Model model) {
		return "/member/login";		
	}
}
