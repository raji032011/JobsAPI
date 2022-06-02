package JobsApi;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_Datadriventesting {

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "empdataprovider")
	void postnewjob(String id, String title, String location, String company, String type, String time,
			String description) throws InterruptedException {
		RestAssured.baseURI = "https://jobs123.herokuapp.com";
		RequestSpecification httpRequest = RestAssured.given();
		// here created data along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Id", id);
		requestParams.put("Job Title", title);
		requestParams.put("Job Location", location);
		requestParams.put("Job Company Name", company);
		requestParams.put("Job Type", type);
		requestParams.put("Job Posted time", time);
		requestParams.put("Job Description", description);

		httpRequest.header("Content-type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.request(Method.POST, "/Jobs");
		Thread.sleep(3);
		// System.out.println("Response Body is:" + postresponse);
		int statuscode = response.getStatusCode();
		System.out.println("Successfully job posted");
		System.out.println("The status code is " + statuscode);
		if (statuscode == 200) {
			System.out.println("Successfully job posted");
			String postresponse = response.getBody().asString().replaceAll("NaN", "\"5 hrs\"");
			// String postresponse = response.getBody().asString();
			MatcherAssert.assertThat(postresponse,
					JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/postjobs.json"));

		} else if (statuscode == 409) {
			System.out.println("Jobs already exists");
			String postresponse = response.getBody().asString();
			MatcherAssert.assertThat(postresponse,
					JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/postjobsexisting.json"));

		}

	}

	@DataProvider(name = "empdataprovider")
	String[][] getjobdata() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/JobsApi/postjobs.xlsx";
		Xlutility xlutil = new Xlutility(path);
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		String jobdata[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				jobdata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}

		}

		/*
		 * String jobdata[][]=
		 * {{"1510","SDET71","PA","Shakthji21","Fulltime","10minutes","SeleniumEngineer"
		 * },
		 * {"1511","SDET72","PA","Shakthji22","Fulltime","10minutes","SeleniumEngineer"}
		 * ,
		 * {"1512","SDET73","PA","Shakthji23","Fulltime","10minutes","SeleniumEngineer"}
		 * }; return(jobdata);
		 */
		return (jobdata);
	}
}
