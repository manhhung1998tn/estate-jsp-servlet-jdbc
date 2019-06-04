package com.nmhung.utils;

import org.modelmapper.ModelMapper;

public class ConvertUtil {
	
	private static ConvertUtil instant;
	private Object value;
	public static ConvertUtil of(Object value) {
		if(instant == null) {
			instant = new ConvertUtil();
		}
		
		
		instant.value = value;
		return instant;
	}
	
	public Object convert(Class<?> zClass) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(value, zClass);
	}
	

	
	
}
