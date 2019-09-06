package com.test.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.web.vo.BoardVO;
import com.test.web.vo.GuestbookVO;
import com.test.web.vo.MemberVO;
import com.test.web.vo.ReplyVO;
import com.test.web.vo.TestVO;

@Repository//스프링에 다오 역할 하는거라고 알려줌
public class BoardDAO {
	
	@Autowired	//관리는 하지만 객체생성을 안하는 친구
	private SqlSession sqlSession;//마이바티스에서 sql에 던져줄때 SqlSession 사용 함
	
	public ArrayList<BoardVO> BoardList(HashMap<String, String> map, int startRecord, int countPerPage)
	{
		
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		RowBounds rb=new RowBounds(startRecord,countPerPage);
		return mapper.BoardList(rb,map);
	}
//711선생님
	public BoardVO boardRead(int boardnum) {
		// TODO Auto-generated method stub
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		mapper.countHit(boardnum);
		return mapper.boardRead(boardnum);
	}
	public int boardDelete(BoardVO vo) {
		// TODO Auto-generated method stub
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.boardDelete(vo);
		
	}
	public int boardWrite(BoardVO vo) {
		// TODO Auto-generated method stub
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.boardWrite(vo);
		
	}
	public int boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.boardUpdate(vo);
	}
	public ArrayList<BoardVO> boardSelect(HashMap<String, String> map) {
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.boardSelect(map);
	}
	public int replyWrite(ReplyVO vo) {
		ReplyMapper mapper= sqlSession.getMapper(ReplyMapper.class);
		return mapper.replyWrite(vo);
	}
	public ArrayList<ReplyVO> replyList(int boardnum) {
		// TODO Auto-generated method stub
		ReplyMapper mapper= sqlSession.getMapper(ReplyMapper.class);
		return mapper.replyList(boardnum);
	}
	public void replyUpdate(ReplyVO vo) {
		// TODO Auto-generated method stub
		ReplyMapper mapper= sqlSession.getMapper(ReplyMapper.class);
		mapper.replyUpdate(vo);
	}
	public boolean replyDelete(ReplyVO vo) {
		ReplyMapper mapper= sqlSession.getMapper(ReplyMapper.class);
		return mapper.replyDelete(vo);
		// TODO Auto-generated method stub
		
	}
	public int getTotal(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.getTotal(map);
	}
	public int boardUpdate2(BoardVO vo) {
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.boardUpdate2(vo);
	}
//	public int boardUpdate(BoardVO vo) {
//		// TODO Auto-generated method stub
//		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
//		return mapper.boardUpdate(vo);
//	}
	public int boardDeleteFile(BoardVO vo) {
		// TODO Auto-generated method stub
		BoardMapper mapper= sqlSession.getMapper(BoardMapper.class);
		return mapper.boardDeleteFile(vo);
	}
	
	
	//710선생님
//	public ArratList<BoardVO> BoardList()
//	{
//		BoardMapper mapper=sqlSession.getMapper(BoardMapper.class);
//		return mapper.BoardList();
//	}
}
