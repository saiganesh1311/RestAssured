package practise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

import files.ReusableCode;

public class Authorization_OAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust").log().all()
		.post("/oauthapi/oauth2/resourceOwner/token").asString();
		
		JsonPath js= ReusableCode.jsonToRaw(response);
		String accessToken= js.getString("access_token");
		
		GetCourse output=given().queryParam("access_token",accessToken)
		.when().get("/oauthapi/getCourseDetails").as(GetCourse.class);
		
		System.out.println(output.getLinkedIn());
		System.out.println(output.getCourses().getApi().get(1).getPrice());
		
		List<Api> apiCourses= output.getCourses().getApi();
		
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		List<WebAutomation> wa= output.getCourses().getWebAutomation();
		
		for(int i=0;i<wa.size();i++)
		{
			System.out.println(wa.get(i).getCourseTitle());
		}
		
		
		

	}

}
