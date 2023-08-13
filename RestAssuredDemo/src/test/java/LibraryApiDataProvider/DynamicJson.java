package LibraryApiDataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson 
{
	@Test(dataProvider = "data")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		// add book
		String postResponse=given().header("Content-Type", "application/json").body(payLoad.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().body("Msg",equalTo("successfully added")).extract().response().asString();
		//
		System.out.println(postResponse+ " is my response");
		JsonPath js= new JsonPath(postResponse);
		String id=js.getString("ID");
		System.out.println(id+ " is my id");
		//	
		// delete book
		String deleteResponse=given().header("Content-Type", "application/json").body(payLoad.deleteBook(isbn+aisle))
		.when().delete("/Library/DeleteBook.php")
		.then().assertThat().body("msg",equalTo("book is successfully deleted")).extract().response().asString();
		//
		System.out.println(deleteResponse+" is my deleteResponse");
	}
	
	@DataProvider(name="data")
	public Object[][] getData()
	{
		return new Object[][] {{"edudw","123"},{"dcfreww","345"},{"scdsw","346"}};
	}

}
