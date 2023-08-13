package OAUTH;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.junit.rules.ExpectedException;
import org.testng.Assert;

import OAUTH.Api;
import OAUTH.Courses;
import OAUTH.GetCourses;
import OAUTH.WebAutomation;
import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;



public class OAuth {

	static String expected[]= {"Selenium Webdriver Java",
			"Cypress" ,
			"Protractor"};
	public static void main(String[] args) throws InterruptedException 
	{
		String url = "https://www.googleapis.com/oauth2/v4/token?code=4%2F0AWtgzh7x9USBtwvDOSGsdaFuRjeAFCA7B12-eEARxWMkLxB4yqOv1RTthI-xxjkjeHJ1Tw&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&grant_type=authorization_code#";
		String[] code=url.split("code=");
		System.out.println(code[1]+" iam the code");
		String[] finalCode=code[1].split("&scope");
		System.out.println(finalCode[0]+"iam the actual code");
		System.out.println("*******************************************");
		//
		// urlEncodingEnabled(false)----will not perform any encoding for the response
		String response =given().queryParams("code", finalCode)
				                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				                .queryParams("grant_type", "authorization_code")
				                .queryParams("state", "verifyfjdss")
				                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
						.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		 //
         System.out.println(response+" iam the response");

		JsonPath jsonPath = new JsonPath(response);
//		String accessToken = jsonPath.getString("access_token");
//		System.out.println(accessToken+" iam the accesstoken");
		GetCourses gc = given().contentType("application/json").queryParams("access_token", "ya29.a0AVvZVsoodB37qw2aevHqOLFcdt6bwcpnZMSEv_LpadYXNE2MCP11Wz7MjTmd3g38TtSHaAQS2cgBFPWV9UC70sD5O0mnbpT1vN1Vts7nFut1StIBdS47XsVqHS19rX6gt5otFOS1MDV9UW4wyS4DI0LesqCmBgaCgYKAaoSARESFQGbdwaIa44ZvfrxBMe5bji2L8fJ8w0165").expect().defaultParser(Parser.JSON)
					.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);

		System.out.println(gc+" iam the acutual response");
		//
		String instructor=gc.getInstructor();
		System.out.println(instructor+ "         instructor");
		Courses courses=gc.getCourses();
		System.out.println("**************");
		System.out.println(courses+"iam the courses");
		List<WebAutomation> webCourses=courses.getWebAutomation();
		List<Api> apiCourses=courses.getApi();
		//
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				apiCourses.get(i).getPrice();
			}	
		}
		// using array list bcz it will grow in size
		ArrayList<String> a= new ArrayList<String>();
		for(int i=0;i<webCourses.size();i++)
		{	
			String course=webCourses.get(i).getCourseTitle();
			a.add(course);	
//			Assert.assertEquals(expected.length, webCourses.size());
//			// Assert.assertEquals(expected[i], webCourses.get(i).getCourseTitle());
//		    Assert.assertEquals(expected[i], course);
		}
		List<String> list=Arrays.asList(expected);
		System.out.println(list+ "iam the list");
		System.out.println(a+ "iam the a ");
		Assert.assertTrue(a.equals(list));
		

	}

}
