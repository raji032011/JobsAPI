package JobsApi;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_delete extends Jobsbase {

	@Test
	public void deletejob() {
		RestAssured.baseURI = "https://jobs123.herokuapp.com/";
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestparams = new JSONObject();
		requestparams.put("Job Id", "1001");
		// requestparams.put("Job Title", "SDET31");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestparams.toJSONString());
		// response object
		Response response = httpRequest.request(Method.DELETE, "/Jobs");
		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("response Body is" + responseBody);
		// status code verification
		int statusCode = response.getStatusCode();
		System.out.println("status code is:" + statusCode);
		// Assert.assertEquals(statusCode, 200);
		if (statusCode == 200) {

			// Jason schema validation
			MatcherAssert.assertThat(responseBody,
					JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/deletevalid.json"));

		}
		
		else if(statusCode==404)
		{
			// Jason schema validation
			MatcherAssert.assertThat(responseBody,
					JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/deletenotfound.json"));

			
		}

	}

	/*@Test
	public void existentdeleterequest() {
		RestAssured.baseURI = "https://jobs123.herokuapp.com/";
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestparams = new JSONObject();
		requestparams.put("Job Id", "1020");
		// requestparams.put("Job Title", "SDET31");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestparams.toJSONString());
		Response response = httpRequest.request(Method.DELETE, "/Jobs");
		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Inside TC5_Delete Response Body is" + responseBody);
		// status code verification
		int statusCode = response.getStatusCode();
		System.out.println("status code is:" + statusCode);
		Assert.assertEquals(statusCode, 404);
	}*/

}
