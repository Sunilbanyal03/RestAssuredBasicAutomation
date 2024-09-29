package com.java.APIbasicauto;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js1=new JsonPath(Payload1.CoursePrices());
		int count=js1.getInt("courses.size()");
		
		//==================================================================================================================================================//
		//1. Print No of courses returned by API
		
		System.out.println(js1.getInt("courses.size()"));
		System.out.println(count);
		

		//===================================================================================================================================================//
		
		//2.Print Purchase Amount
		
		int purchaseAmount=js1.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//===================================================================================================================================================//
		
		//3. Print Title of the first course
		
		String firstCourseTitle=js1.getString("courses.title[0]");
		System.out.println(firstCourseTitle);
		
		//==================================================================================================================================================//
		
		//4. Print All course titles and their respective Prices
		
		for(int i=0;i<count;i++) {
		
		String courseTitles=js1.getString("courses["+i+"].title");
		int priceOfCourse=js1.getInt("courses["+i+"].price");
		System.out.println(courseTitles +" price is "+priceOfCourse);
		
		}
		
		//==================================================================================================================================================//
		
		//5. Print no of copies sold by RPA Course
		
		for(int i=0;i<count;i++) {
			
			String courseTitle1=js1.getString("courses["+i+"].title");
			if(courseTitle1.equalsIgnoreCase("RPA")) {
				
				int numberOfCopies=js1.getInt("courses["+i+"].copies");
				
				System.out.println("number of copies sold for RPA course are "+numberOfCopies);
			}
			
		}
		
		
		
	}

}
