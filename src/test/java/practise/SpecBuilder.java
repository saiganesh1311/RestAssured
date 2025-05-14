package practise;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

import files.ReusableCode;

public class SpecBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				                                          .addFormParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				                                          .addFormParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				                                          .addFormParam("grant_type", "client_credentials")
				                                          .addFormParam("scope", "trust").build();
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given().spec(req)
		.log().all()
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
