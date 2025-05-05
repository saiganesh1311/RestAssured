package practise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import static io.restassured.RestAssured.*;

import files.Payload;
import files.ReusableCode;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Creating a place
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= ReusableCode.jsonToRaw(response);
		String placeID= js.getString("place_id");
		
		//Update place
		String newAddress= "Banglore, Karnataka";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("/maps/api/place/update/json")
		.then().log().all();
		
		//Get Place
		
		String value=given().queryParam("key","qaclick123").queryParam("place_id", placeID).header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json")
		.then().log().all().extract().response().asString();
		
		JsonPath js1= ReusableCode.jsonToRaw(value);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		
		
		Assert.assertEquals(newAddress, actualAddress);
		
		
		
	}

}
