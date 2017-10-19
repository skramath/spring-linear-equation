package com.example.bean;

public class CoeffRequest {
	
	private String expression1;
	private String expression2;
	private String uuid;
	
	public CoeffRequest(){
		
	}	
	public CoeffRequest(String expression1, String expression2, String uuid) {
		
		this.expression1 = expression1;
		this.expression2 = expression2;
		this.uuid = uuid;
	}
	
	public String getExpression1() {
		return expression1;
	}
	public void setExpression1(String expression1) {
		this.expression1 = expression1;
	}
	public String getExpression2() {
		return expression2;
	}
	public void setExpression2(String expression2) {
		this.expression2 = expression2;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	
	

}
