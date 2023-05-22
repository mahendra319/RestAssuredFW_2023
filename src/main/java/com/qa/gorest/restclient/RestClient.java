package com.qa.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all http methods which will call the api's and having generic methods for getting the response 
 * and fetch the values from response
 * 
 * @author mahen
 *
 */
public class RestClient {
	
	//GET http method
	
	/**
	 * This method is used to GET API
	 * @param contentType
	 * @param baseURI
	 * @param pathParam
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return this method is returning Response from get call
	 */
	
	//without error handling
//	public static Response doGet(String contentType,String baseURI, String pathParam,  
//			String token, Map<String, String> paramsMap, boolean log) {
//		
//		
//		setBaseUri(baseURI);
//		RequestSpecification request = createRequest(contentType, token, paramsMap, log);
//		
//		 return getResponse("GET", request, pathParam);
//		
//	}
	
	//with error handling
	public static Response doGet(String contentType, String baseURI, String pathParam, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {

		if (setBaseUri(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			return getResponse("GET", request, pathParam);
		}
		return null;
	}

	
	//POST API
	/**
	 * This method is used to call POST API
	 * @param contentType
	 * @param baseURI
	 * @param pathParam
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return response from post call
	 */
	public static Response doPost(String contentType, String baseURI, String pathParam, Map<String, String> token,
			Map<String, String> paramsMap, boolean log, Object obj) {

		if (setBaseUri(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			return getResponse("POST", request, pathParam);
		}
		return null;
	}
	
	
	
	private static void addRequestPayload(RequestSpecification request, Object obj) {

		if (obj instanceof Map) {

			request.formParams((Map<String, String>) obj);
		} else {

			String jsonRequestPayload = TestUtil.getSerializedJSON(obj);
			request.body(jsonRequestPayload);
		}
	}
	
	
	//with error handling
	private static boolean setBaseUri(String baseUri) {
		
		if(baseUri == null || baseUri.isEmpty()) {
			System.out.println("Base uri is missing. either null or blank. Please pass correct base uri");
			return false;			
		}
		try {

			RestAssured.baseURI = baseUri;
			return true;
		} catch (Exception e) {
			System.out.println("Exception occured while assigning baseURI to RestAssured");
			return false;
		}

	}
	
	
	//set BaseUri method with Error handling
//	private static void setBaseUri(String baseUri) {
//
//			RestAssured.baseURI = baseUri;
//	}
//	
	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {
		
		RequestSpecification request;
		
		if(log) {
			
			request=RestAssured.given().log().all();
			
		}else {
			
			request=RestAssured.given();
		}
		
	if(contentType!=null) {	
		if(contentType.equalsIgnoreCase("JSON")) {
			
			request.contentType(ContentType.JSON);
			
		}else if(contentType.equalsIgnoreCase("XML")) {
			
			request.contentType(ContentType.XML);
			
		}else if(contentType.equalsIgnoreCase("TEXT")) {
			
			request.contentType(ContentType.TEXT);
			
		}else if(contentType.equalsIgnoreCase("multipart")) {
			//control name - image, is from application . i.e. Imgur application in this example
			request.multiPart("image", new File("M:\\Selenium_Naveen\\apple.png"));
			
		}
	}	
		if(token.size()>=0) {
			
			//request.header("Authorization", "Bearer "+token);
			request.headers(token);
			
			
		}
		
		if (!(paramsMap == null)) {
			
			request.queryParams(paramsMap);
		}
		
		
		return request;
		
	}
	
	
	private static Response getResponse(String httpMethod,RequestSpecification request, String pathParam) {
		
		return executeApi(httpMethod, request, pathParam);
		
	}
	
	
	private static Response executeApi(String httpMethod, RequestSpecification request, String pathParam) {
		
		Response response =null;
		
		switch (httpMethod) {
		
		case "GET":
			response = request.get(pathParam);
			break;

		case "POST":
			response = request.post(pathParam);
			break;
			
		case "PUT":
			response = request.put(pathParam);
			break;
			
		case "DELETE":
			response = request.delete(pathParam);
			break;
			
		default:
			System.out.println("Please pass correct http method.");
			break;
		}
		
		return response;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
