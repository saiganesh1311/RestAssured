package practise;

import static io.restassured.RestAssured.*;

import files.ReusableCode;
import io.restassured.path.json.JsonPath;

public class GraphQL {

	public static void main(String[] args) {
		
		String response=given().log().all().header("content-type","application/json")
		.body("{\"query\":\"\\nquery($episodeId:Int!)\\n{\\n  \\n  episode(episodeId:$episodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n}\",\"variables\":{\"episodeId\":14590}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

		System.out.println(response);
		
		JsonPath js=ReusableCode.jsonToRaw(response);
		System.out.println(js.getString("data.episode.name"));
		
	}

}
