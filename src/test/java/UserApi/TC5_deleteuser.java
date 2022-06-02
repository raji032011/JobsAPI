package UserApi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC5_deleteuser extends Jobsbase {
	@Test
	void deleteuser() throws InterruptedException {

		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com";
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("APIPROCESSING");
		authscheme.setPassword("2xx@Success");
		RestAssured.authentication = authscheme;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.delete("/Users/U1009");
		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("response Body after deleting user is" + responseBody);
	
		// status code verification
		int statusCode = response.getStatusCode();
		System.out.println("status code is:" + statusCode);
		Assert.assertEquals(statusCode, 200);
		

	}
	@Test
	void deleteexistinguser() throws InterruptedException
	{
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com";
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("APIPROCESSING");
		authscheme.setPassword("2xx@Success");
		RestAssured.authentication = authscheme;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.delete("/Users/U1532");
		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body after delete(existing user) is" + responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("status code is:" + statusCode);
		Assert.assertEquals(statusCode, 404);
		
		
	}
}
