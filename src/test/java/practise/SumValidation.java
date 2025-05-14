package practise;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableCode;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sum()
	{
		JsonPath js= ReusableCode.jsonToRaw(Payload.CoursePrice());
		int sum=0;
		for(int i=0;i<js.getInt("courses.size()");i++)
		{
			int totalPrice= (js.getInt("courses["+i+"].price"))*(js.getInt("courses["+i+"].copies"));
			sum= sum+totalPrice;			
			System.out.println(sum);
		}
		int PurchaseAmount=js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, PurchaseAmount);
	}
	
	

}
