package Baseclass_jobs;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Jobsbase {

	public static RequestSpecification httpRequest;
	public static RequestSpecification httpRequest1;
	public static Response response;
	public static Response response1;
	public Logger logger;

	@BeforeClass
	public void setup() {
		logger = logger.getLogger("Jobs Api");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

}
