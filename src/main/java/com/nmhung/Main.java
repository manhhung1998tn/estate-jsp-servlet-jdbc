package com.nmhung;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nmhung.constant.ConstantSQL;
import com.nmhung.entity.BuildingEntity;
import com.nmhung.paging.PageRequest;
import com.nmhung.paging.Pageble;
import com.nmhung.paging.Sorter;
import com.nmhung.repository.impl.BuildingRepositoryImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BuildingRepositoryImpl repository = new BuildingRepositoryImpl();
		
		//entity.setId(21L);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", 2);
		map.put("name", "est");
		Pageble pageble = new PageRequest(1, 10, new Sorter("id", ConstantSQL.DESC));
		List<BuildingEntity> list = repository.findAll(map, pageble);
		
		
		
		
		for(BuildingEntity buildingEntity : list) {
			System.out.println(buildingEntity.getId() + "\t" + buildingEntity.getName());
		}
		


	}

}
