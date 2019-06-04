package com.nmhung.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

	
	private static SessionUtil sessionUtil = null;
	
	public static SessionUtil getInstance() {
		if(sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		
		return sessionUtil;
	}
	
	
	public void put(HttpServletRequest request,String key,Object object) {
		request.getSession().setAttribute(key, object);
	}
	
	public Object getValue(HttpServletRequest request,String key) {
		return request.getSession().getAttribute(key);
	}
	
	
	public void removeValue(HttpServletRequest request,String key) {
		request.getSession().removeAttribute(key);
	}
	
}
