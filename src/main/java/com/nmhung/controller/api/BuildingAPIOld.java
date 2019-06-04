package com.nmhung.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmhung.entity.BuildingEntity;
import com.nmhung.service.BuildingService;
import com.nmhung.service.impl.BuildingServiceImplOld;
import com.nmhung.utils.HttpUtil;

/**
 * Servlet implementation class BuildingAPI
 */


public class BuildingAPIOld extends HttpServlet {
	
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//	private BuildingService service;
//    public BuildingAPI() {
//        super();
//        // TODO Auto-generated constructor stub
//        service = new BuildingServiceImplOld();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("application/json");
//		request.setCharacterEncoding("UTF-8");
//		try {
//			Long id = Long.parseLong(request.getParameter("id"));
//			responseObjectInClient(response.getOutputStream(), service.findOne(id));
//		}catch (NumberFormatException e) {
//			List<BuildingEntity> list = service.findAll();
//			responseObjectInClient(response.getOutputStream(), list);
//		}
//		
//		
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("application/json");
//		request.setCharacterEncoding("UTF-8");
//		BuildingEntity entity = getModelClient(request.getReader());
//		Long id = service.insert(entity);
//		
//		
//		
//		responseObjectInClient(response.getOutputStream(), service.findOne(id));
//	}
//	
//	
//	@Override
//	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("application/json");
//		request.setCharacterEncoding("UTF-8");
//		BuildingEntity entity = getModelClient(request.getReader());
//		responseObjectInClient(response.getOutputStream(), service.del(entity));
//		
//	}
//	
//	@Override
//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("application/json");
//		request.setCharacterEncoding("UTF-8");
//		BuildingEntity entity = getModelClient(request.getReader());
//		responseObjectInClient(response.getOutputStream(), service.update(entity));
//		
//	}
//	
//	private BuildingEntity getModelClient(BufferedReader bufferedReader) throws IOException {
//		return HttpUtil.of(bufferedReader).toModel(BuildingEntity.class);
//	}
//	
//	private void responseObjectInClient(OutputStream outputStream, Object object) throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		
//			mapper.writeValue(outputStream,object);
//		
//	}
	

}
