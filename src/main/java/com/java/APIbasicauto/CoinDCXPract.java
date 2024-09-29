package com.java.APIbasicauto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;

import POJOLearning.*;


import io.restassured.path.json.JsonPath;

public class CoinDCXPract {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		String ratefile=new String(Files.readAllBytes(Paths.get("C:\\Users\\lenovo\\eclipse-workspace\\APIbasicauto\\src\\main\\resources\\crptoRateandVolume")));
		System.out.println(ratefile);
		
		//List<CoinDcxRateFile> cn=ratef
	    	
	    }
	
			
		


}
