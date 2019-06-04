package com.nmhung.DAO.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.nmhung.DAO.AbstractDAO;
import com.nmhung.mapper.ResultSetMapper;

public class AbstractDAOImpl<T> implements AbstractDAO<T> {
	public ResourceBundle bundle;

	private String url;
	private String password;
	private String username;
	private String driver;
	protected Class<T> zClass;

	
	
	public AbstractDAOImpl() {
		
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		// TODO Auto-generated constructor stub
		bundle = ResourceBundle.getBundle("db");

		driver = bundle.getString("driver");
		url = bundle.getString("url");
		username = bundle.getString("username");
		password = bundle.getString("password");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<T> query(String sql,Object... parameters) {
		List<T> list = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(sql);
			setParameters(preparedStatement, parameters);
			
			System.out.println(preparedStatement.toString());

			resultSet = preparedStatement.executeQuery();
			list = resultSetMapper.mapRow(resultSet, zClass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}

		}

		return list;
	}

	public Long excuteNonQuery(String sql, boolean isInsert, Object... parameters) {
		Connection con = null;
		try {
			con = getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return (long) 0;
		}

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con.setAutoCommit(false);
			if (isInsert) {
				statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				statement = con.prepareStatement(sql);
			}

			// add parameters

			setParameters(statement, parameters);
			Long rs = (long) statement.executeUpdate();
			
			
			if (rs <= 0) {
				con.rollback();
				return (long) 0;
			}

			if (isInsert) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					rs = resultSet.getLong(1);

				}
			}

			con.commit();

			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return (long) 0;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}

		}
	}

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
		int parameterIndex = 1;
		
		for (Object x : parameters) {

			if (x instanceof Object[]) {
				for (Object y : (Object[]) x) {
					statement.setObject(parameterIndex, y);
					parameterIndex++;
				}
			} else {
				statement.setObject(parameterIndex, x);
				parameterIndex++;
			}

		}
		
	}

	@Override
	public boolean update(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return excuteNonQuery(sql, false, parameters) > 0;
	}

	@Override
	public boolean del(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return excuteNonQuery(sql, false, parameters) > 0;
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return excuteNonQuery(sql, true, parameters);
	}

	@Override
	public T save(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		Connection con = null;
		T rs;
		try {
			con = getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return null;
		}

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con.setAutoCommit(false);

			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// add parameters

			setParameters(statement, parameters);
			int excute = statement.executeUpdate();
			if (excute <= 0) {
				con.rollback();
				return null;
			}
			
			resultSet = statement.getGeneratedKeys();
			
			ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
			List<T> list = resultSetMapper.mapRow(resultSet, zClass);
			

			con.commit();
			
			return list.isEmpty()? null : list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}

		}
	}

}
