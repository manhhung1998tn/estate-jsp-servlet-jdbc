package com.nmhung.repository.impl;

import java.util.List;

import com.nmhung.entity.BuildingEntity;
import com.nmhung.repository.BuildingRepository;

public class BuildingRepositoryImpl extends AbstractRepositoryImpl<BuildingEntity> implements BuildingRepository{

	@Override
	public List<BuildingEntity> findByName(BuildingEntity entity) {
		
		return null;
	}

}
