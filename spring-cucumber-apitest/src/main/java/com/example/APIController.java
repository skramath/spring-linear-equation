package com.example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.CoefficentBean;
import com.example.service.EquationService;
import com.example.service.ValidatorService;

@RestController
public class APIController {   
    
	@Autowired
	@Resource(name = "eqnService")
	EquationService equationService;
	
	@Autowired
	@Resource(name = "eqnValidator")
	ValidatorService validateService;
    
    @RequestMapping(method = { RequestMethod.GET }, value = { "/getUUID" })
    public CoefficentBean getUUID(HttpServletResponse response) {
    	return null;
    }
    
    @RequestMapping(method = {  RequestMethod.GET }, value = { "/solve" })
    public String solve(HttpServletResponse response) {
    	String exp1 = "5x+4y=1";//-4x+3y=11";
		String exp2 = "3x-6y=2";//"6x-5y=7";
		//double[] answer = LinearEquation.solve2x2LinearEquation( 4,-3,6,5,11,7 );
		CoefficentBean coeeficentBean = equationService.solve2x2LinearEquation(validateService.getCoefficent(exp1, exp2));
		return coeeficentBean.getMessage();
    }
}
