package com.nmhung.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	
	
	public HttpUtil(String value) {
		this.value = value;
		//System.out.println(value);
	}
	
	
	public <T> T toModel(Class<T> toClass) {
		try {
			
			return new ObjectMapper().readValue(value, toClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	public static String bufferedReaderToJson(BufferedReader bufferedReader) {
		StringBuilder json = new StringBuilder();
		String line;
		
		try {
			while((line = bufferedReader.readLine()) != null) {
				json.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json.toString();
	}
	


	public static HttpUtil of(BufferedReader bufferedReader) {
		return new HttpUtil(HttpUtil.bufferedReaderToJson(bufferedReader));
	}


	
}
