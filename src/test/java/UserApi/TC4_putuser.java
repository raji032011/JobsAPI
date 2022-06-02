package UserApi;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Baseclass_jobs.Jobsbase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_putuser extends Jobsbase {
	@Test
	 void putuser() throws InterruptedException
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
	  requestParams.put("name","Kumareii,Sadhukhan");
	  requestParams.put("phone_number","9141221267");
	  requestParams.put("location","USA");
	  requestParams.put("time_zone","EST");
	  requestParams.put("linkedin_url","www.linkedin.com/in/BaisaliSadhukhan");
	  requestParams.put("education_ug","UG");
	  requestParams.put("education_pg","PG");
	  requestParams.put("visa_status","H4");
	  requestParams.put("comments","THROUGH INDEED"); 
	  httpRequest.header("Content-Type","application/json");
	  httpRequest.body(requestParams.toJSONString()); // attach above data to the request
	  
	  //Response object
	  Response response=httpRequest.request(Method.PUT,"/Users/U06");  
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
}

