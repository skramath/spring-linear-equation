package com.examples;

import java.io.IOException;

import org.junit.Test;

import com.example.bean.CreateBean;

public class LinearEquationTest extends SpringIntegrationTest{
	
	
	/*@Test
	public void getUUIDTest(){
		try {
				executeGet("http://localhost:8080/api/getUID");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getMultipleUUIDTest(){
		try {
				executeGet("http://localhost:8080/api/getUID");
				executeGet("http://localhost:8080/api/getUID");
				executeGet("http://localhost:8080/api/getUID");
				executeGet("http://localhost:8080/api/getUID");
				executeGet("http://localhost:8080/api/getUID");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void solveLinearEquationTest(){
		try {
			   this.createRequestObject();
			   executePost("http://localhost:8080/api/solve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	@Test
	public void invalidLinearEquationTest(){
		try {
			   executeGet("http://localhost:8080/solve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void invalidUUIDTest(){
		try {
			   executeGet("http://localhost:8080/solve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void validUidLinearCoefficentTest(){
		try {
			   executeGet("http://localhost:8080/solve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void invalidUidLinearCoefficentTest(){
		try {
			   executeGet("http://localhost:8080/solve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
