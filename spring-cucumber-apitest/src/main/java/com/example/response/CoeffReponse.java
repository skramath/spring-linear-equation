package com.example.response;

import com.example.bean.CoefficentBean;

public class CoeffReponse {	
	
	public CoeffReponse() {
		
	}
	
	private String message;
	
	private String uuid;
	
	private CoefficentBean coeffBean;
	
	public CoeffReponse(String uuid,String message,CoefficentBean coeffBean){
		this.coeffBean = coeffBean;
	}
		
	public CoeffReponse(String uuid,String message){
		this.message = message;
		this.uuid    = uuid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public CoefficentBean getCoeffBean() {
		return coeffBean;
	}

	public void setCoeffBean(CoefficentBean coeffBean) {
		this.coeffBean = coeffBean;
	}
	
}
