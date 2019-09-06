package com.test.web.vo;

import lombok.Data;

@Data
public class BoardVO {
	
	private int boardnum;
	private String userid;
	private String title;
	private String content;
	private String inputdate;
	private String originalFileName;
	private String savedFileName;
	private int hit;
	
	
	
}
