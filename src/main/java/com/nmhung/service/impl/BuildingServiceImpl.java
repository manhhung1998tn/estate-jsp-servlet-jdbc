package com.nmhung.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nmhung.dto.BuildingDTO;
import com.nmhung.entity.BuildingEntity;
import com.nmhung.repository.BuildingRepository;
import com.nmhung.repository.impl.BuildingRepositoryImpl;
import com.nmhung.service.BuildingService;
import com.nmhung.utils.ConvertUtil;


@Service
public class BuildingServiceImpl implements BuildingService{

	
	
	private BuildingRepository buildingRepository;

	public BuildingServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		buildingRepository = new BuildingRepositoryImpl();
	}

	@Override
	public List<BuildingEntity> findAll() {
		// TODO Auto-generated method stub
		
		return buildingRepository.findAll();
	}

	@Override
	public BuildingEntity findOne(Long id) {
		// TODO Auto-generated method stub
		
		
		BuildingEntity entity = new BuildingEntity();
		entity.setId(id);
		return buildingRepository.findById(entity);
	}

	@Override
	public Boolean update(BuildingDTO dto) {
		// TODO Auto-generated method stub
		
		if(dto.getId() == null) {
			return null;
		}
		
		BuildingEntity entity = (BuildingEntity) ConvertUtil.of(dto).convert(BuildingEntity.class);
		entity.setId(null);
		entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		entity.setModifiedBy("hdz");
		
		
		
		return buildingRepository.update(entity);
	}

	@Override
	public Boolean del(BuildingDTO dto) {
		if(dto.getId() == null) {
			return false;
		}
		
		BuildingEntity entity = (BuildingEntity) ConvertUtil.of(dto).convert(BuildingEntity.class);
		entity.setId(null);
		entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		entity.setModifiedBy("hdz");
		
		
		
		return buildingRepository.delete(entity);
		
	}

	@Override
	public BuildingEntity insert(BuildingDTO dto) {

		System.out.println(dto.getName());
		
		BuildingEntity entity = (BuildingEntity) ConvertUtil.of(dto).convert(BuildingEntity.class);
	
		entity.setId(null);
		entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		entity.setCreatedBy("hdz");
		
		
		
		
		Long idInsert = buildingRepository.insert(entity);
		entity.setId(idInsert);
		
		
		return buildingRepository.findById(entity);
	}
	
	

}
