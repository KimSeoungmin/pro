package com.test.web.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int replynum;
	private int boardnum;
	private String userid;
	private String replytext;
	private String inputdate;
	
}
