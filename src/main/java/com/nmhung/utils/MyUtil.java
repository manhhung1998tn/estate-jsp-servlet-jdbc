package com.nmhung.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
	public static String formatDate(Date date) {
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}
	
	public static Date formatDate(String date) {
		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String currentDate() {
		return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
	
	
	public static String getParameter(String str) {
		
		
		
		return null;
	}
}
