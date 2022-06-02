package JobsApi;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_put_jobs extends Jobsbase {

	@Test
	void putjob() {
		RestAssured.baseURI = "https://jobs123.herokuapp.com/Jobs";
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-type", "application/json");
		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Id", "4113");
		requestParams.put("Job Title", "SDET26");
		requestParams.put("Job Location", "PAA");
		requestParams.put("Job Company Name", "Shakthiaasasas");
		requestParams.put("Job Type", "Fulltime");
		requestParams.put("Job Posted time", "11minutes");
		httpRequest.body(requestParams.toJSONString()); // attach above data to the request
		// Response object
		Response response = httpRequest.put("https://jobs123.herokuapp.com/Jobs");
		// String responseBody=response.getBody().asString();
		String putresponse = response.getBody().asString().replaceAll("NaN", "\"5 hrs\"");
		logger.info("Response Body is:" + putresponse);
		// status code validation
		logger.info("Status Code");
		int statusCode1 = response.getStatusCode();
		logger.info("Status code is: " + statusCode1);

		if (statusCode1 == 200) {

			// Jason schema validation
			MatcherAssert.assertThat(putresponse,
					JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/putvalid.json"));
		} else if (statusCode1 == 404) {
			// Jason schema validation
			MatcherAssert.assertThat(putresponse,
					JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/putnonexistingid.json"));
		}

	}

}

/*
 * @Test void put_nojob() {
 * RestAssured.baseURI="https://jobs123.herokuapp.com/Jobs"; //Request object
 * RequestSpecification httpRequest=RestAssured.given();
 * httpRequest.header("Content-type","application/json"); //Request paylaod
 * sending along with post request JSONObject requestParams=new JSONObject();
 * requestParams.put("Job Id","1000"); requestParams.put("Job Title","SDET776");
 * httpRequest.body(requestParams.toJSONString()); //Response object Response
 * response=httpRequest.put("https://jobs123.herokuapp.com/Jobs"); String
 * responseBody=response.getBody().asString(); logger.info("Response Body is:"
 * +responseBody); //status code validation logger.info("Status Code"); int
 * statusCode=response.getStatusCode();
 * logger.info("Status code is: "+statusCode); //Jason schema validation
 * MatcherAssert.assertThat(responseBody,
 * JsonSchemaValidator.matchesJsonSchemaInClasspath(
 * "Schema/putnonexistingid.json")); }
 */
