package com.nmhung.repository;

import java.util.List;
import java.util.Map;

import com.nmhung.DAO.AbstractDAO;
import com.nmhung.paging.Pageble;

public interface AbstractRepository <T> extends AbstractDAO<T>{
	Long insert(T entity);
	Boolean update(T entity);
	Boolean delete(T entity);
	List<T> findAll();
	T findById(T entity);
	List<T> findAll(Map<String,Object> propeties, Pageble pageble,Object...where);
	
}
