package com.test.web.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

import com.test.web.dao.MemberDAO;
import com.test.web.service.GuestbookService;
import com.test.web.service.MemberService;
import com.test.web.service.SelectMemberService;
import com.test.web.vo.GuestbookVO;
import com.test.web.vo.MemberVO;
import com.test.web.vo.TestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService service;
	//							구분 짓는 용도
	@RequestMapping(value = "guestbookList", method = {RequestMethod.GET, RequestMethod.POST})
	//vlaue가 존재하지 않으면 name으로 가져서 name의 값을 서치 아이템으로 가짐
	public String guestbookList(
			@RequestParam(value="searchItem",defaultValue="name") String searchItem,
			@RequestParam(value="searchKeyword",defaultValue="") String searchKeyword,
			Model model,HttpSession setion) {
		System.out.println("컨트롤러");
		ArrayList<GuestbookVO> list =service.guestbookList(searchItem,searchKeyword);
		model.addAttribute("list", list);
		return "/guestbook/guestbookList";		
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)	
	public String write(GuestbookVO vo, Model model, MultipartFile uploadFile) {
		boolean result=service.write(vo,uploadFile);
		model.addAttribute("writeResult", result);
		//return "redirect:/guestbook/guestbookList";	//리다이랙트는 모델에 담은객체를 전달 못할 수도 있음
		return "forward:/guestbook/guestbookList";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)	
	public String delete(GuestbookVO vo, Model model) {
		boolean result=service.delete(vo);
		model.addAttribute("deleteResult", result);
		//return "redirect:/guestbook/guestbookList";	//리다이랙트는 모델에 담은객체를 전달 못할 수도 있음
		return "forward:/guestbook/guestbookList";
	}
	@RequestMapping(value = "download", method = RequestMethod.GET)	
	public void download(GuestbookVO vo, HttpServletResponse response) {

		GuestbookVO guestbookVO= service.read(vo);
		service.download(guestbookVO,response);

	}
	
}
