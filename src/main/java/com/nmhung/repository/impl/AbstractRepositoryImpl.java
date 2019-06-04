package com.nmhung.repository.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nmhung.DAO.impl.AbstractDAOImpl;
import com.nmhung.annotations.Column;
import com.nmhung.annotations.Id;
import com.nmhung.annotations.Table;
import com.nmhung.paging.Pageble;
import com.nmhung.repository.AbstractRepository;

public class AbstractRepositoryImpl<T> extends AbstractDAOImpl<T> implements AbstractRepository<T> {



	@Override
	public Boolean delete(T entity) {
		// TODO Auto-generated method stub

		List<Field> fieldsId = new ArrayList<>();
		List<Object> parametersId = new ArrayList<>();

		for (Field field : zClass.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)) {
				try {

					Object para = field.get(entity);
					if (para != null) {
						fieldsId.add(field);
						parametersId.add(para);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		Class<?> parent = zClass.getSuperclass();

		while (parent != null) {
			for (Field field : parent.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)) {
					try {

						Object para = field.get(entity);
						if (para != null) {
							fieldsId.add(field);
							parametersId.add(para);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			parent = parent.getSuperclass();
		}
		if (parametersId.size() <= 0) {
			return false;
		}

		String sql = "DELETE FROM " + getTableName() + createCondition(fieldsId);

		System.out.println(sql);
		return del(sql, parametersId.toArray());

	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM " + getTableName();
		return query(sql);
	}

	@Override
	public T findById(T entity) {
		// TODO Auto-generated method stub
		List<Field> fieldsId = new ArrayList<>();
		List<Object> parametersId = new ArrayList<>();

		for (Field field : zClass.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)) {
				try {

					Object para = field.get(entity);
					if (para != null) {
						fieldsId.add(field);
						parametersId.add(para);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		Class<?> parent = zClass.getSuperclass();

		while (parent != null) {
			for (Field field : parent.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)) {
					try {

						Object para = field.get(entity);
						if (para != null) {
							fieldsId.add(field);
							parametersId.add(para);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			parent = parent.getSuperclass();
		}
		if (parametersId.size() <= 0) {
			return null;
		}
		String sql = "SELECT * FROM " + getTableName() + createCondition(fieldsId);
		System.out.println(sql);

		List<T> rs = query(sql, parametersId.toArray());

		return rs.isEmpty() ? null : rs.get(0);
	}




	private String getTableName() {
		if (zClass.isAnnotationPresent(Table.class)) {
			return zClass.getAnnotation(Table.class).name();
		}
		return null;
	}

	@Override
	public Long insert(T entity) {

		List<Field> fields = new ArrayList<>();

		List<Object> parameters = new ArrayList<>();

		for (Field field : zClass.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
				try {
					
					Object para = field.get(entity);
					fields.add(field);
					parameters.add(para);

				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		Class<?> parent = zClass.getSuperclass();

		while (parent != null) {
			for (Field field : parent.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Column.class)) {
					try {

						Object para = field.get(entity);
						fields.add(field);
						parameters.add(para);

					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			parent = parent.getSuperclass();
		}
		
		

		String sql = creaSQLInsert(fields);
		System.out.println(sql);
		
		return insert(sql,parameters.toArray());
	}

	@Override
	public Boolean update(T entity) {

		List<Field> fields = new ArrayList<>();
		List<Field> fieldsId = new ArrayList<>();
		List<Object> parameters = new ArrayList<>();
		List<Object> parametersId = new ArrayList<>();

		for (Field field : zClass.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Column.class)) {
				try {

					Object para = field.get(entity);

					if (!field.isAnnotationPresent(Id.class)) {

						fields.add(field);
						parameters.add(para);
					} else {
						if (para != null) {
							fieldsId.add(field);
							parametersId.add(para);
						}
					}

				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		Class<?> parent = zClass.getSuperclass();

		while (parent != null) {
			for (Field field : parent.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Column.class)) {
					try {

						Object para = field.get(entity);

						if (!field.isAnnotationPresent(Id.class)) {

							fields.add(field);
							parameters.add(para);
						} else {
							if (para != null) {
								fieldsId.add(field);
								parametersId.add(para);
							}

						}

					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			parent = parent.getSuperclass();
		}

		String sql = createSQLUpdate(fields, fieldsId);
		System.out.println(sql);
		if (fieldsId.size() > 0) {
			return update(sql, parameters.toArray(),parametersId.toArray());
		}
		
		
		System.out.println("update err");
		
		return null;

		
	}

	@Override
	public List<T> findAll(Map<String, Object> propeties, Pageble pageble, Object...where) {
		
		// TODO Auto-generated method stub
		List<Object> param = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ").append(getTableName()).append(" ");
		
		if(propeties != null && propeties.size() > 0) {
			sql.append("WHERE 1 = 1 ");
			for(String key : propeties.keySet()) {
				if(propeties.get(key) != null) {
					sql.append(" AND LOWER(").append(key).append(") LIKE ? ");
					param.add('%' + propeties.get(key).toString() + '%');
				}
			}
			
		}
		
		if(where != null) {
			for(Object object : where) {
				sql.append(object);
			}
		}
		
		if(pageble != null) {
			if(pageble.getSorter() != null) {
				sql.append(" ORDER BY ").append(pageble.getSorter().getSortName()).append(" ").append(pageble.getSorter().getSortBy()).append(" ");
			}
			sql.append(" LIMIT ").append(pageble.getOfSet()).append(" , ").append(pageble.getLimit());
			
		}
		
		
		
		
	
		
		List<T> list = null;
		list = query(sql.toString(), param.toArray());
		
		return list;
	}

	
	
	
	private String creaSQLInsert(List<Field> fields) {
		StringBuilder fieldsName = new StringBuilder(" ");
		StringBuilder tmp = new StringBuilder(" ");
		for (Field field : fields) {
			fieldsName.append(field.getAnnotation(Column.class).name() + " ,");
			tmp.append("? ,");
		}
		fieldsName.replace(fieldsName.length() - 1, fieldsName.length(), "");
		tmp.replace(tmp.length() - 1, tmp.length(), "");
		return "INSERT INTO " + getTableName() + "( " + fieldsName.toString() + ") VALUES(" + tmp.toString() + ") ";
	}

	private String createCondition(List<Field> fields) {
		if (fields.size() <= 0) {
			return "";
		}
		StringBuilder condition = new StringBuilder();
		for (Field field : fields) {
			condition.append(field.getAnnotation(Column.class).name() + " = ? AND");

		}
		condition.replace(condition.length() - 3, condition.length(), "");
		return " WHERE " + condition.toString();
	}

	private String createSQLUpdate(List<Field> fields, List<Field> fieldsId) {
		StringBuilder fieldsName = new StringBuilder();
		for (Field field : fields) {
			fieldsName.append(field.getAnnotation(Column.class).name() + " = ? ,");
		}
		fieldsName.replace(fieldsName.length() - 1, fieldsName.length(), "");
		return "UPDATE  " + getTableName() + " SET " + fieldsName + createCondition(fieldsId);
	}


}
