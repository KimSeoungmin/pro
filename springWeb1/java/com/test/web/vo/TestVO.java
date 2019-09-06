package com.test.web.vo;

public class TestVO {
	private String a;	//변수명 웹의 파라미터 이름과 맞춰 줘야함
	private int b;
	
	public TestVO() {}
	
	public TestVO(String a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	
	@Override
	public String toString() {
		return "TestVO [a=" + a + ", b=" + b + "]";
	}
	
}
