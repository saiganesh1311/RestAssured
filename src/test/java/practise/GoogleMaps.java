package practise;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.GetLocation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class GoogleMaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlace ap= new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("import io.restassured.RestAssured");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("(+91) 983 893 3937");
		List<String> MyList= new ArrayList<String>();
		MyList.add("shoe park");;
		MyList.add("shop");
		ap.setType(MyList);
		GetLocation l= new GetLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		given().queryParam("key", "qaclick123")
		.body(ap).log().all().when().post("/maps/api/place/add/json").then().assertThat().statusCode(200);

	}

}
