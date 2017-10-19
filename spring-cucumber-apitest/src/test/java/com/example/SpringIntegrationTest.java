package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.example.SpringCucumberApitestApplication;
import com.example.bean.CoeffReponse;
import com.example.bean.CoeffRequest;
import com.example.constants.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringCucumberApitestApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class SpringIntegrationTest {
	
	@Value("9090")   // 6
    int port;
	
	String valid_uid    = "test";
	String invalid_uid  = "invalid_UUID";
	
	protected static ResponseResults latestResponse = null;
    protected RestTemplate restTemplate;    
    protected CoeffRequest req;
    
    @Bean
    public void restTemplate() {
    	restTemplate= new TestRestTemplate();
    }
    
   
    // 1. Make a sample rest service call
    @Test
    public void testRestService(){
    	try {
			executeGet("http://localhost:8080/api/restcheck");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // 2. Make a restful service to request for an unique equation identifier [UUID]
 	@Test
 	public void getUUIDTest(){
 		System.out.println("Get UUID Test Running");
 		try {
 			executeGet("http://localhost:8080/api/invalidateUID");
 			CoeffReponse response = executeGet("http://localhost:8080/api/getUID");
 			if(response.getUuid()!=null){
 				 valid_uid = response.getUuid();
 			}
 			System.out.println();
 			Assert.assertTrue(response.getMessage(),Constants.SUCCESS_CODE.equals(response.getStatusCode()));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	}
 	
 	//3. Make a restful service to request for an unique equation identifier [UUID], slots for the identifier is unavailable
 	@Test
 	public void getMultipleUUIDTest(){
 		System.out.println("Multiple User Test Running");
 		try {
 			executeGet("http://localhost:8080/api/invalidateUID");
 			CoeffReponse response=null;
 			for(int i=0;i<5;i++){
 				executeGet("http://localhost:8080/api/getUID");
 			}		
 			response  = executeGet("http://localhost:8080/api/getUID");
 			Assert.assertTrue(response.getMessage(),Constants.FAILED_CODE.equals(response.getStatusCode()));
 		}catch (IOException e) {// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	}
 	
 	//4. Upload a linear equation of 2 variables of type for eg: 2x + y = 1 with valid UUID
 	@Test
 	public void uploadValidLinearEqnwithValidUUIDTest(){
 		try {
 		    this.createRequestObject("2x + y = 1",null,valid_uid);
 			ResponseEntity<CoeffReponse> response = executePost("http://localhost:8080/api/validateexp");
 			Assert.assertEquals(Constants.SUCCESS_CODE, response.getBody().getStatusCode());
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}
 	
 	//5. upload valid linear equation for eg: 2x + y = 1  Invalid UUID
 	@Test
 	public void uploadLinearEqnWithInvalidUUIDTest(){
 		try {
 		   this.createRequestObject("2x + y = 1",null,invalid_uid);
 		   ResponseEntity<CoeffReponse> response = executePost("http://localhost:8080/api/validateexp");
 		   Assert.assertEquals(Constants.FAILED_CODE, response.getBody().getStatusCode());
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	}
 	
 	//6. validate/upload invalid linear equation with Valid UUID [for eg : if more than 2 variables, invalid operators]
 	@Test
 	public void uploadInValidLinearEqnwithValidUUIDTest(){
 		try {
 			   this.createRequestObject("5x+y+3z=1",null,valid_uid);
 			   ResponseEntity<CoeffReponse> response = executePost("http://localhost:8080/api/validateexp");
 			   Assert.assertEquals(Constants.FAILED_CODE, response.getBody().getStatusCode());
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	}
 	
 	//7. validate/upload invalid linear equation with Invalid UUID [for eg : if more than 2 variables, invalid operators]
 		@Test
 		public void uploadInvalidLinearEqnWithInvalidUUIDTest(){
 			try {
 				   this.createRequestObject("5x+y+3z=1",null,invalid_uid);
 				   ResponseEntity<CoeffReponse> response = executePost("http://localhost:8080/api/validateexp");
 				   Assert.assertEquals(Constants.FAILED_CODE, response.getBody().getStatusCode());
 				} catch (IOException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 		}
 	
 	//8. Scenario: Solve linear equation with Valid UUID
 	@Test
 	public void solveLinearEqnWithValidUUIDTest(){
 		try {
 			   this.createRequestObject("5x+3y=1","3x-6y=2",valid_uid);
 			   ResponseEntity<CoeffReponse> response = executePost("http://localhost:8080/api/solveexp");
 			   Assert.assertEquals(Constants.SUCCESS_CODE, response.getBody().getStatusCode().toString());
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	}
 	
 	//9. Scenario: Solve linear equation with inValid UUID
 	@Test
 	public void solveLinearEqnWithInvalidUUIDTest(){
 		try {
 			   this.createRequestObject("5x+3y=1","3x-6y=2",invalid_uid);
 			   ResponseEntity<CoeffReponse> response = executePost("http://localhost:8080/api/solveexp");
 			   Assert.assertEquals(Constants.FAILED_CODE, response.getBody().getStatusCode());
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	}
    
    
    protected CoeffReponse executeGet(String url) throws IOException {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        CoeffReponse response = restTemplate.getForObject(url,CoeffReponse.class);
        return response;
    }
    
    protected CoeffRequest createRequestObject(String exp1,String exp2,String uuid){
    	req = new CoeffRequest(exp1,exp2,uuid);
    	if(req.getUuid() == null){
    		req.setUuid("test");
    	}
    	return req;
    }

    protected ResponseEntity<CoeffReponse> executePost(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        restTemplate.setErrorHandler(errorHandler);
        ResponseEntity<CoeffReponse> response = restTemplate.postForEntity(url,req,CoeffReponse.class);
        return response;
    }

    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;
        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
    
    void validateResponse(CoeffReponse response,boolean status){    	
    	if(status){
    		Assert.assertEquals(Constants.SUCCESS_CODE, response.getStatusCode(),response.getMessage());
    	}else{
    		Assert.assertEquals(Constants.FAILED_CODE, response.getStatusCode(),response.getMessage());
    	}
    }

   
}