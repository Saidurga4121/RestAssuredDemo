package Requests;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJson 
{
	public static void main(String[] args)
	{
		JsonPath js= new JsonPath(payLoad.getDetails());
		//
		int count=js.getInt("courses.size()");
		System.out.println(count);
		//
		int amount=js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		//
		// String firstTitle=js.getString("courses[0].title");
		// System.out.println(firstTitle);
		//
		
		int sum=0;
		for(int i=0;i<count;i++)
		{
			String title=js.getString("courses["+i+"].title");
			int price=js.getInt("courses["+i+"].price"); // 50
			int copies=js.getInt("courses["+i+"].copies"); // 6
			int totalCost=price*copies; 
			sum=sum+totalCost;
			System.out.println(title + " "+ price);
			if(title.equalsIgnoreCase("RPA"))
			{
				System.out.println(title + "---->" + copies);
			}
		}
		System.out.println(sum+ "iam the sum");
	}
}
