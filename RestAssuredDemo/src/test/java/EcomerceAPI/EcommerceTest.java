package EcomerceAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import EcommerceAPI.LoginRequest;
import EcommerceAPI.LoginResponse;
import EcommerceAPI.OrdersRequest;
import EcommerceAPI.orderDetails;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class EcommerceTest 
{
	public static void main(String[] args)
	{
		RequestSpecification requestSpecification =new RequestSpecBuilder()
														.setBaseUri("https://rahulshettyacademy.com")
														.setContentType(ContentType.JSON).build();
		//
		LoginRequest login= new LoginRequest();
		login.setUserEmail("saidurgareddy4121@gmail.com");
		login.setUserPassword("Kartheek@01");
		// login API
		RequestSpecification loginRes=given().log().all().spec(requestSpecification).body(login);
		//
		LoginResponse response=loginRes.when().post("/api/ecom/auth/login").
		then().extract().response().as(LoginResponse.class);
		//
		String token=response.getToken();
		String userId=response.getUserId();
		String message=response.getMessage();
		System.out.println(userId);
		// create product API
		RequestSpecification addProductBase =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).build();
		//
		RequestSpecification product=given().relaxedHTTPSValidation().log().all().spec(addProductBase)
											.param("productName", "qwerty").param("productCategory", "fashion")
											.param("productAddedBy", userId).param("productSubCategory", "shirts")
											.param("productPrice", "11500").param("productDescription", "Lenova")
											.param("productFor", "men").multiPart("productImage",new File("C:\\Users\\003KT8744\\Downloads\\QWERTY_keyboard.jpg"));
				
				String addProductResponse=product.when().post("/api/ecom/product/add-product").then().extract().response().asString();
		
				JsonPath js= new JsonPath(addProductResponse);
				String productid=js.get("productId");
				System.out.println(productid);
		// create order
		RequestSpecification orderBase =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).setContentType(ContentType.JSON).build();
		//
		OrdersRequest orderRequest= new OrdersRequest();
		orderDetails orderDetails= new orderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productid);
		// create a list since setOrders will accept list ... and in future more elements can be added
		ArrayList<orderDetails> orderDetailsList= new ArrayList();
		orderDetailsList.add(orderDetails);
		//
		orderRequest.setOrders(orderDetailsList);
		//
		String createOrder=given().log().all().spec(orderBase).body(orderRequest).when().post("/api/ecom/order/create-order").then().extract().response().asString();
	    System.out.println(createOrder);
	    // Delete order
		RequestSpecification deleteBase =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).setContentType(ContentType.JSON).build();
		//
		String deleteResponse=given().log().all().spec(deleteBase).pathParam("productId", productid)
		.when().delete("/api/ecom/product/delete-product/{productId}").then().extract().response().asString();
		System.out.println(deleteResponse);
	}

}
