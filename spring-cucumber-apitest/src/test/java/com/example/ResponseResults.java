package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;


public class ResponseResults {
	
    private ClientHttpResponse theResponse = null;
    private String body = null;
    
    
    protected ResponseResults(final ClientHttpResponse response) throws IOException {
        this.theResponse = response;
        final InputStream bodyInputStream = response.getBody();
        if (null == bodyInputStream) {
            this.body = "{}";
        } else {
            final StringWriter stringWriter = new StringWriter();
            IOUtils.copy(bodyInputStream, stringWriter);
            this.body = stringWriter.toString();
            System.err.println(body);
        }
    }

    protected ClientHttpResponse getTheResponse() {
        return theResponse;
    }

    protected String getBody() {
        return body;
    }
}