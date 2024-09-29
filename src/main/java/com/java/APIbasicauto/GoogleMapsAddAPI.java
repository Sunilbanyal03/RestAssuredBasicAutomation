package com.java.APIbasicauto;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJOLearning.GoogleMapsAdd;
import POJOLearning.Location;

public class GoogleMapsAddAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GoogleMapsAdd gm=new GoogleMapsAdd();
		gm.setAccuracy(50);
		gm.setAddress("29, side layout, cohen 09");
		gm.setLanguage("French-IN");
		gm.setWebsite("http://google.com");
		gm.setName("Frontline house");
		gm.setPhone_number("(+91) 983 893 3937");
		
		
		Location lc=new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		 List<String> t1=new ArrayList<String>();
		 t1.add("shoe park");
		 t1.add("shop");
		 
		 gm.setTypes(t1);
		 gm.setLocation(lc);

		
		
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response= given().queryParam("key", "qaclick123").body(GoogleMapsAdd.class).post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);

	}

}
