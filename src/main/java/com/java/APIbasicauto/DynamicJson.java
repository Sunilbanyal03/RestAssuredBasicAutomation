package com.java.APIbasicauto;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
	String addBookResponse=	given().header("Content-Type" , "text/plaintext").body(Payload1.bookPayload(isbn,aisle))
		.when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().asString();
		
	
	JsonPath js=new JsonPath(addBookResponse);
	String actualResponsAddBookMsg=js.get("Msg");
	String actualResponsAddBookid=js.get("ID");
	System.out.println("Msg is "+actualResponsAddBookMsg+" ID is "+actualResponsAddBookid);
	Assert.assertEquals(actualResponsAddBookMsg, "successfully added");
	
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData(){
		 
		 return new Object[][] {{"QAZq","29266"},{"QAZe","29267"},{"QAZt","29268"},{"QAZy","29269"},{"QAZu","29260"}};
	}

}
