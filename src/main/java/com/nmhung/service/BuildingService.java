package com.nmhung.service;

import java.util.List;

import com.nmhung.dto.BuildingDTO;
import com.nmhung.entity.BuildingEntity;

public interface BuildingService {

	List<BuildingEntity> findAll();
	BuildingEntity findOne(Long id);
	Boolean update(BuildingDTO dto);
	Boolean del(BuildingDTO dto);
	BuildingEntity insert(BuildingDTO dto);
		
}
