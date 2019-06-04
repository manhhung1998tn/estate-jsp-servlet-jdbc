package com.nmhung.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nmhung.DAO.AbstractDAO;
import com.nmhung.DAO.impl.AbstractDAOImpl;
import com.nmhung.annotations.Column;
import com.nmhung.annotations.Entity;
import com.nmhung.annotations.Id;
import com.nmhung.annotations.Table;

public class DemoRepositoryImpl<T> extends AbstractDAOImpl<T> {
	
	

	public List<T> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		String tableName = getTableName(zClass);
		sql.append(tableName);

		

		return this.query(sql.toString());
	}

	public T findById(T entity) {
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		String tableName = getTableName(zClass);

		List<Object> listParametersId = new ArrayList<>();
		if (tableName == null) {
			return null;
		}

		Map<String, Field> mapId = getColumsId(zClass);
		sql.append(tableName + " WHERE ");

		List<Method> listMethod = new ArrayList<>(Arrays.asList(entity.getClass().getMethods()));

		for (String key : mapId.keySet()) {
			try {
				Object parameters = getObjectParameters(entity, mapId.get(key), listMethod);

				if (parameters != null) {

					sql.append(" " + key + " = ? AND");
					listParametersId.add(parameters);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
			

		}

		sql.replace(sql.length() - 3, sql.length(), "");

		System.out.println(sql);

		return this.query(sql.toString(), listParametersId).get(0);
	}

	public T save(T entity) {

		StringBuilder sql = new StringBuilder("");
		String tableName = getTableName(entity.getClass());
		List<Object> listParameters = new ArrayList<>();
		List<Object> listParametersId = new ArrayList<>();
		List<String> columName = new ArrayList<>();
		if (tableName == null) {
			return null;
		}

		//get all colums and field
		Map<String, Field> map = getColums(entity.getClass());
		
		//get colums id
		Map<String, Field> mapId = getColumsId(entity.getClass());

		StringBuilder tmp = new StringBuilder("   ");
		
		//get methods entity
		List<Method> listMethod = new ArrayList<>(Arrays.asList(entity.getClass().getMethods()));
		
		boolean isUpdate = false;
		
		StringBuilder strUpdate = new StringBuilder("");

		
		// kiem tra co id == null
		for (String key : mapId.keySet()) {
			try {
				Object parameters = getObjectParameters(entity, mapId.get(key), listMethod);
				if (parameters != null) {
					strUpdate.append(" " + key + "=? AND");
					listParametersId.add(parameters);
					map.remove(key);
					isUpdate = true;

				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		if(isUpdate) {
			strUpdate.replace(strUpdate.length() - 3, strUpdate.length(), "");
		}
		

		// get paramerter and colum
		for (String key : map.keySet()) {

			try {
				Object parameters = getObjectParameters(entity, map.get(key), listMethod);
				columName.add(key);
				listParameters.add(parameters);

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
		//lay cau truy van
		sql = getSQL(columName, tableName, isUpdate);
		
		if(isUpdate) {
			sql.append(" WHERE " + strUpdate);
		}
		

		System.out.println(sql);

		return this.save(sql.toString(), listParameters.toArray(), listParametersId.toArray());
	}

	private StringBuilder getSQL(List<String> columName, String tableName, boolean isUpdate) {
		StringBuilder sql = new StringBuilder();
		if (isUpdate) {
			sql.append("UPDATE " + tableName + " SET ");
			for (String string : columName) {
				sql.append(" " + string + " = ? ,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");

		} else {
			sql.append("INSERT INTO " + tableName + " (");

			for (String string : columName) {
				sql.append(" " + string + " ,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
			sql.append(") VALUES( ");
			for (int i = 0; i < columName.size(); ++i) {
				sql.append("? ,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
			sql.append(" )");
		}
		return sql;
	}

	public Boolean delete(T entity) {
		StringBuilder sql = new StringBuilder("DELETE FROM ");
		String tableName = getTableName(entity.getClass());

		List<Object> listParametersId = new ArrayList<>();
		if (tableName == null) {
			return false;
		}

		Map<String, Field> mapId = getColumsId(entity.getClass());
		sql.append(tableName + " WHERE ");

		List<Method> listMethod = new ArrayList<>(Arrays.asList(entity.getClass().getMethods()));

		for (String key : mapId.keySet()) {

			Object parameters;
			try {
				parameters = getObjectParameters(entity, mapId.get(key), listMethod);
				if (parameters != null) {

					sql.append(" " + key + " = ? AND");
					listParametersId.add(parameters);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}

		sql.replace(sql.length() - 3, sql.length(), "");

		System.out.println(sql);
		

		return this.del(sql.toString(), listParametersId);
		// return dao.del(sql.toString(), listParametersId.toArray());
	}

	public Object getObjectParameters(Object entity, Field field, List<Method> list)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object rs = null;

		for (int i = 0; i < list.size(); ++i) {
			
			if ((list.get(i).getName().startsWith("get"))) {

				if (list.get(i).getName().toLowerCase().endsWith(field.getName().toLowerCase())
						&& (list.get(i).getName().length() == (field.getName().length() + 3))) {

					rs = list.get(i).invoke(entity);
					list.remove(i);
					break;
				}
			} else {
				list.remove(i);
				i--;

			}
		}

		return rs;
	}

	private Map<String, Field> getColums(Class<?> zClass) {
		Field fieldID;
		String idName;
		Map<String, Field> rs = new HashMap<>();
		if (zClass.isAnnotationPresent(Entity.class)) {
			Field[] fields = zClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class)) {

					Column column = field.getAnnotation(Column.class);
					rs.put(column.name(), field);
				}
			}

			Class parenClass = zClass.getSuperclass();
			if (parenClass != null) {
				Map<String, Field> listParen = getColums(parenClass);
				for (String key : listParen.keySet()) {
					rs.put(key, listParen.get(key));
				}
			}

		}

		return rs;
	}

	private Map<String, Field> getColumsId(Class<?> zClass) {
		
		Map<String, Field> rs = new HashMap<>();
		if (zClass.isAnnotationPresent(Entity.class)) {
			Field[] fields = zClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)) {
					Column column = field.getAnnotation(Column.class);
					rs.put(column.name(), field);
				}
			}

			Class parenClass = zClass.getSuperclass();
			if (parenClass != null) {
				Map<String, Field> listParen = getColumsId(parenClass);
				for (String key : listParen.keySet()) {
					rs.put(key, listParen.get(key));
				}
			}

		}

		return rs;
	}

	private String getTableName(Class<?> zClass) {
	
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			return table.name();
		}

		return null;
	}

	
	public List<T> findByParameter(Field... methods) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	/*
	public Long insert(T entity, Class<T> zClass)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		StringBuilder sql = new StringBuilder("INSERT INTO ");
		String tableName = getTableName(zClass);
		List<Object> listParameters = new ArrayList<>();
		if (tableName == null) {
			return null;
		}

		Map<String, Field> map = getColums(zClass);
		sql.append(tableName + "( ");

		StringBuilder tmp = new StringBuilder("");
		List<Method> listMethod = new ArrayList<>(Arrays.asList(entity.getClass().getMethods()));

		for (String key : map.keySet()) {

			Object parameters = getObjectParameters(entity, map.get(key), listMethod);
			if (parameters != null) {
				sql.append(key + " ,");
				tmp.append("? ,");
				listParameters.add(parameters);
			}

		}
		sql.replace(sql.length() - 1, sql.length(), "");
		tmp.replace(tmp.length() - 1, tmp.length(), "");
		sql.append(" )VALUES(");
		sql.append(tmp);
		sql.append(" ) ");

		System.out.println(sql);
		AbtractDAO<T> dao = new AbstractDAOImpl<T>();

		return dao.insert(sql.toString(), listParameters.toArray());
//		return null;

	}

	public boolean update(T entity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder sql = new StringBuilder("UPDATE ");
		String tableName = getTableName(entity.getClass());
		List<Object> listParameters = new ArrayList<>();
		List<Object> listParametersId = new ArrayList<>();
		if (tableName == null) {
			return false;
		}

		Map<String, Field> map = getColums(entity.getClass());
		Map<String, Field> mapId = getColumsId(entity.getClass());
		sql.append(tableName + " SET ");
		StringBuilder tmp = new StringBuilder("   ");

		List<Method> listMethod = new ArrayList<>(Arrays.asList(entity.getClass().getMethods()));
		System.out.println(listMethod.size());

		for (String key : map.keySet()) {

			Object parameters = getObjectParameters(entity, map.get(key), listMethod);

			if (parameters != null) {
				if (mapId.size() > 0) {
					for (String keyId : mapId.keySet()) {
						if (keyId.equalsIgnoreCase(key)) {
							tmp.append(" " + keyId + "= ? AND");
							listParametersId.add(parameters);
							mapId.remove(keyId);
							break;
						}
					}
				}

				sql.append(key + " = ? ,");
				listParameters.add(parameters);
			}

		}

		sql.replace(sql.length() - 1, sql.length(), "");
		tmp.replace(tmp.length() - 3, tmp.length(), "");
		if (tmp.indexOf("= ?") >= 0) {
			sql.append(" WHERE ");
			sql.append(tmp);
		}

		sql.replace(sql.length() - 1, sql.length(), "");

		System.out.println(sql);
		AbtractDAO<T> dao = new AbstractDAOImpl<T>();

		return dao.update(sql.toString(), listParameters.toArray(), listParametersId.toArray());

	}
*/
}
