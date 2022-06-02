package JobsApi;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_post_jobs extends Jobsbase {
	@Test
	void postjob() throws InterruptedException {
		// Specify base URI
		// RestAssured.baseURI="https://jobs123.herokuapp.com/Jobs";
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-type", "application/json");
		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Id", "1338");
		requestParams.put("Job Title", "SDET63");
		requestParams.put("Job Location", "PA");
		requestParams.put("Job Company Name", "Shakthji7");
		requestParams.put("Job Type", "Fulltime");
		requestParams.put("Job Posted time", "10minutes");
		requestParams.put("Job Description", "SeleniumEngineer");
		httpRequest.body(requestParams.toJSONString()); // attach above data to the request

		// Response object
		Response response = httpRequest.post("https://jobs123.herokuapp.com/Jobs");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		// status code validation
		logger.info("Status Code");
		int statusCode = response.getStatusCode();
		logger.info("Status code is: " + statusCode);
		logger.info("Response body");

	}

	 
	@Test
	void jasonschemausers() {
		logger.info("checking response body");
		String responseBody = response.getBody().asString();
		MatcherAssert.assertThat(responseBody,
				JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/post.json"));
		logger.info("Json schema validated");
	}
}
