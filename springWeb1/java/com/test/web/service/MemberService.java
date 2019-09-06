package com.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.MemberDAO;
import com.test.web.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	public boolean memberInsert(MemberVO vo)
	{
		int result=dao.memberInsert(vo);
		boolean temp=false;
		if(result>0)
		{
			temp=true;
		}
		return temp;
	}

}
