package shared;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static io.restassured.RestAssured.given;

public class Dummy {

	JsonPath jp;
	Response response;
	Utils utils;
	static String access_token = "";
	ScenarioContext scenarioContext = new ScenarioContext();

	@BeforeTest
	public void getToken() {

		RestAssured.baseURI = "http://18.224.60.108:8080";
		response = given().contentType("application/x-www-form-urlencoded").param("client_id", "wiga-webapp")
				.param("username", "angesh.bhardwaj@wipro.com").param("password", "wiga123$")
				.param("grant_type", "password").when().post("/auth/realms/wiga-dev/protocol/openid-connect/token")
				.then().contentType(ContentType.JSON).log().all().extract().response();
		jp = new JsonPath(response.asString());

		access_token = jp.getString("access_token");

		System.out.println("Access token is : " + access_token);

	}

	@Test(dataProvider = "getData")
	public void requestData(APIdata apiData) throws IOException {

		response = given().baseUri("http://18.222.88.57:8762").queryParam("query", "hipaa")
				.queryParam("bot_instance_id", "1062").queryParam("query_id", "7b6ed203-a262-a867-9100-a2d234709745")
				.header("Authorization", "Bearer " + access_token).when().get("search-service/getanswers").then()
				.statusCode(200).header("Content-Type", "text/plain;charset=UTF-8").log().all().extract().response();

	//	jp = new JsonPath(response.asString());
		ResponseBody body =response.getBody();
		String bodyStringValue=body.asString();
		  // Validate if Response Body Contains a specific String
	    Assert.assertTrue(bodyStringValue.contains("HWS"));
	    Assert.assertTrue(bodyStringValue.contains("Health Insurance Portability and Accountability Act"));
	    

		//Assert.assertTrue(jsonObject.has("78.00"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<APIdata> ls = CSVReader.getCSV();
		Object[][] arr = new Object[ls.size()][1];

		for (int i = 0; i < ls.size(); i++) {
			arr[i][0] = ls.get(i);
		}

		return arr;
	}
}
