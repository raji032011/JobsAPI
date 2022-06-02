package UserApi;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;

public class TC1_Getallusers extends Jobsbase {

	@BeforeClass
	void getuserdetails() throws InterruptedException {
		// specifiying baseURL
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com";
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("APIPROCESSING");
		authscheme.setPassword("2xx@Success");
		RestAssured.authentication = authscheme;
		// Request Object
		RequestSpecification httpgetrequest = RestAssured.given();
		// Response Object
		response = httpgetrequest.request(Method.GET, "/Users");
		Thread.sleep(10);

	}

	@Test
	void checkresponsebody() throws IOException {
		logger.info("checking response body");
		String responsebody = response.getBody().asString();
		BufferedReader reader =new BufferedReader(new InputStreamReader(response.getBody().asInputStream()));
		System.out.println("mjn " + reader.readLine());
		try {
			FileWriter myWriter = new FileWriter("filename_get.txt");
			myWriter.write(responsebody);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkstatuscode() {
		// Status code validation
		logger.info("checking statuscode");
		int statuscode = response.getStatusCode();
		logger.info("The status code is " + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	void checkresponsetime() {
		logger.info("checking response time");
		long responsetime = response.getTime();
		logger.info("Response time is " + responsetime);
		if (responsetime > 1000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responsetime < 2000);
	}

	@Test
	void checkcontentType() {
		logger.info("checking content type");
		String contenttype = response.getContentType();
		// System.out.println("Content type is" + contenttype);
		logger.info("The content type is " + contenttype);
		Assert.assertEquals(contenttype, "application/json");
	}
 @Test
	void jasonschemausers()
	{
		logger.info("checking response body");
		MatcherAssert.assertThat(response.asString(),JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/users.json"));
		logger.info("schema validated");
	}
	@AfterClass
	void teardown() {
		logger.info(" Finished getting all jobs");
	}

}
