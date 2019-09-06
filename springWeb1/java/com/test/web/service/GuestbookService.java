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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.test.web.dao.GuestbookDAO;
import com.test.web.vo.GuestbookVO;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDAO dao;
	public ArrayList<GuestbookVO> guestbookList(String searchItem, String searchKeyword)
	{
		System.out.println("서비스");
		HashMap<String, String>map=new HashMap<>();
		map.put("searchItem",searchItem);
		map.put("searchKeyword",searchKeyword);
		return dao.guestbookList(map);
	}
	
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
	
	//파일삭제도 추가
	public boolean delete(GuestbookVO vo)
	{
		GuestbookVO guestbookVO = dao.read(vo);
		String savedFilename = guestbookVO.getSavedFilename();
		
		if(dao.delete(vo)!=1) return false;
		//파일 삭제 순서 위치 잘 알기
		File file =new File("C:/test/"+savedFilename);
		if(file.exists()) file.delete();
		
		return true;
	}

	public GuestbookVO read(GuestbookVO vo) {
		
		return dao.read(vo);
	}

	public void download(GuestbookVO guestbookVO, HttpServletResponse response) {
		
		File file= new File("C:/test/"+guestbookVO.getSavedFilename());
		String originalFilename=guestbookVO.getOriginalFilename();
		
		try {
			//해더는 정보를 담고 있음
			response.setHeader("Content-Disposition"/*응답의 종류를 담고있음*/,
					/*응답의 형태*/ /*저장할 파일 이름*/
					"attachment;filename="
			+URLEncoder.encode(originalFilename,"UTF-8"));
			response.setContentLength((int)file.length());//원본파일과 딱맞게 하기 위해 지정
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//로그인 처리
	
//	public boolean selectMembers(MemberVO vo, HttpSession session)
//	{
//		if(dao.SelectMember(vo)==null) return false;
//		session.setAttribute("userid", vo.getUserid());
//		return true;
//	}
}

