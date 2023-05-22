package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.pojo.User;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {

	
	String baseUri = "https://gorest.co.in";
	String pathParam = "/public-api/users/";
	String token = "7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
	
	@Test
	public void createUserApiPostTest() {
		
		User user = new User("Mahe K","active", "male", "Mahe@gmail.com");
		
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer "+token);
		
		Response response = RestClient.doPost("JSOn", baseUri, pathParam, authToken, null, true, user);
		
		int id = response.jsonPath().getInt("data.id");
		System.out.println("New user id is: "+id);
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getUserData() {
		Object [][] userData = ExcelUtil.getTestData("Users");
		return userData;
	}
	
	@Test(dataProvider = "getUserData")
	public void createMultipleUsersApiPostTest(String name, String status, String gender, String email) {
		
		User user = new User(name, status, gender, email);
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer "+token);
		
		Response response = RestClient.doPost("JSOn", baseUri, pathParam, authToken, null, true, user);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		int id = response.jsonPath().getInt("data.id");
		System.out.println("New user id is: "+id);
		
		System.out.println("**********************************");
	}
	
	
	
	
	
	
	
	
	
	
}
