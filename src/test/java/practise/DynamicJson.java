package practise;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableCode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class DynamicJson {
	
	@Test(dataProvider="bookData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI= "http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body(Payload.Addbook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= ReusableCode.jsonToRaw(response);
		System.out.println(js.getString("ID"));
	}
	
	@DataProvider(name="bookData")
	public Object[][] getData()
	{
		return new Object[][]
				{
					{"qwert","24680"},
					{"zxcvb","78772"},
					{"asdfg","362727"}
			
				};
	
	}

}
