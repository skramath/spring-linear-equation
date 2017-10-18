package com.example;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.CoefficentBean;
import com.example.request.CoeffRequest;
import com.example.response.CoeffReponse;
import com.example.service.EquationService;
import com.example.service.UIDService;
import com.example.service.ValidatorService;


@RequestMapping(value = "/api")
@RestController
public class APIController { 
	
	
    
	@Autowired
	@Resource(name = "eqnService")
	EquationService equationService;
	
	@Autowired
	@Resource(name = "eqnValidator")
	ValidatorService validateService;
	
	@Autowired
	@Resource(name="uidService")
	UIDService uidService;
    
	@RequestMapping(method = { RequestMethod.GET }, value = {"/getUID" })
    public String getUUID(HttpServletResponse response) {
    	UUID uid = uidService.retrieveUID();
    	return (uid !=null) ? uid.toString(): "Slot not avaiable, please wait...";
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = { "/solve" })
    public @ResponseBody CoeffReponse solve(@RequestBody CoeffRequest req) {
    	String exp1 = req.getExpression1();//"5x+y=1";//-4x+3y=11";
		String exp2 = req.getExpression2();//"3x-6y=2";//"6x-5y=7";
		CoefficentBean coeeficentBean = null;
		//double[] answer = LinearEquation.solve2x2LinearEquation( 4,-3,6,5,11,7 );
		if(uidService.validateUID(req.getUuid())){
			coeeficentBean = equationService.solve2x2LinearEquation(validateService.getCoefficent(exp1, exp2));
			return new CoeffReponse(req.getUuid(),coeeficentBean.getMessage(),coeeficentBean);
		}
		return new CoeffReponse(null,"Invalid UUID",null);
    }
    
    /*public static void main(String[] args) {
    	
    	String exp1 = "5x+y+2z=1";//-4x+3y=11";
		String exp2 = "3x-6y=2";//"6x-5y=7";
		//double[] answer = LinearEquation.solve2x2LinearEquation( 4,-3,6,5,11,7 );
		CoefficentBean coeeficentBean = new EquationServiceImp().solve2x2LinearEquation(new EquationValidatior().getCoefficent(exp1, exp2));
		System.out.println(coeeficentBean.getMessage());
		
	}*/
    
    
    
    
    
    
}
