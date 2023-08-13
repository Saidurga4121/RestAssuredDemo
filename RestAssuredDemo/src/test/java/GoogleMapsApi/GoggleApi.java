
package GoogleMapsApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GoogleAPI.GoggleMapsSerialize;
import GoogleAPI.Location;

public class GoggleApi 
{
	public static void main(String[] args)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//
		// for serialization, we need to add data bind dependencies
		GoggleMapsSerialize obj= new GoggleMapsSerialize();
		Location loc= new Location();
		//
		obj.setAccuracy(50);
		obj.setAddress("29, side layout, cohen 09");
		obj.setLanguage("French-IN");
		//
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		obj.setLocation(loc);
		obj.setName("Frontline house");
		obj.setPhone_number("(+91) 983 893 3937");
		//
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		obj.setTypes(myList);
		obj.setWebsite("http://google.com");
		
		
		
		
		RequestSpecification requestSpecification =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		ResponseSpecification responseSpecification= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("key","qaclick123");
		// u can add multiple params here
		//given().log().all().spec(requestSpecification).pathParams("id",2).body(obj)
		//.when().log().all().post("/maps/api/place/{id}")
		//
		// it will log all only if the validation fails
		Response response=given().
								log().ifValidationFails().
								spec(requestSpecification).
								queryParams(map).body(obj)
						.when().log().all().post("/maps/api/place/add/json")
						.then().
								log().ifError().
								spec(responseSpecification).
								extract().response()	;	//
		System.out.println(response.path("place_id"));
	}

}
