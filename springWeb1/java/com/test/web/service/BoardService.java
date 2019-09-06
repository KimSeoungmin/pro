package com.test.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.web.dao.BoardDAO;
import com.test.web.dao.GuestbookDAO;
import com.test.web.dao.MemberDAO;
import com.test.web.dao.SelectMemberDAO;
import com.test.web.util.PageNavigator;
import com.test.web.vo.BoardVO;
import com.test.web.vo.GuestbookVO;
import com.test.web.vo.MemberVO;
import com.test.web.vo.ReplyVO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;

	private final int countPerPage=10;
	private final int pagePerGroup=5;
	@Autowired
	private SelectMemberDAO sdao;
	//게시판출력??
	public ArrayList<BoardVO> BoardList(String searchItem, String searchKeyword, PageNavigator navi)
	{
		HashMap<String, String>map=new HashMap<>();
		map.put("searchItem",searchItem);
		map.put("searchKeyword",searchKeyword);
		return dao.BoardList(map,navi.getStartRecord(),navi.getCountPerPage());
	}
	//	711선생님
	public BoardVO boardRead(int boardnum) {
		return dao.boardRead(boardnum);
	}

	//사람선택??
	public boolean selectMembers(MemberVO vo, HttpSession session)
	{
		if(sdao.SelectMember(vo)==null) return false;
		session.setAttribute("userid", vo.getUserid());
		return true;
	}

	//게시판 파일도삭제
	public boolean boardDelete(BoardVO vo, HttpSession session,int boardnum) {
		// TODO Auto-generated method stub
		String userid=(String) session.getAttribute("userid");
		vo.setUserid(userid);
		BoardVO BoardVO = dao.boardRead(boardnum);
		String savedFilename = BoardVO.getSavedFileName();
		//	String savedFilename2
		//	= dao.boardRead(vo.getBoardnum()).getSavedFileName();

		if(dao.boardDelete(vo)!=1) return false;
		//파일 삭제 순서 위치 잘 알기
		File file =new File("C:/test/"+savedFilename);
		if(file.exists()) file.delete();

		return true;
	}

	//파일수정에 왔을때 파일 삭제
	//	public boolean boardDeleteFile(BoardVO vo, HttpSession session,int boardnum) {
	//		// TODO Auto-generated method stub
	//		String userid=(String) session.getAttribute("userid");
	//		vo.setUserid(userid);
	//		BoardVO BoardVO = dao.boardRead(boardnum);
	//		String savedFilename = BoardVO.getSavedFileName();
	//		
	//		//파일 삭제 순서 위치 잘 알기
	//		File file =new File("C:/test/"+savedFilename);
	//		if(file.exists()) file.delete();
	//		if(dao.boardDeleteFile(vo)!=1) return false;
	//		
	//		return true;
	//	}

	//	BoardVO boardVO = dao.read(vo);
	//	String savedFilename = guestbookVO.getSavedFilename();
	//	
	//	if(dao.delete(vo)!=1) return false;
	//	//파일 삭제 순서 위치 잘 알기
	//	File file =new File("C:/test/"+savedFilename);
	//	if(file.exists()) file.delete();
	//	
	//	return true;
	/*
	public boolean write(GuestbookVO vo, MultipartFile uploadFile)
	{
		if(!uploadFile.isEmpty())//파일 등록 혹은 없을시 등록시를 구분 첨부파일 있을시 false
		{
			String originalFilename = uploadFile.getOriginalFilename();	//원래 파일명
			String savedFilename= UUID.randomUUID().toString();	//UUID고유한 값을 생성할때 사용 
			 								//숫자 영어 소문자 타이푼 3가지 조합해서 36가지의 문자열 생성
			 								//고유한 문자 생성하는 것 실제에 서버에 저장되는 파일명
			vo.setOriginalFilename(originalFilename);
			vo.setSavedFilename(savedFilename);
			try {
				uploadFile.transferTo(new File("C:/test/"+savedFilename));
			} 
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //첨부파일 저장될 경로 지정
		}

		if(dao.write(vo)!=1) return false;
		return true;
	}
	 */
	//게시판등록
	public boolean boardWrite(BoardVO vo, HttpSession session, MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		String userid=(String) session.getAttribute("userid");
		vo.setUserid(userid);
		if(!uploadFile.isEmpty())
		{
			String originalFilename = uploadFile.getOriginalFilename();	//원래 파일명
			String savedFilename= UUID.randomUUID().toString();	//UUID고유한 값을 생성할때 사용
			//숫자 영어 소문자 타이푼 3가지 조합해서 36가지의 문자열 생성
			//고유한 문자 생성하는 것 실제에 서버에 저장되는 파일명
			vo.setOriginalFileName(originalFilename);
			vo.setSavedFileName(savedFilename);
			try {
				uploadFile.transferTo(new File("C:/test/"+savedFilename));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //첨부파일 저장될 경로 지정
		}

		if(dao.boardWrite(vo)!=1)
		{
			return false;
		}
		return true;
	}
	//게시판수정
	public boolean boardUpdate(BoardVO vo, HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		vo.setUserid(userid);

		if(dao.boardUpdate(vo)!=1) return false;
		return true;
	}
	//게시판검색출력
	public ArrayList<BoardVO> boardSelect(String searchItem, String searchKeyword) {
		HashMap<String, String>map=new HashMap<>();
		map.put("searchItem",searchItem);
		map.put("searchKeyword",searchKeyword);
		return dao.boardSelect(map);
	}
	//댓글쓰기
	public boolean replyWrite(ReplyVO vo, HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		vo.setUserid(userid);
		if(dao.replyWrite(vo)!=1) return false;
		return true;
	}
	//댓글출력
	public ArrayList<ReplyVO> replyList(int boardnum) {

		return dao.replyList(boardnum);
	}
	//댓글수정
	public void replyUpdate(ReplyVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		String userid=(String)session.getAttribute("userid");
		vo.setUserid(userid);
		dao.replyUpdate(vo);

	}
	//댓글삭제
	public boolean replyDelete(ReplyVO vo, HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		vo.setUserid(userid);
		return dao.replyDelete(vo);
		// TODO Auto-generated method stub


	}
	//페이징
	public PageNavigator getNavi(int currentPage, String searchItem, String searchKeyword) {
		// TODO Auto-generated method stub
		HashMap<String, String> map=new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchKeyword", searchKeyword);
		int totalRecordsCount=dao.getTotal(map);
		PageNavigator navi=new PageNavigator(countPerPage, pagePerGroup, currentPage, totalRecordsCount);
		return navi;
	}

	//파일다운로드
	public void download(BoardVO boardVO, HttpServletResponse response) {
		File file= new File("C:/test/"+boardVO.getSavedFileName());
		String originalFilename=boardVO.getOriginalFileName();

		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+URLEncoder.encode(originalFilename,"UTF-8"));
			response.setContentLength((int)file.length());
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//업데이트2
	public boolean boardUpdate2(BoardVO vo, HttpSession session, int boardnum, MultipartFile uploadFile) {
		String userid=(String)session.getAttribute("userid");
		vo.setUserid(userid);

		//파일만 삭제
		BoardVO boardVO = dao.boardRead(boardnum);
		String savedFilename1 = boardVO.getSavedFileName();
//		System.out.println("@@Num"+boardnum);
//		System.out.println("@@@"+boardVO.getSavedFileName());
		//파일 삭제 순서 위치 잘 알기
		File file =new File("C:/test/"+savedFilename1);
		if(file.exists()) file.delete();

		boolean result=false;
		if(!uploadFile.isEmpty())
		{
			String originalFilename = uploadFile.getOriginalFilename();	//원래 파일명
			String savedFilename= UUID.randomUUID().toString();	//UUID고유한 값을 생성할때 사용
			//숫자 영어 소문자 타이푼 3가지 조합해서 36가지의 문자열 생성
			//고유한 문자 생성하는 것 실제에 서버에 저장되는 파일명
			vo.setOriginalFileName(originalFilename);
			vo.setSavedFileName(savedFilename);
			try {
				uploadFile.transferTo(new File("C:/test/"+savedFilename));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		//업데이트
		if(dao.boardUpdate2(vo)>0 )
		{
			result= true;
		}
		return result;
	}
	//선생님이 해주신거는 곳
	public boolean boardUpdateT(BoardVO vo, RedirectAttributes rttr,HttpSession session,Model model,MultipartFile uploadFile) {
		//update query 가 실패했을경우
		String oldSaveFilename=vo.getSavedFileName();
		String userid = (String)session.getAttribute("userid");
		vo.setUserid(userid);

		//수정내용에 펌부파일이 등록되어 있을 경우
		if(uploadFile.isEmpty())
		{
			//vo에 객체에 새로운 첨부파일을 해줌
			vo.setOriginalFileName(uploadFile.getOriginalFilename());
			//vo객체에 새롭게 서버에 저장될 파이명을 set
			vo.setSavedFileName(UUID.randomUUID().toString());

		}
		try {
			uploadFile.transferTo(new File("C:/test/"+vo.getSavedFileName()));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file=new File("C:/test/"+oldSaveFilename);
		if(file.exists()) file.delete();

		if(dao.boardUpdate(vo)!=1)return false;
		return true;

	}

	//	파일만삭제 추가
	//	public boolean delete(BoardVO vo,int boardnum)
	//	{
	//		BoardVO boardVO = dao.boardRead(boardnum);
	//		String savedFilename = boardVO.getSavedFileName();
	//		//파일 삭제 순서 위치 잘 알기
	//		File file =new File("C:/test/"+savedFilename);
	//		if(file.exists()) file.delete();
	//		return true;
	//	}

	//	File file= new File("C:/test/"+guestbookVO.getSavedFilename());
	//	String originalFilename=guestbookVO.getOriginalFilename();
	//	
	//	try {
	//		//해더는 정보를 담고 있음
	//		response.setHeader("Content-Disposition"/*응답의 종류를 담고있음*/,
	//				/*응답의 형태*/ /*저장할 파일 이름*/
	//				"attachment;filename="
	//		+URLEncoder.encode(originalFilename,"UTF-8"));
	//		response.setContentLength((int)file.length());//원본파일과 딱맞게 하기 위해 지정
	//		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}


	//	710선생님
	//	@Service
	//	public class BoardService {
	//		@Autowired
	//		private BoardDAO dao;
	//		
	//		public ArrayList<BoardVO> BoardList(){
	//			return dao.BoardList();
	//		}
}
