package com.java.APIbasicauto;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	
	public void sumOfCourse() {
	
	JsonPath js2=new JsonPath(Payload1.CoursePrices());
	int count1=js2.getInt("courses.size()");
	int sum=0;
	for(int i=0;i<count1;i++) {
		
		int coursePrice=js2.getInt("courses["+i+"].copies")*js2.getInt("courses["+i+"].price");
		
		System.out.println(coursePrice);
		sum=sum+coursePrice;
		
	}
	
	System.out.println(sum);
	int purchaseAmount=js2.getInt("dashboard.purchaseAmount");
	Assert.assertEquals(sum, purchaseAmount);

}
}
