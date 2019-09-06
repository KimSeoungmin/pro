package com.test.web.util;

import lombok.Data;

@Data
public class PageNavigator {

	private int countPerPage;		//페이지당 글목록 수
	private int pagePerGroup;		//그룹당 페이지 수 
	private int currentPage;		//현재 페이지 (최근 글이 1부터 시작)
	private int totalRecordsCount;	//전체 글 수
	private int totalPageCount;		//전체 페이지 수
	private int currentGroup;		//현재 그룹 (최근 그룹이 0부터 시작)
	private int startPageGroup;		//현재 그룹의 첫 페이지
	private int endPageGroup;		//현재 그룹의 마지막 페이지
	private int startRecord;		//전체 레코드 중 현재 페이지 첫 글의 위치 (0부터 시작)
	
	
	/*
	 * 생성자
	 */
	//인자 값을 4개 가지고 있음											현해페이지
	public PageNavigator(int countPerPage, int pagePerGroup, int currentPage, int totalRecordsCount) {
		//페이지당 글 수, 그룹당 페이지 수, 현재 페이지, 전체 글 수를 전달받음

		this.countPerPage = countPerPage;	//한 화면의 굴 수     
		this.pagePerGroup = pagePerGroup;	//1 2 3 4 5몇개의 페이지만  표시할껀가
										    //예 총페이지8개 한화면에 보여주는건 5개
		this.totalRecordsCount = totalRecordsCount;	//전체개시글 개수
		
		//전체 페이지 수(한 화면에 10개씩 하겠다 할때 77개의 데이터를 8페이지로 나누는 공식)
		totalPageCount = (totalRecordsCount + countPerPage - 1) / countPerPage;

		//전달된 현재 페이지가 1보다 작으면 현재페이지를 1페이지로 지정 (첫페이지 시 0페이지가 존재하지않아 전 버튼을 눌러도 1페이로 남음
		if (currentPage < 1)	currentPage = 1;
		//전달된 현재 페이지가 마지막 페이지보다 크면 현재페이지를 마지막 페이지로 지정(마지막페이지 시 0페이지가 존재하지않아 전 버튼을 눌러도 1페이로 남음
		if (currentPage > totalPageCount)	currentPage = totalPageCount;
		
		this.currentPage = currentPage;

		//현재 그룹
		currentGroup = (currentPage - 1) / pagePerGroup;
		
		//현재 그룹의 첫 페이지
		startPageGroup = currentGroup * pagePerGroup + 1;
		//현재 그룹의 마지막 페이지
		endPageGroup = startPageGroup + pagePerGroup - 1;
		//현재 그룹의 마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지를 전체 페이지 수로 지정
		endPageGroup = endPageGroup > totalPageCount ? totalPageCount : endPageGroup;

		//전체 레코드 중 현재 페이지 첫 글의 위치(RowBounds 전달용)
		startRecord = (currentPage - 1) * countPerPage;
	}
}
