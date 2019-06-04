package com.nmhung.repository;

import java.util.List;

import com.nmhung.entity.BuildingEntity;

public interface BuildingRepository extends AbstractRepository<BuildingEntity>{
	List<BuildingEntity> findByName(BuildingEntity entity);
}
