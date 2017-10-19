package com.example.bean;


/*
 * Response Object. Entire response data will be available in this object
 */
public class CoeffReponse {	
	
	private String message;	
	private String uuid;	
	private String statusCode;	
	private CoefficentBean coeffBean;
	
	public CoeffReponse() {
		
	}
	
	public CoeffReponse(String uuid,String message,CoefficentBean coeffBean,String statusCode){
		this.coeffBean  = coeffBean;
		this.message    = message;
		this.uuid       = uuid;
		this.statusCode = statusCode;
	}
		
	public CoeffReponse(String uuid,String message,String statusCode){
		this.message = message;
		this.uuid    = uuid;
		this.statusCode = statusCode;
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
}
