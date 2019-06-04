package com.nmhung.DAO;

import java.util.List;

import com.nmhung.entity.BuildingEntity;

public interface BuildingDAO extends AbstractDAO<BuildingEntity>{
	List<BuildingEntity> findAll();
	BuildingEntity findOne(Long id);
	boolean update(BuildingEntity entity);
	boolean del(BuildingEntity entity);
	Long insert(BuildingEntity entity);
}
