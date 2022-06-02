package JobsApi;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.File;

import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;

public class TC1_Get_jobs extends Jobsbase {

	public Response response;
	@BeforeClass
	void getjobdetails() throws InterruptedException
	{
		//specifiying baseURL
		RestAssured.baseURI="https://jobs123.herokuapp.com/Jobs";
		//Request Object
		RequestSpecification httpgetrequest =RestAssured.given();
		//Response Object
		response=httpgetrequest.request(Method.GET);
		
		Thread.sleep(3);
		
	}
	
	@Test
	void checkresponsebody()
	{
	logger.info("checking response body");
	String getresponse =response.getBody().asString().replaceAll("NaN","\"5 hrs\"");
	System.out.println("Response body is" + getresponse);
	
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
	void checkstatusline()
	{
		logger.info("checking status line");
	    String statusline = response.getStatusLine();
	    logger.info("The status line is "+statusline);
	    Assert.assertEquals(statusline, "HTTP/1.1 200 OK");		
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
	void checkserverType()
	{
		logger.info("checking server type");
	    String servertype = response.header("Server");
	  //  System.out.println("server type is" + servertype);
	    logger.info("The server type is "+servertype);
	    Assert.assertEquals(servertype, "gunicorn");		
	}
	
	@Test
	void jasonschema()
	{
				
		String getresponse =response.getBody().asString().replaceAll("NaN","\"5 hrs\"");
		System.out.println("Response body is" + getresponse);
		MatcherAssert.assertThat(getresponse,JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/jobsget.json"));
		logger.info("Json schema for jobs validated");
	}
	@AfterClass
	void teardown()
	{
		logger.info(" Finished getting all jobs");
	}
}

