package com.test.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.web.dao.MemberDAO;
import com.test.web.service.BoardService;
import com.test.web.service.GuestbookService;
import com.test.web.service.MemberService;
import com.test.web.service.SelectMemberService;
import com.test.web.util.PageNavigator;
import com.test.web.vo.BoardVO;
import com.test.web.vo.GuestbookVO;
import com.test.web.vo.MemberVO;
import com.test.web.vo.ReplyVO;
import com.test.web.vo.TestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	//							구분 짓는 용도
	@RequestMapping(value = "boardList", method = {RequestMethod.GET, RequestMethod.POST})	
	public String BoardList(Model model,
			@RequestParam(value="currentPage",defaultValue="1") int currentPage,
			@RequestParam(value="searchItem",defaultValue="title") String searchItem,
			@RequestParam(value="searchKeyword",defaultValue="") String searchKeyword
					)
		{
		PageNavigator navi=service.getNavi(currentPage, searchItem, searchKeyword);
		ArrayList<BoardVO> list=service.BoardList(searchItem, searchKeyword, navi);
		model.addAttribute("list", list);
		model.addAttribute("navi", navi);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchKeyword", searchKeyword);
		return "/board/boardList";		
	}
	
//	711선생님 게시판 상세보기
//	private BoardService service;
	//							구분 짓는 용도
	@RequestMapping(value = "boardRead", method = {RequestMethod.GET,RequestMethod.POST})	
	public String BoardRead(int boardnum, Model model) {
		BoardVO vo=service.boardRead(boardnum);
		ArrayList<ReplyVO> list=service.replyList(boardnum);
		model.addAttribute("vo", vo);
		model.addAttribute("replylist", list);
		return "/board/boardRead";
	}
	
	@RequestMapping(value = "boardDelete", method = RequestMethod.GET)	
	public String BoardRead(BoardVO vo, RedirectAttributes rttr,HttpSession session,Model model,int boardnum) {
		boolean result=service.boardDelete(vo,session,boardnum);
		rttr.addFlashAttribute("deleteResult", result);//Flaash는 값을 한번만 사용하고 버린다는 친구
													   //리퀘스트 역역에 저장 해서 가져감.
		//service.boardRead(boardnum); 없어도 될듯?
		return "redirect:/board/boardList";
	}
	
	@RequestMapping(value = "boardWriteForm", method = RequestMethod.GET)	
	public String boardWriteForm() {
		return "/board/boardWriteForm";
	}
	//글쓰기
	@RequestMapping(value = "boardWrite", method = RequestMethod.POST)	
	public String boardWrite(BoardVO vo,  RedirectAttributes rttr,HttpSession session, Model model,
			MultipartFile uploadFile) {
		boolean result=service.boardWrite(vo, session, uploadFile);
		rttr.addFlashAttribute("writeResult", result);
		return "redirect:/board/boardList";
	}
	
	
	//updateForm
	@RequestMapping(value = "boardUpdateForm", method = RequestMethod.GET)	
	public String boardUpdateForm(int boardnum, Model model) {
		BoardVO vo=service.boardRead(boardnum);
		model.addAttribute("vo", vo);
		return "/board/boardUpdateForm";
	}
	
	//update
	@RequestMapping(value = "boardUpdate", method = RequestMethod.POST)	
	public String boardUpdate(BoardVO vo, RedirectAttributes rttr,HttpSession session,Model model) {
		boolean result=service.boardUpdate(vo,session);
		rttr.addFlashAttribute("updateResult", result);
		return "redirect:/board/boardRead?boardnum="+vo.getBoardnum();
	}
	//파일 삭제하면서 수정해줌
	@RequestMapping(value = "boardUpdate2", method = RequestMethod.POST)	
	public String boardUpdate2(BoardVO vo, RedirectAttributes rttr,HttpSession session,Model model,int boardnum,MultipartFile uploadFile) {
		
		
		boolean result=service.boardUpdate2(vo,session,boardnum,uploadFile);
		rttr.addFlashAttribute("updateResult", result);
		return "redirect:/board/boardRead?boardnum="+vo.getBoardnum();
	}
	
	@RequestMapping(value = "boardSelect", method = RequestMethod.POST)	
	public String boardSelect(
			@RequestParam(value="searchItem",defaultValue="name") String searchItem,
			@RequestParam(value="searchKeyword",defaultValue="") String searchKeyword,
			HttpSession session,Model model) {
		ArrayList<BoardVO> list =service.boardSelect(searchItem,searchKeyword);
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		return "/board/boardList";
	}
	@RequestMapping(value = "replyWrite", method = RequestMethod.POST)	
	public String replyWrite(ReplyVO vo, HttpSession session) {
		service.replyWrite(vo,session);
		return "redirect:/board/boardRead?boardnum="+vo.getBoardnum();
	}
	
	@RequestMapping(value = "replyUpdate", method = RequestMethod.GET)	
	public String replyUpdate(ReplyVO vo, HttpSession session) {
		service.replyUpdate(vo,session);
		return "redirect:/board/boardRead?boardnum="+vo.getBoardnum();
	}
	
	@RequestMapping(value = "replyDelete", method = RequestMethod.GET)	
	public String replyDelete(ReplyVO vo, HttpSession session,RedirectAttributes rttr ) {
		boolean resuldelete=service.replyDelete(vo,session);
		rttr.addFlashAttribute("resuldelete", resuldelete);
		return "redirect:/board/boardRead?boardnum="+vo.getBoardnum();
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)	
	public void download(BoardVO vo, HttpServletResponse response, int boardnum) {
		BoardVO boardVO= service.boardRead(boardnum);
		service.download(boardVO,response);
	}
	//파일수정 버튼클릭시 수정폼으로 와짐
	@RequestMapping(value = "FileUpdate", method = RequestMethod.GET)	
	public String FileUpdate(int boardnum, Model model) {
		BoardVO vo=service.boardRead(boardnum);
		model.addAttribute("vo", vo);
		return "/board/FileUpdateForm";
	}
//	@RequestMapping(value = "download", method = RequestMethod.GET)	
//	public void download(GuestbookVO vo, HttpServletResponse response) {
//
//		GuestbookVO guestbookVO= service.read(vo);
//		service.download(guestbookVO,response);
//
//	}
/*
	710선생님
	@Controller
	@RequestMapping("/board")
	public class BoardController {

		@Autowired
		private BoardService service;
		//							구분 짓는 용도
		@RequestMapping(value = "boardList", method = RequestMethod.GET)	
		public String boardList(Model model) {
			ArrayList<BoardVO> list=service.BoardList();
			model.addAttribute("list", list);
			return "/board/boardList";		
		}
*/
	
}
