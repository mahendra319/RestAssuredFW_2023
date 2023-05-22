package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {

	
	String baseUri = "https://gorest.co.in";
	String pathParam = "/public-api/users/";
	String token = "7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
	
	
	
	@Test
	public void getAllUserListApiTest() {
		
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer "+token);
		Response response = RestClient.doGet("JSON", baseUri, pathParam, authToken, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	@Test
	public void getUserWithQueryParamApiTest() {
		
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer "+token);
		
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("name", "Kama Adiga");
		paramsMap.put("gender", "male");
		paramsMap.put("status", "active");
		
		Response response = RestClient.doGet("JSON", baseUri, pathParam, authToken, paramsMap, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	
	
	
	
	
	
	
	
}
