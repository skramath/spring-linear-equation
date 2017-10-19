package com.example.bean;

public class CoefficentBean {
	
	public double x11;
	public double y12;
	public double x21;
	public double y22;
	public double k1;
	public double k2;
	public String message;
	
	public CoefficentBean(){
		
	}
	
	public CoefficentBean(double x11,double y12,String message) {
		this.x11 = x11;
		this.y12 = y12;
		this.message = message;
	}
	
	public double getX11() {
		return x11;
	}
	public void setX11(double x11) {
		this.x11 = x11;
	}
	public double getY12() {
		return y12;
	}
	public void setY12(double y12) {
		this.y12 = y12;
	}
	public double getX21() {
		return x21;
	}
	public void setX21(double x21) {
		this.x21 = x21;
	}
	public double getY22() {
		return y22;
	}
	public void setY22(double y22) {
		this.y22 = y22;
	}
	public double getK1() {
		return k1;
	}
	public void setK1(double k1) {
		this.k1 = k1;
	}
	public double getK2() {
		return k2;
	}
	public void setK2(double k2) {
		this.k2 = k2;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
		

}
