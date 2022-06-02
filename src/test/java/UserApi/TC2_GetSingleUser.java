package UserApi;

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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC2_GetSingleUser extends Jobsbase {
	
	@BeforeClass
	void getsingleuserdetails() throws InterruptedException
	{
		//specifiying baseURL
		RestAssured.baseURI="https://springboot-lms-userskill.herokuapp.com";
		PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
	    authscheme.setUserName("APIPROCESSING");
	    authscheme.setPassword("2xx@Success");  
	    RestAssured.authentication=authscheme;
		//Request Object
		RequestSpecification httpgetrequest =RestAssured.given();
		//Response Object
		response=httpgetrequest.request(Method.GET,"/Users/U06");
		Thread.sleep(3);
		
	}
	
	@Test
	void checkresponsebody()
	{
	logger.info("checking response body");
	String responsebody =response.getBody().asString();
  	logger.info("Response body is " +responsebody);
	Assert.assertTrue(responsebody!=null);
	}
	
	@Test
	void checkstatuscode()
	{
	//Status code validation
	logger.info("checking statuscode");
	int statuscode = response.getStatusCode();
	logger.info("The status code is "+statuscode);
	Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	void checkresponsetime()
	{
		logger.info("checking response time");
		long responsetime = response.getTime();
		logger.info("Response time is " + responsetime);
		if(responsetime>1000)
		logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responsetime<2000);
	}
	
	@Test
	void checkcontentType()
	{
		logger.info("checking content type");
	    String contenttype = response.getContentType();
	  //  System.out.println("Content type is" + contenttype);
	    logger.info("The content type is "+contenttype);
	    Assert.assertEquals(contenttype, "application/json");		
	}
	@Test
	void jasonschemausers()
	{
		logger.info("checking response body");
		MatcherAssert.assertThat(response.asString(),JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/oneuser.json"));
		logger.info("Json schema validated");
	}
	@AfterClass
	void teardown()
	{
		logger.info(" Finished getting all jobs");
	}

	
	
	
		
}

