package com.qa.gorest.util;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;
public class Token_Imgur {
	
	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	static String clientId = "37e9096b142a0e8";
	
	/**
	 * This method is used to get entire response 
	 * @return entire response in the form of Hash Map object
	 */
	public static Map<Object, Object> getAccessToken() {
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		
		paramsMap.put("refresh_token", "5d0be54ac8b00dd3ed59d0754a733092ef3e9660");
		paramsMap.put("client_id", "37e9096b142a0e8");
		paramsMap.put("client_secret", "e60617c38f07e048799f8245811e3c0912b6654a");
		paramsMap.put("grant_type", "refresh_token");
		
		
		JsonPath jsPath = given()
			.formParams(paramsMap)
		.when()
			.post("https://api.imgur.com/oauth2/token")
		.then()
			.extract().jsonPath();
		
		System.out.println(jsPath.getMap(""));
		
		appTokenMap =  jsPath.getMap("");
		
		return appTokenMap;
	}
	
	/**
	 * This method is used to get Access token only
	 * @return tokenMap , having Bearer token
	 */
	public static Map<String, String> getAuthToken() {
		
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Authorization token : "+authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		
		return tokenMap;
		
	}
	
	/**
	 * This method is used to get Client Id only
	 * @return
	 */
	public static Map<String, String> getClientId() {
		
		System.out.println("Client Id is: "+clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		
		return tokenMap;
	}
	
	
/**
 * 	This method is used to get access token from response
 * @return access token only
 */
	public static String getAccessTokenOnly() {
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		
		paramsMap.put("refresh_token", "5d0be54ac8b00dd3ed59d0754a733092ef3e9660");
		paramsMap.put("client_id", "37e9096b142a0e8");
		paramsMap.put("client_secret", "e60617c38f07e048799f8245811e3c0912b6654a");
		paramsMap.put("grant_type", "refresh_token");
		
		
		String accessToken = given()
			.formParams(paramsMap)
		.when()
			.post("https://api.imgur.com/oauth2/token")
		.then()
			.extract().path("access_token").toString();
		
		System.out.println("Access Token is: "+accessToken);
		
		return accessToken;
	}
//	public static void main(String [] args) {
//		
//		getAccessTokenOnly();
//	}

}
