package practise;

import org.testng.Assert;

import files.Payload;
import files.ReusableCode;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static int count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// no of courses 
		JsonPath js= ReusableCode.jsonToRaw(Payload.CoursePrice());
		count= js.getInt("courses.size()");
		System.out.println(count);
		
		// purchased amount	
		int purchasedAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchasedAmount);
		
		// title of first course
		String firstTitle= js.get("courses[0].title");
		System.out.println(firstTitle);
		
		//title of all course and their respective price
		for(int i=0;i<count;i++)
		{
			String courseTitle= js.get("courses["+i+"].title");
			String coursePrice=js.get("courses["+i+"].price").toString();
			System.out.println(courseTitle+" "+coursePrice);
		}
		
		//no of copies sold by RPA
		for(int i=0;i<count;i++)
		{
			 String courseTitle= js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				String copies=js.get("courses["+i+"].copies").toString();
				System.out.println("the no of copies sold of RPA are "+ copies);
				break;
			}
		}
		
		//sum of all courses matches the purchase amount
		int sum=0;
		for(int i=0;i<count;i++)
		{
			int totalPrice= (js.getInt("courses["+i+"].price"))*(js.getInt("courses["+i+"].copies"));
			sum= sum+totalPrice;			
			
		}
		int PurchaseAmount=js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, PurchaseAmount);
		System.out.println("hello there");
		

	}

}
