package com.java.APIbasicauto;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;



public class BasicAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Validate if Place API is working as expected
		// Given- all input details
		//When- Submit the API (resource and http method)
		// Then -validate the response 
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		given().log().all().queryParams("key","qaclick123").header("Content-Type","application/json")
		.body(Payload1.Addplace())
		.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server",equalTo( "Apache/2.4.52 (Ubuntu)"));
	}

}
