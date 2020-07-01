package steps;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import shared.ScenarioContext;
import shared.Utils;

public class StepDefination extends Utils {

	public StepDefination() throws FileNotFoundException {
		super();
	}


	RequestSpecification rs;
	JsonPath jp;
	Response response;
	ScenarioContext scenarioContext = new ScenarioContext();

	@Given("I create the payload")
	public void i_create_the_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
				
		rs = given().spec(requestSpecificationForPost());
	}

	@When("^(?:i|I) hit post API$")
	public void user_hits_post_API() {
		// Write code here that turns the phrase above into concrete actions

		response = rs.when().post("/auth/realms/wiga-dev/protocol/openid-connect/token").then()
				.contentType(ContentType.JSON).log().all().extract().response();

	}

	@Then("^I validate if API call is sucessful with status code \"([^\"]*)\"$")
	public void api_call_is_success_with_status_code(String statusCode) {
		Assert.assertEquals(Integer.parseInt(statusCode), response.getStatusCode());

	}

	@And("^(?:i|I) fetch the Access token$")
	public void fetch_the_Access_token() throws IOException {
		String access_token = "";

		//jp = new JsonPath(response.asString());

		//access_token = jp.getString("access_token");
		access_token = getJsonPath(response, "access_token");

		scenarioContext.setContext("access_token", access_token);
	}

	@And("^(?:i|I) use access token with authorization type as Bearer to make a Get call$")
	public void user_uses_access_token_with_authorization_type_as_Bearer_to_make_a_Get_call() throws IOException {
	
		rs = given().spec(requestSpecificationForGet()).header("Authorization",
				"Bearer " + scenarioContext.getContext("access_token"));
		response = rs.when().get("search-service/getanswers").then().header("Content-Type", "text/plain;charset=UTF-8")
				.log().all().extract().response();
	}

	
	@Then("I validate the data returned contains \"([^\"]*)\"$")
	public void i_validate_the_data_returned(String expectedTitle) {
		jp = new JsonPath(response.asString());
		System.out.println("Total answers on the API appearing is  " + jp.getInt("answers.size()"));
		
		for (int i=0;i<jp.getInt("answers.size()");i++) {
//			System.out.println("Title of book---- " +i+ ":" + jp.getString("answers[" + i + "].page_links.title"));
			String title=jp.getString("answers[" + i + "].page_links.title");
			if(title.contains(expectedTitle)) {
				System.out.println("Page Link---- " +i+ ":" + jp.getString("answers[" + i + "].page_links.page_link"));
				Assert.assertTrue(title.contains(expectedTitle),"JSON returned is not correct");
				break;
			}
			
		}
		
		
	}

}
