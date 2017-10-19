package com.example;

import java.util.UUID;

import javax.annotation.Resource;
import com.example.bean.CoeffReponse;
import com.example.bean.CoeffRequest;
import com.example.bean.CoefficentBean;
import com.example.constants.Constants;
import com.example.service.EquationService;
import com.example.service.UIDService;
import com.example.service.ValidatorService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    
	
	@RequestMapping(method = { RequestMethod.GET }, value = {"/restcheck" })
    public CoeffReponse checkRestService(HttpServletResponse response) {
    	return new CoeffReponse(null, Constants.SERVER_UP,Constants.SUCCESS_CODE); 
    }
	
	@RequestMapping(method = { RequestMethod.GET }, value = {"/getUID" })
    public CoeffReponse getUUID(HttpServletResponse response) {
		UUID uid = uidService.retrieveUID();
		return (uid !=null) ? new CoeffReponse(uid.toString(),Constants.UUID_AVAILABLE,Constants.SUCCESS_CODE) : 
				new CoeffReponse(null,Constants.UUID_UNAVAILABLE,Constants.FAILED_CODE);
    }
	
	@RequestMapping(method = { RequestMethod.GET }, value = {"/invalidateUID" })
    public CoeffReponse invalidateUUID(HttpServletResponse response) {
		return (uidService.clearUID()) ? new CoeffReponse(null,Constants.UUID_CLEARED,Constants.SUCCESS_CODE) : 
				new CoeffReponse(null,Constants.UUID_NOT_CLEARED,Constants.FAILED_CODE);
    }
	
	@RequestMapping(method = {RequestMethod.POST}, value = { "/validateexp" })
    public @ResponseBody CoeffReponse validate(@RequestBody CoeffRequest req) {
    	CoefficentBean coeeficentBean = null;
    	String exp1 = req.getExpression1();
		if(uidService.validateUID(req.getUuid())){
			coeeficentBean = validateService.getCoefficent(exp1);
			return (coeeficentBean.getX11() != 0.0 && coeeficentBean.getY12() != 0.0) ? 
					new CoeffReponse(req.getUuid(),Constants.VALID_EXPRESSION,Constants.SUCCESS_CODE):
						new CoeffReponse(req.getUuid(),Constants.INVALID_EXPRESSION,Constants.FAILED_CODE);	
		}
		return new CoeffReponse(null,Constants.UUID_INAVLID,Constants.FAILED_CODE);
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = { "/solveexp" })
    public @ResponseBody CoeffReponse solve(@RequestBody CoeffRequest req) {
    	CoefficentBean coeeficentBean = null;
    	String exp1 = req.getExpression1();
		String exp2 = req.getExpression2();
		if(uidService.validateUID(req.getUuid())){
			coeeficentBean = equationService.solve2x2LinearEquation(validateService.getCoefficent(exp1, exp2));
			return new CoeffReponse(req.getUuid(),coeeficentBean.getMessage(),coeeficentBean,Constants.SUCCESS_CODE);
		}
		return new CoeffReponse(null,Constants.UUID_INAVLID,Constants.FAILED_CODE);
    }
    
}
