package com.nmhung.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nmhung.dto.BuildingDTO;
import com.nmhung.entity.BuildingEntity;
import com.nmhung.service.BuildingService;
import com.nmhung.service.impl.BuildingServiceImpl;

@RestController
public class BuildingAPI {

	
	
	BuildingService service;
	
	
	
	
	public BuildingAPI() {
		super();
		// TODO Auto-generated constructor stub
		service = new BuildingServiceImpl();
	}


	@GetMapping(value="/api/building")
	public List<BuildingEntity> findAll(){
		return service.findAll();
	}
	
	
	@GetMapping(value="/api/building/{id}")
	public BuildingEntity findOne(@PathVariable Long id){
		return service.findOne(id);
	}
	
	
	@PostMapping(value="api/building")
	public BuildingEntity insert(@RequestBody BuildingDTO dto)  {
		
		
		return service.insert(dto);
	}
	
	@PutMapping(value="api/building")
	public Boolean update(@RequestBody BuildingDTO dto)  {
		
		
		return service.update(dto);
	}
	
	
	@DeleteMapping(value="api/building")
	public boolean delete(@RequestBody BuildingDTO dto)  {
		return service.del(dto);
	}
	
	
	
}
