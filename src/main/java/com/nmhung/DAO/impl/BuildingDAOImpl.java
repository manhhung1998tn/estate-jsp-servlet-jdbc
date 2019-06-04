package com.nmhung.DAO.impl;

import java.util.List;

import com.nmhung.DAO.BuildingDAO;
import com.nmhung.entity.BuildingEntity;

public class BuildingDAOImpl extends AbstractDAOImpl<BuildingEntity> implements BuildingDAO{
	
	
	public List<BuildingEntity> findAll(){
		String sql = "SELECT * FROM building";
		return query(sql, BuildingEntity.class);
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		BuildingDAOImpl dao = new BuildingDAOImpl();
		System.out.println(dao.findAll().get(0).getCreatedBy());
		
		
	}

	@Override
	public BuildingEntity findOne(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM building WHERE id = ?";
		List<BuildingEntity> list = query(sql, BuildingEntity.class,id);
		return list.isEmpty()? null : list.get(0);
	}




	@Override
	public boolean update(BuildingEntity entity) {
		// TODO Auto-generated method stub
		String sql = "UPDATE building SET name = ?  ,numberofbasement = ?  ,"
				+ "buildingarea = ?  ,district = ?  ,"
				+ "ward = ?  ,street = ?  ,"
				+ "structure = ?  ,costrent = ?  ,"
				+ "costdescription = ?  ,servicecost = ?  ,"
				+ "carcost = ?  ,motorbikecost = ?  ,"
				+ "overtimecost = ?  ,electricitycost = ?  ,"
				+ "deposit = ?  ,payment = ?  ,"
				+ "timerent = ?  ,timedecorator = ?  ,"
				+ "managername = ?  ,managerphone = ?  ,"
				+ "createddate = ?  ,modifieddate = ?  ,"
				+ "createdby = ?  ,modifiedby = ? "
				+ " WHERE id = ? ";
				
		return update(sql,entity.getName(),entity.getNumberOfBasement(),
				entity.getBuildingArea(),entity.getDistrict(),
				entity.getWard(), entity.getStreet(),
				entity.getStructure(), entity.getCostRent(),
				entity.getCostDescription(),entity.getServiceCost(),
				entity.getCarCost(),entity.getMotorbikeCost(),
				entity.getElectricityCost(), entity.getElectricityCost(),
				entity.getDeposit(),entity.getPayment(), 
				entity.getTimeErent(),entity.getTimeDecorator(), 
				entity.getManagerName(),entity.getManagerPhone(),
				entity.getCreatedDate(),entity.getModifiedDate(),
				entity.getCreatedBy(),entity.getModifiedBy(),
				entity.getId()
				);
	}




	@Override
	public boolean del(BuildingEntity entity) {
		String sql = "DELETE FROM building WHERE id = ?";
		return del(sql,entity.getId());
	}




	@Override
	public Long insert(BuildingEntity entity) {
		String sql = "INSERT building (name ,numberofbasement ,"
				+ "buildingarea ,district ,"
				+ "ward ,street ,"
				+ "structure ,costrent ,"
				+ "costdescription ,servicecost ,"
				+ "carcost ,motorbikecost ,"
				+ "overtimecost ,electricitycost ,"
				+ "deposit ,payment ,"
				+ "timerent ,timedecorator ,"
				+ "managername ,managerphone ,"
				+ "createddate ,modifieddate ,"
				+ "createdby ,modifiedby )"
				+ "VALUES( ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
				
		return insert(sql,entity.getName(),entity.getNumberOfBasement(),
				entity.getBuildingArea(),entity.getDistrict(),
				entity.getWard(), entity.getStreet(),
				entity.getStructure(), entity.getCostRent(),
				entity.getCostDescription(),entity.getServiceCost(),
				entity.getCarCost(),entity.getMotorbikeCost(),
				entity.getElectricityCost(), entity.getElectricityCost(),
				entity.getDeposit(),entity.getPayment(), 
				entity.getTimeErent(),entity.getTimeDecorator(), 
				entity.getManagerName(),entity.getManagerPhone(),
				entity.getCreatedDate(),entity.getModifiedDate(),
				entity.getCreatedBy(),entity.getModifiedBy());
	}
}
