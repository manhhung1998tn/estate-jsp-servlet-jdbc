package com.nmhung.DAO;

import java.util.List;

public interface AbstractDAO<T> {
	List<T> query(String sql,Object... parameters);
	boolean update(String sql,Object... parameters);
	boolean del(String sql,Object... parameters);
	Long insert(String sql,Object... parameters);
	T save(String sql,Object... parameters);
	
}
