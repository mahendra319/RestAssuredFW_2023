package com.qa.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {


	/**
	 * This method is used to convert POJO object to a JSON string
	 * @param obj
	 * @return jsonString
	 */
	public static String getSerializedJSON(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		
		try {
			jsonString = mapper.writeValueAsString(obj);
			System.out.println("JSON request payload ..."+jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	return jsonString;	
	}
	
	
}
