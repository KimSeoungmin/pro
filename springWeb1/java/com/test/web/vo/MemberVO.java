package com.test.web.vo;

import lombok.Data;

@Data
public class MemberVO {
	private String userid; 		//아이디
	private String userpwd;		//비밀번호
	private String username;	//이름
	private String phone;		//전화번호
	private String address;		//주소
	private String hobby;		//취미
	private String marital;		//결혼여부
	private String joinpath;	//가입경로
	
//	public MemberVO() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public MemberVO(String userid, String userpwd, String username, String phone, String address, String hobby,
//			String marital, String joinpath) {
//		super();
//		this.userid = userid;
//		this.userpwd = userpwd;
//		this.username = username;
//		this.phone = phone;
//		this.address = address;
//		this.hobby = hobby;
//		this.marital = marital;
//		this.joinpath = joinpath;
//	}
//
//	public String getUserid() {
//		return userid;
//	}
//
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
//
//	public String getUserpwd() {
//		return userpwd;
//	}
//
//	public void setUserpwd(String userpwd) {
//		this.userpwd = userpwd;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getHobby() {
//		return hobby;
//	}
//
//	public void setHobby(String hobby) {
//		this.hobby = hobby;
//	}
//
//	public String getMarital() {
//		return marital;
//	}
//
//	public void setMarital(String marital) {
//		this.marital = marital;
//	}
//
//	public String getJoinpath() {
//		return joinpath;
//	}
//
//	public void setJoinpath(String joinpath) {
//		this.joinpath = joinpath;
//	}
//
//	@Override
//	public String toString() {
//		return "MemberVO [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", phone=" + phone
//				+ ", address=" + address + ", hobby=" + hobby + ", marital=" + marital + ", joinpath=" + joinpath + "]";
//	}
	
}

