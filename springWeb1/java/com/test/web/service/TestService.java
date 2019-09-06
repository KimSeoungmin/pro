package com.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.TestDAO;
import com.test.web.vo.TestVO;

@Service	//반드시 해야 함
public class TestService {
	
	@Autowired
	private TestDAO dao;
	public void TestInsert(TestVO vo)
	{
		int result=dao.testInsert(vo);
		System.out.println(result);
		
	}
}
	