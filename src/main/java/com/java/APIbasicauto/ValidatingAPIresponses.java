package com.java.APIbasicauto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ValidatingAPIresponses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Add Place- Update place with new address- get place id to validate new address
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
	String response=given().log().all().queryParams("key","qaclick123").header("Content-Type","application/json")
		.body(Payload1.Addplace())
		.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server",equalTo( "Apache/2.4.52 (Ubuntu)")).extract().response().asString();
	
	System.out.println(response);
	System.out.println("123456789");
	
	// Get Place from Jason Response
	JsonPath js=new JsonPath(response);
	String Place_id=js.getString("place_id");
	System.out.println(Place_id);
//=========================================================================================================================================================================================================//	
	//Update place using place id
	
	String AddresToUpdate= "wadgaonsheri,Pune";
	String updateAPIResponse=given().log().all().queryParams("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+Place_id+"\",\r\n"
			+ "\"address\":\""+AddresToUpdate+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}")
	.when().put("maps/api/place/update/json").then().assertThat().statusCode(200).extract().response().asString();
	
	
	System.out.println("UpdateAPI Response is "+updateAPIResponse);
	
//========================================================================================================================================================================================================//	
	// Get place API
	
	String GetplaceAPIResponse= given().queryParam("key", "qaclick123").queryParam("place_id", Place_id).when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().asString();			
	
	System.out.println(GetplaceAPIResponse);
	
	JsonPath js1=new JsonPath(GetplaceAPIResponse);
	String actualAddress=js1.getString("address");
	
	System.out.println("End of API chain");
	Assert.assertEquals(AddresToUpdate, actualAddress);
//======================================================================================================================================================================================================//			
	}
}
