package GoogleMapsApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payLoad;

public class Basics 
{
	protected static String place_id;
	static JsonPath js;
	
	@Test
	public static void sample() throws IOException
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		// add place api
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
								.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\003KT8744\\Documents\\rahul.json"))))
						.when().post("/maps/api/place/add/json")
						.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
								.header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response().asString();
		//
		System.out.println(response);
		//
		// converts string to json 
		js= new JsonPath(response);
		place_id=js.getString("place_id");
		System.out.println(place_id+ " is place id");		
		// put api
		String putResponse=given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
								  .body("{\r\n"
							       		+ "\"place_id\":\""+place_id+"\",\r\n"
							       		+ "\"address\":\"70 Summer walk, USA\",\r\n"
							       		+ "\"key\":\"qaclick123\"\r\n"
							       		+ "}")
						  .when().put("maps/api/place/update/json")
						  .then().assertThat().body("msg", equalTo("Address successfully updated")).extract().response().asString();
		//
		System.out.println(putResponse+"iam put response");
		
		// get api
		
		String getResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
						   .when().get("maps/api/place/get/json/json")
						   .then().assertThat().statusCode(200).extract().response().asString();
		//
		System.out.println(getResponse+" iam get response");
		//
		js=new JsonPath(getResponse);
		String address=js.getString("address");
		Assert.assertEquals(address, "70 Summer walk, USA","iam matched");
		
	}

}
