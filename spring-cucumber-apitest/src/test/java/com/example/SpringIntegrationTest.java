package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.example.SpringCucumberApitestApplication;
import com.example.bean.CoefficentBean;
import com.example.request.CoeffRequest;
import com.example.response.CoeffReponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringCucumberApitestApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class SpringIntegrationTest {
	
	@Value("${local.server.port}")   // 6
    int port;
	
    protected static ResponseResults latestResponse = null;

    protected TestRestTemplate restTemplate;
    
    protected CoeffRequest req;
    
    @Test
    public void testRestService(){
    	try {
			executeGet("http://localhost:8080/solve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @Bean
    public void restTemplate() {
    	restTemplate= new TestRestTemplate();
    }
    
    protected void executeGet(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        //restTemplate=new RestTemplate();
        if (restTemplate == null) {
            restTemplate = new TestRestTemplate();
        }
        latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, new ResponseExtractor<ResponseResults>() {
            @Override
        public ResponseResults extractData(ClientHttpResponse response) throws IOException {
                if (errorHandler.hadError) {
                    return (errorHandler.getResults());
                } else {
                    return (new ResponseResults(response));
                }
            }
        });
    }
    
    protected CoeffRequest createRequestObject(){
    	req = new CoeffRequest();
    	req.setExpression1("5x+y=1");
    	req.setExpression2("3x-6y=2");
    	req.setUuid("test");
    	return req;
    }

    protected void executePost(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        
        if (restTemplate == null) {
            restTemplate = new TestRestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        /*latestResponse = restTemplate.execute(url, HttpMethod.POST, requestCallback, new ResponseExtractor<ResponseResults>() {
            @Override
            public ResponseResults extractData(ClientHttpResponse response) throws IOException {
                if (errorHandler.hadError) {
                    return (errorHandler.getResults());
                } else {
                    return (new ResponseResults(response));
                }
            }
        });*/
        ResponseEntity<CoeffReponse> response = restTemplate.postForEntity(url,req,CoeffReponse.class);
        System.out.println(response.getBody().getMessage());

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

   
}