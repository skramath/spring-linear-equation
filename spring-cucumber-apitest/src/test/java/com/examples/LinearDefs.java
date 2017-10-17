package com.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.http.HttpStatus;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LinearDefs extends SpringIntegrationTest  {
	
	  @When("^the client calls /solve$")
	    public void the_employee_issues_GET_version() throws Throwable {
	        executeGet("http://localhost:8080/solve");
	    }

	    @Then("^the employee receives status code of (\\d+)$")
	    public void thestatus_code_of_employee_code(int statusCode) throws Throwable {
	        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
	        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	    }

	    @And("^the response should contain:$")
	    public void the_employee_receives_server_version_body(String version) throws Throwable {
	    	System.out.println(version);
	    	System.out.println(latestResponse.getBody());
	        assertThat(latestResponse.getBody(), is(version));
	    }

}
