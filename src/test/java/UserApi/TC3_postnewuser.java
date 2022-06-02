package UserApi;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC3_postnewuser extends Jobsbase {
	@Test
	 void postuser() throws InterruptedException
	 {
	  
	  //Specify base URI
	  RestAssured.baseURI="https://springboot-lms-userskill.herokuapp.com";
	  PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
	  authscheme.setUserName("APIPROCESSING");
	  authscheme.setPassword("2xx@Success");
	  RestAssured.authentication=authscheme;
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();   
	  //Request paylaod sending along with post request
	  JSONObject requestParams=new JSONObject();
	  requestParams.put("name","Satyalakshmi,Sadhukhan");
	  requestParams.put("phone_number","9000005341");
	  requestParams.put("location","USA");
	  requestParams.put("time_zone","EST");
	  requestParams.put("linkedin_url","www.linkedin.com/in/BaisaliSadhukhan");
	  requestParams.put("education_ug","UG");
	  requestParams.put("education_pg","PG");
	  requestParams.put("visa_status","H4");
	  requestParams.put("comments","THROUGH LINKED POST"); 
	  httpRequest.header("Content-Type","application/json");
	  httpRequest.body(requestParams.toJSONString()); // attach above data to the request
	   //Response object
	  Response response=httpRequest.request(Method.POST,"/Users");
	  	  //print response in console window
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  //status code validation
	  logger.info("Status Code");
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  logger.info("Response body");
	  String responsebody =response.getBody().asString();
	  logger.info("Response body is " +responsebody);  	  
		  
 }
	
	@Test
	void jasonschemausers()
	{
		
		logger.info("checking response body");
		MatcherAssert.assertThat(response1.asString(),JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/post.json"));
		logger.info("Json schema validated");
	}	
	
	

}

