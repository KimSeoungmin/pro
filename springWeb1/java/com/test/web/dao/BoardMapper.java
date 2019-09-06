package com.test.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import com.test.web.vo.BoardVO;
import com.test.web.vo.GuestbookVO;

public interface BoardMapper {
	public ArrayList<BoardVO> BoardList(RowBounds rb, HashMap<String, String> map);

	public BoardVO boardRead(int boardnum);

	public void countHit(int boardnum);

	public int boardDelete(BoardVO vo);
	
	public int boardWrite(BoardVO vo);

	public int boardUpdate(BoardVO vo);

	public ArrayList<BoardVO> boardSelect(HashMap<String, String> map);

	public int getTotal(HashMap<String, String> map);

	public int boardUpdate2(BoardVO vo);

	public int boardDeleteFile(BoardVO vo);
	
//	0710선생님
//	public ArrayList<BoardVO> BoardList();
	
}
