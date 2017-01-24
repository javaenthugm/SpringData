package com.gk.study.java.springdata.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SimpleJdbcDaoImpl extends  JdbcDaoSupport {//NamedParameterJdbcDaoSupport{//SimpleJdbcDaoSupport {

	public int getUserCount(){		
		String sql = "select count(*) from users";
		return this.getJdbcTemplate().queryForInt(sql);
		
	}

}
