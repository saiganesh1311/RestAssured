package practise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import files.ReusableCode;

public class BugTracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RestAssured.baseURI= "https://saiganesh370.atlassian.net";
		
		String repsonse = given().header("Content-Type","application/json").header("Authorization","Basic c2FpZ2FuZXNoMzcwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjB0QlNsWHZRRzBVdFQ5OTdKME85bzFORlZsWGNxUU1WMldHS2ljOThIU21CTGxuMl9RZlZjenB6Z212X0VCOThfcU1TVFVMa1RGc0FPRW5VdFNFRGx5bFNnLUpza3dNM1NVZXNXWTgxV0NfbFFOaXVKLUhDNmg2cVFiLVM1M1pQUkxORE1Pd0VOYlEtb2ZHWjR6bkZQWUR2Y1RQYXhXbGZTMUJzaWlOOWJ0bXM9OUQ1RERDMUY=")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Dropdowns not working as expected\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all().post("/rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().asString();
		JsonPath js= ReusableCode.jsonToRaw(repsonse);
		String IssueID= js.getString("id");
	    // adding the attachments
		
		given().header("Authorization","Basic c2FpZ2FuZXNoMzcwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjB0QlNsWHZRRzBVdFQ5OTdKME85bzFORlZsWGNxUU1WMldHS2ljOThIU21CTGxuMl9RZlZjenB6Z212X0VCOThfcU1TVFVMa1RGc0FPRW5VdFNFRGx5bFNnLUpza3dNM1NVZXNXWTgxV0NfbFFOaXVKLUhDNmg2cVFiLVM1M1pQUkxORE1Pd0VOYlEtb2ZHWjR6bkZQWUR2Y1RQYXhXbGZTMUJzaWlOOWJ0bXM9OUQ1RERDMUY=")
		.header("X-Atlassian-Token","no-check").pathParam("key",IssueID )
		.multiPart("file",new File("C:\\Users\\K.Sai Ganesh\\Pictures\\SDHC\\DCIM\\avalahalli\\IMG_8664.CR3"))
		.log().all().post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
	}

}
