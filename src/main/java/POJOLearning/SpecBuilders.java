package POJOLearning;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {

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
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		RequestSpecification reqspeci=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		RequestSpecification reqsp=given().spec(reqspeci).body(GoogleMapsAdd.class);
		
		ResponseSpecification respspeci=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		Response resp=reqsp.post("/maps/api/place/add/json").then().log().all().spec(respspeci).extract().response();
		
		
		String newresponse=resp.asString();
		
		System.out.println(newresponse);
		
		Assert.assertEquals(response, newresponse);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

	}

}
