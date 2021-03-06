package com.test.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.web.vo.TestVO;

@Repository//스프링에 다오 역할 하는거라고 알려줌
public class TestDAO {
	@Autowired	//관리는 하지만 객체생성을 안하는 친구
	private SqlSession sqlSession;//마이바티스에서 sql에 던져줄때 SqlSession 사용 함
	
	public int testInsert(TestVO vo)
	{
		TestMapper mapper= sqlSession.getMapper(TestMapper.class);
		return mapper.testInsert(vo);
	}
}
