package practise;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import files.ReusableCode;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

public class EcommerceAPI {
	
	public static void main(String[] args)
	{
	
	RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			                .setContentType(ContentType.JSON).build();
	
	
	LoginRequest loginRequest = new LoginRequest();
	loginRequest.setUserEmail("saiganesh370@gmail.com");
	loginRequest.setUserPassword("Saiganesh@11");
	RequestSpecification reqLogin=given().spec(req).body(loginRequest).log().all();
	LoginResponse loginResponse =reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
	String token=loginResponse.getToken();
	String userId= loginResponse.getUserId();
	
	//Add product
	
	RequestSpecification addProdBaseReq= new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization",token)
            .build();
	RequestSpecification addProdReq = given().log().all().spec(addProdBaseReq).param("productName", "Laptop")
			.param("productAddedBy", userId).param("productCategory", "fashion")
			.param("productSubCategory", "shirts").param("productPrice", "11500")
			.param("productDescription", "Lenova").param("productFor", "men")
			.multiPart("productImage",new File("C:\\Users\\K.Sai Ganesh\\Pictures\\Camera Roll\\WIN_20210530_10_24_06_Pro.jpg"));
	
	String AddProductResponse=addProdReq.when().post("/api/ecom/product/add-product")
			.then().extract().response().asString();
	JsonPath js= ReusableCode.jsonToRaw(AddProductResponse);
	String productId=js.getString("productId");
	
	
	//create order
	RequestSpecification createProdBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType(ContentType.JSON)
			.addHeader("authorization",token)
            .build();
	OrderDetails od= new OrderDetails();
	od.setCountry("India");
	od.setProductOrderedId(productId);
	
	List<OrderDetails> orderDetailsList= new ArrayList<OrderDetails>();
	orderDetailsList.add(od);
	
	Orders orders= new Orders();
	orders.setOrders(orderDetailsList);
	
	RequestSpecification createProdReq= given().log().all().spec(createProdBaseReq).body(orders);
	createProdReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response();
	
	
	
	//delete the product
	
    RequestSpecification deleteProdBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType(ContentType.JSON)
			.addHeader("authorization",token)
            .build();
    RequestSpecification deleteProdReq=given().spec(deleteProdBaseReq).pathParam("productId",productId);
    String deleteResponse=deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}")
    .then().log().all().extract().response().asString();
    
    JsonPath js1= ReusableCode.jsonToRaw(deleteResponse);
    System.out.println(js1.getString("message"));
	
	
	
	
	
	}
	

}
