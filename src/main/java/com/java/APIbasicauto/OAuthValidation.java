package com.java.APIbasicauto;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJOLearning.Api;
import POJOLearning.GetCourses;
import POJOLearning.Mobile;
import POJOLearning.WebAutomation;
public class OAuthValidation {
	
	@Test
	public void oauth() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		
		String responseAuthrizationServer=given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
				.when().post(RestAssured.baseURI).asString();
		
		System.out.println(responseAuthrizationServer);
		System.out.println("==================================================================================================================================================================================");
		JsonPath js=new JsonPath(responseAuthrizationServer);
		String accessToken=js.get("access_token");
		
		//String reponseGetCourses=given().queryParam("access_token", accessToken).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		GetCourses gc=given().queryParam("access_token", accessToken).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
		System.out.println("==================================================================================================================================================================================");
		System.out.println(gc);
		System.out.println(gc.getExpertise());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getServices());
		System.out.println(gc.getUrl());
		System.out.println(gc.getCourses().getWebAutomation().get(1).getCourseTitle());
		System.out.println(gc.getCourses().getMobile().getLast().getPrice());
		System.out.println(gc.getCourses().getApi().size());
		System.out.println(gc.getCourses().getWebAutomation().getFirst().getCourseTitle());
		//	Assert.assertEquals(gc, Payload1.getCourseDetails());
		System.out.println("==================================================================================================================================================================================");
		
		List<Api> A =gc.getCourses().getApi();
		System.out.println(A); 
		for(int i=0;i<A.size();i++) {
			if(A.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
				
				System.out.println("Price for "+A.get(i).getCourseTitle()+" is "+A.get(i).getPrice());
			}
		}

		System.out.println("====================================================================================================================================================================================");
		
		String expectedCourseTitlesWebAuto[]= {"Selenium Webdriver Java","Cypress","Protractor"};
		ArrayList<String> actualCourseTitles=new ArrayList<String>();
		List<Api> Api=gc.getCourses().getApi();
		List<WebAutomation> Webauto=gc.getCourses().getWebAutomation();
		List<Mobile> Mobile=gc.getCourses().getMobile();
		
		for(int j=0;j<Api.size();j++) {
			
			System.out.println("Price for course "+Api.get(j).getCourseTitle().toUpperCase()+" is "+Api.get(j).getPrice());
		}
		
        for(int k=0;k<Webauto.size();k++) {
			
			System.out.println("Price for course "+Webauto.get(k).getCourseTitle().toUpperCase()+" is "+Webauto.get(k).getPrice());
			actualCourseTitles.add(Webauto.get(k).getCourseTitle()); 
			System.out.println(actualCourseTitles);
			
		} 
	 
        
        for(int l=0;l<Mobile.size();l++) {
			
			System.out.println("Price for course "+Mobile.get(l).getCourseTitle().toUpperCase()+" is "+Mobile.get(l).getPrice());
		} 
        
        List<String> expectedCourseTitlesArrayList =Arrays.asList(expectedCourseTitlesWebAuto);
        Assert.assertEquals(actualCourseTitles, expectedCourseTitlesArrayList);
        
	}

}
