package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.util.Token_Imgur;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetImgurApiTest {
	
	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUserName;
	
	String baseURI = "https://api.imgur.com";
	
	String clientId = "37e9096b142a0e8";
	
	
	@BeforeMethod
	public void setUp() {
		
		tokenMap = Token_Imgur.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
	}
	
	
	@Test
	public void getAccountBlockStatus() {
		
		Map<String, String> authMap = Token_Imgur.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, "/account/v1/"+accountUserName+"/block", authMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
	@Test
	public void getAccountBase() {
		
		//Response response = RestClient.doGet(null, baseURI, "/3/account/"+accountUserName, "Client-ID "+clientId, null, true);
//		RestClient.setBaseUri(baseURI);
//		RequestSpecification request = RestClient.createRequest(null, null, null, true);
//		request.header("Authorization", "Client-ID "+clientId);
//		Response response = RestClient.getResponse("GET", request, "/3/account/"+accountUserName);
		Map<String, String> clientIdMap = Token_Imgur.getClientId();
		Response response = RestClient.doGet(null, baseURI, "/3/account/"+accountUserName, clientIdMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.jsonPath().getString("data.id"));
	}
	
	
	@Test
	public void getAccountBlocks() {
		
		Map<String, String> authToken = Token_Imgur.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, "/3/account/me/block", authToken, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	
	@Test
	public void uploadImagePostApiTest() {
		
		Map<String, String> clientIdMap = Token_Imgur.getClientId();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "Test titleapi");
		formMap.put("description", "Test description api");
		
		Response response = RestClient.doPost("multipart", baseURI, "/3/upload", clientIdMap, null, true, formMap);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	
	
	
	
	
	

}
