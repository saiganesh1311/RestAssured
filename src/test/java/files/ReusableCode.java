package files;

import io.restassured.path.json.JsonPath;

public class ReusableCode {
	
	public static JsonPath jsonToRaw(String response)
	{
		JsonPath js= new JsonPath(response);
		return js;
	}

}









