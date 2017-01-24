package com.gk.study.java.springdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gk.study.java.springdata.model.User;


@Repository
public class SpringDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;//= new JdbcTemplate();
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	
	
	public void insertUser(User user){
		String sql = "insert into users(firstname,lastname) value (?,?)";
		jdbcTemplate.update(sql, new Object[]{user.getFirsName(),user.getLastName()});
	}
	
	public void insertUserUsingNamedParameterJdbcTemplate(User user){
		String sql = "insert into users(firstname,lastname) value (:firstname,:lastname)";
		/*SqlParameterSource sqlParameterSource = new MapSqlParameterSource("firstname", user.getFirsName()).
				addValue("lastname", user.getLastName());*/
		
		//Using Map
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstname", user.getFirsName());
		map.put("lastname", user.getLastName());
		
		SqlParameterSource mapSqlParamSource = new MapSqlParameterSource(map);
		
		
		namedParameterJdbcTemplate.update(sql, mapSqlParamSource);
	
	}
	
	public int getUserCount(){		
		String sql = "select count(*) from users";
		//jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.queryForInt(sql);
		
	}
	
	public String getUserName(int userid){
		String sql = "select firstname from users where userid=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{userid}, String.class);
	}
	
	public User getUserObj(int userid){
		String sql = "select * from users where userid=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{userid}, new UserMapper());
		
	}
	
	public List<User> getUserList(){
		String sql = "select * from users";
		return jdbcTemplate.query(sql, new UserMapper());
	}
	
	private static final class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rn) throws SQLException {
			User user = new User();
			user.setUserId(rs.getInt("userid"));
			user.setFirsName(rs.getString("firstname"));
			return user;
		}
		
	} 

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	
	
	/**
	 * Using Spring datasource
	 * 
	 */
   /*public User getUser(int userid){
		
		Connection conn = null;
		User usr = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select firstname from users where userid=?");
			ps.setInt(1, userid);
			 
			ResultSet rs =  ps.executeQuery();

			if(rs.next()){
				usr = new User(userid, rs.getString(1));
			}
			rs.close();
			ps.close();
		}catch (Exception e) {
			System.out.println("Exception "+e.getLocalizedMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return usr;
	}*/
	
	/**
	 * 
	 * Using Regular JDBC call
	 * /
	
	/*
	 * public User getUser(int userid){
		
		Connection conn = null;
		User usr = null;
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/act?user=gopi&password=way2wins");
			
			PreparedStatement ps = conn.prepareStatement("select firstname from users where userid=?");
			ps.setInt(1, userid);
			
			ResultSet rs =  ps.executeQuery();

			if(rs.next()){
				usr = new User(userid, rs.getString(1));
			}
			rs.close();
			ps.close();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return usr;
	}*/

}
