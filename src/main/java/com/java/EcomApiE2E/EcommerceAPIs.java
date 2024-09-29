package com.java.EcomApiE2E;

import POJOLearning.Login;
import POJOLearning.LoginResponse;
import POJOLearning.OrderDetail;
import POJOLearning.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;


public class EcommerceAPIs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Login to create token
		
		Login lgin=new Login();
		lgin.setUserEmail("Suniil.banyal@gmail.com");
		lgin.setUserPassword("Password@123");
		
		RequestSpecification req1=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).setBody(lgin).build();
		
		
		ResponseSpecification res1=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		LoginResponse response=given().spec(req1).when().post("api/ecom/auth/login").then().log().all().spec(res1).extract().response().as(LoginResponse.class);
		System.out.println("Token for user authentication is \n"+response.getToken());
		String token=response.getToken();
		String userid=response.getUserId();

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		// Create new product
		
		
		RequestSpecification reqcreateproduct=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		RequestSpecification reqcreateproductfull=given().log().all().spec(reqcreateproduct).formParam("productName", "Sunils Product")
				         .formParam("productAddedBy", userid).formParam("productCategory", "fashion").formParam("productSubCategory", "shirts").formParam("productPrice", "11500")
				         .formParam("productDescription", "Addias Originals").formParam("productFor", "Men")
				         .multiPart("productImage", new File("C:\\Users\\lenovo\\Downloads\\IMG_20240115_164621.jpg"));
		
		ResponseSpecification responsecreateproduct=new ResponseSpecBuilder().expectStatusCode(201).build();
		
		Response respcreateproductAPI= given().log().all().spec(reqcreateproductfull).when().post("api/ecom/product/add-product").then().spec(responsecreateproduct).extract().response();
		System.out.println(respcreateproductAPI.asString());
		
		JsonPath js=new JsonPath(respcreateproductAPI.asString());
		String productID=js.getString("productId");
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		// Create new order
		
		RequestSpecification createorder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization", token).build();
		
		OrderDetail od=new OrderDetail();
		od.setCountry("India");
		od.setProductOrderedId(productID);
		
		List<OrderDetail> orderss=new ArrayList<OrderDetail>();
		orderss.add(od);
		
		Orders or=new Orders();
		or.setOrders(orderss);
		
		RequestSpecification createorderfinal=given().log().all().spec(createorder).body(or);
		
		String reposnecreateorder=createorderfinal.when().post("api/ecom/order/create-order").then().log().all().extract().response().asPrettyString();
		System.out.println(reposnecreateorder);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		// Delete product
		
		RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON)
				.build();

				RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",productID);

				String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
				extract().response().asString();

				JsonPath js1 = new JsonPath(deleteProductResponse);

				Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
	}

}
