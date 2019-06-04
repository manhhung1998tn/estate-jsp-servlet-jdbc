package com.nmhung.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nmhung.DAO.BuildingDAO;
import com.nmhung.DAO.impl.BuildingDAOImpl;
import com.nmhung.entity.BuildingEntity;
import com.nmhung.service.BuildingService;

@Service
public class BuildingServiceImplOld {
	private BuildingDAO dao;

	
	public List<BuildingEntity> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public BuildingEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	public boolean update(BuildingEntity entity) {
		// TODO Auto-generated method stub
		entity.setModifiedBy("saga");
		entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		BuildingEntity entityOld = dao.findOne(entity.getId());
		if(entityOld == null) {
			return false;
		}
		
		entity.setCreatedBy(entityOld.getCreatedBy());
		entity.setCreatedDate(entity.getCreatedDate());
		
		return dao.update(entity);
	}

	public boolean del(BuildingEntity entity) {
		// TODO Auto-generated method stub
		return dao.del(entity);
	}

	public Long insert(BuildingEntity entity) {
		// TODO Auto-generated method stub
		entity.setCreatedBy("Hungdz");
		entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		return dao.insert(entity);
	}
	
	public BuildingServiceImplOld() {
		super();
		dao = new BuildingDAOImpl();
	}

	
	
	
	
	
	
}
