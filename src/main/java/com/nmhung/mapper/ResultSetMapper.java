package com.nmhung.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.nmhung.annotations.Column;
import com.nmhung.annotations.Entity;

public class ResultSetMapper<T> {
	public List<T> mapRow(ResultSet resultSet, Class<T> zClass) throws SQLException {
		List<T> list = null;
		

		if (zClass.isAnnotationPresent(Entity.class)) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			Field[] fields = zClass.getDeclaredFields();
			
			
			list = new ArrayList<>();
			while (resultSet.next()) {
			
				ArrayList<Field> listFields = new ArrayList<>(Arrays.asList(fields));	
				
				try {
					T item = zClass.newInstance();
					for (int i = 0; i < metaData.getColumnCount(); ++i) {
	
						String columName = metaData.getColumnName(i + 1);
						Object columValue = resultSet.getObject(columName);

						
						
						if(!coverResultSetToEntity(listFields,columName,columValue,item)) {
							
							Class parenClass = zClass.getSuperclass();
							
							while(parenClass != null) {
								List<Field> fieldsParen = new ArrayList<>(Arrays.asList(parenClass.getDeclaredFields()));
								if(coverResultSetToEntity(fieldsParen,columName,columValue,item)) {
									break;
								}
								parenClass = parenClass.getSuperclass();
							}
						}


					}

					list.add(item);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
	

		return list;

	}

	private boolean coverResultSetToEntity( List<Field> fields,String columName, Object columValue,T item) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		for(Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if (column.name().equals(columName) && columValue != null) {
					BeanUtils.setProperty(item, field.getName(), columValue);
					
					fields.remove(field);
					return true;
				}
			}
		}
		
		return false;
		
	}

	
}
