package com.test.web.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.MemberDAO;
import com.test.web.dao.SelectMemberDAO;
import com.test.web.vo.MemberVO;

@Service
public class SelectMemberService {
	@Autowired
	private SelectMemberDAO dao;
//	public MemberVO selectMember(MemberVO vo)
//	{
//		MemberVO result=null;
//		result=dao.SelectMember(vo);
//		if(result!=null)
//		{
//			return result;
//		}
//		return result;
//	}
	
	//로그인 처리
	
	public boolean selectMembers(MemberVO vo, HttpSession session)
	{
		if(dao.SelectMember(vo)==null) return false;
		session.setAttribute("userid", vo.getUserid());
		return true;
	}
}
